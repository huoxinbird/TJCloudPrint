package cn.edu.tongji.sse.gcp;

import java.io.File;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import cn.edu.tongji.sse.model.Printer;

import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.auth.oauth2.draft10.AccessTokenRequest.AuthorizationCodeGrant;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;


@Component
public class GCPUtil implements IGCPUtil {
	
	private Client client;
	static private String capabilities = "[{\"name\":\"psk:PageMediaSize\",\"type\":\"Feature\",\"options\":[{\"name\":\"psk:ISOA4\",\"scoredProperties\":{\"psk:MediaSizeWidth\":\"210000\",\"psk:MediaSizeHeight\":\"297000\"}}]}]";
	
	public String[] getAccessTokenWithCode(String code) {
		System.out.println("requestAccessToken");
		try {
			AuthorizationCodeGrant request = new AuthorizationCodeGrant(
					new NetHttpTransport(), new JacksonFactory(),
					"https://accounts.google.com/o/oauth2/token",
					client.getClientId(), client.getClientSecret(), code,
					client.getRedirectUrl());
			AccessTokenResponse response = request.execute();
			
			System.out.println("Access token: " + response.accessToken);
			System.out.println("Expires in: " + response.expiresIn);
			System.out.println("Refresh token: " + response.refreshToken);
			
			String[] tokens = new String[2];
			tokens[0] = response.accessToken;
			tokens[1] = response.refreshToken;
			return tokens;

		}

		catch (Exception e) {
			
			return null;
		}
	}
	

	public void getPrinterDetail(String token, String printerId) {
		JsonNode rootNode = requestToGcp(token,"http://www.google.com/cloudprint/printer?printerid="+printerId);
		JsonNode successNode = rootNode.get("success");
		System.out.println("get capabilities: "+successNode.asBoolean());
		
		
	}
	
	public List<Printer> getPrinterList(String token) {
		
		JsonNode rootNode = requestToGcp(token,"http://www.google.com/cloudprint/search");
		List<Printer> list = new ArrayList<Printer>();
				
		JsonNode successNode = rootNode.get("success");
		System.out.println("get printer list: "+successNode.asBoolean()); 
		
		JsonNode printersNode = rootNode.get("printers");
		Iterator<JsonNode> printers = printersNode.getElements();
		
		
		while (printers.hasNext()) {
			Printer p = new Printer();
			JsonNode printerNode = (JsonNode) printers.next();
		
			p.setId(printerNode.get("id").asText());
			p.setName(printerNode.get("name").asText());

			
			list.add(p);
			System.out.println("name: "+p.getName());
		}
				
		
		return list;
	}
	
	public JsonNode submit(String token, String printerId, String title, String filePath, String contentType, String tag) {
		JsonNode result = null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://www.google.com/cloudprint/submit");
		httppost.addHeader("Authorization", "OAuth "+ token);

		MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
			
		try {
			
		
		
		reqEntity.addPart("printerid", new StringBody(printerId));
		reqEntity.addPart("title", new StringBody(title));	
		reqEntity.addPart("capabilities", new StringBody(capabilities));
		
		FileBody file = new FileBody(new File(filePath));			
		reqEntity.addPart("content", file );
				
		reqEntity.addPart("contentType", new StringBody(contentType));
		reqEntity.addPart("tag", new StringBody(tag));
		
		httppost.setEntity(reqEntity);

		System.out.println("executing request " + httppost.getRequestLine());
		
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity resEntity = response.getEntity();

		if (resEntity != null) {
			System.out.println("response not null");
			InputStream in = resEntity.getContent();
			
			StringBuffer   out   =   new   StringBuffer();
	        byte[]   b   =   new   byte[4096];
	        for   (int   n;   (n   =   in.read(b))   !=   -1;)   {
	                out.append(new   String(b,   0,   n));
	        } 
	        System.out.println(out.toString());
			ObjectMapper mapper = new ObjectMapper();
			result = mapper.readValue(out.toString(), JsonNode.class);
			
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	
	private JsonNode requestToGcp(String token, String uri) {
		
		JsonNode result = null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
		
		try {
			HttpGet httpGet = new HttpGet(uri);
			httpGet.addHeader("Authorization", "OAuth "+ token);
						
			
			HttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			
			System.out.println(response.getStatusLine());
			
			if (entity != null) {
				System.out.println("entity != null");
				
				InputStream in = entity.getContent();
				System.out.println("getContent");
//				StringBuffer   out   =   new   StringBuffer();
//		        byte[]   b   =   new   byte[4096];
//		        for   (int   n;   (n   =   in.read(b))   !=   -1;)   {
//		                out.append(new   String(b,   0,   n));
//		        } 
//		        System.out.println(out.toString());
				
		        ObjectMapper mapper = new ObjectMapper();
				result = mapper.readValue(in, JsonNode.class);
			}
		}
		catch (Exception e) {
			System.out.println("null e");
			result = null;
		}
		finally {
			
			httpclient.getConnectionManager().shutdown();
		}
		
		
		return result;
	}
	
	@Resource
	public void setClient(Client client) {		
		this.client = client;
	}
}
