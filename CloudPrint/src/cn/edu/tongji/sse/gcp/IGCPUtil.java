package cn.edu.tongji.sse.gcp;

import java.util.List;

import org.codehaus.jackson.JsonNode;

import cn.edu.tongji.sse.model.Printer;

public interface IGCPUtil {
	public String[] getAccessTokenWithCode(String code);
	public List<Printer> getPrinterList(String token, String refreshToken);
	public void getPrinterDetail(String token, String printerId);
	public JsonNode submit(String token, String printerId, String title, String filePath, String contentType, String tag);
	public JsonNode requestToGcp(String token, String uri);
	public String getRrefreshToken(String refresh);
}
