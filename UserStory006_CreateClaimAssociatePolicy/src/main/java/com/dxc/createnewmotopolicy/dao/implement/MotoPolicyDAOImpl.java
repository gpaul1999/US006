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

import com.dxc.createnewmotopolicy.dao.MotoPolicyDAO;
import com.dxc.createnewmotopolicy.entity.MotoPolicy;
@Transactional
public class MotoPolicyDAOImpl implements MotoPolicyDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<MotoPolicy> listMotoPolicy() {
		List<MotoPolicy> list = new ArrayList<MotoPolicy>();
		Session session = sessionFactory.getCurrentSession();
		list = session.createQuery("from MotoPolicy", MotoPolicy.class).getResultList();
		session.flush();
		return list;
	}

	public void addPolicy(MotoPolicy policy) {
		Session session = sessionFactory.getCurrentSession();
		//random Policy Number
		String id = null;
		while (id == null || findPolicyByCode(id) != null) {
			id = RandomStringUtils.random(8, false, true);
		}
		policy.setPolicyNumber(id);
		try {
			session.persist(policy);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MotoPolicy findPolicyByCode(String code) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("deprecation")
		Criteria crit = session.createCriteria(MotoPolicy.class);
		crit.add(Restrictions.eq("policyNumber", code));
		return (MotoPolicy) crit.uniqueResult();
	}

	public void updatePolicyInfor(MotoPolicy policy) {
		Session session = sessionFactory.getCurrentSession();
		session.load(MotoPolicy.class, policy.getPolicyNumber());
		session.update(policy);
		System.out.println("Update sucessful!");
	}

	public MotoPolicy findPolicyByEngine(String code) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("deprecation")
		Criteria crit = session.createCriteria(MotoPolicy.class);
		crit.add(Restrictions.eq("engineNumber", code));
		return (MotoPolicy) crit.uniqueResult();
	}

	public MotoPolicy findPolicyByChassis(String code) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("deprecation")
		Criteria crit = session.createCriteria(MotoPolicy.class);
		crit.add(Restrictions.eq("chassisNumber", code));
		return (MotoPolicy) crit.uniqueResult();
	}

}
