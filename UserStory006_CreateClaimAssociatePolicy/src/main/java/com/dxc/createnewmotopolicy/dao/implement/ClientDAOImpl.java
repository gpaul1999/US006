package com.dxc.createnewmotopolicy.dao.implement;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.createnewmotopolicy.dao.ClientDAO;
import com.dxc.createnewmotopolicy.entity.Client;
@Transactional
public class ClientDAOImpl implements ClientDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public Client findClient(String id) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("deprecation")
		Criteria crit = session.createCriteria(Client.class);
		crit.add(Restrictions.eq("clientNumber", id));
		return (Client) crit.uniqueResult();
	}

}
