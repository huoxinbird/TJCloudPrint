package cn.edu.tongji.sse.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import cn.edu.tongji.sse.model.Shop;
import cn.edu.tongji.sse.model.User;

public class ShopDao extends HibernateDaoSupport implements IShopDao {

	@Override
	public boolean addShopForUser(User u, Shop s) {
		
		s.setUser(u);
		getHibernateTemplate().saveOrUpdate(s);
		
		return true;
	}

	
	public Shop getShopWithUserId(final long id) {
		
		List<Shop> list = getHibernateTemplate().execute(new HibernateCallback< List<Shop> > () {			
			@SuppressWarnings("unchecked")
			public List<Shop> doInHibernate(Session session) throws HibernateException {					
				
				List<Shop> result = session.createCriteria(Shop.class).add(
						Restrictions.eq("user.id", id)).list();				
								
				return result;
			}
		
		});								
			
		
		if (list.size() == 1) {
			System.out.println("shop 1");
			return list.get(0);
		}
		
		return null;
	}

}
