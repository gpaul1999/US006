package com.dxc.createnewmotopolicy.dao.implement;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.createnewmotopolicy.dao.ClaimDAO;
import com.dxc.createnewmotopolicy.entity.Claim;

@Transactional
public class ClaimDAOImpl implements ClaimDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Claim> listClaim() {
		List<Claim> list = new ArrayList<Claim>();
		Session session = sessionFactory.getCurrentSession();
		list = session.createQuery("from Claim", Claim.class).getResultList();
		session.flush();
		return list;
	}

	public void addClaim(Claim claim) {
		Session session = sessionFactory.getCurrentSession();
		//random Claim number
		String id = null;
		while (id == null || findClaimByNumber(id) != null) {
			id = RandomStringUtils.random(8, false, true);
		}
		claim.setClaimNumber(id);
		System.out.println(claim);
		try {
			session.persist(claim);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public Claim findClaimByNumber(String number) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("deprecation")
		Criteria crit = session.createCriteria(Claim.class);
		crit.add(Restrictions.eq("claimNumber", number));
		return (Claim) crit.uniqueResult();
	}

	public void updateClaimInfor(Claim claim) {
		Session session = sessionFactory.getCurrentSession();
		session.load(Claim.class, claim.getClaimNumber());
		session.update(claim);
		System.out.println("Update sucessful!");
	}

}
