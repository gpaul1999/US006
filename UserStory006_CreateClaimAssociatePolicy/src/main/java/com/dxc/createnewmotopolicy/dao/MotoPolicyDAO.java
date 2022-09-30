package com.dxc.createnewmotopolicy.dao;

import java.util.List;

import com.dxc.createnewmotopolicy.entity.MotoPolicy;

public interface MotoPolicyDAO {
	public List<MotoPolicy> listMotoPolicy();
	public void addPolicy(MotoPolicy policy);
	public MotoPolicy findPolicyByCode(String code);
	public void updatePolicyInfor(MotoPolicy policy);
	public MotoPolicy findPolicyByEngine(String code);
	public MotoPolicy findPolicyByChassis(String code);
}
