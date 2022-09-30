package com.dxc.createnewmotopolicy.dao;

import java.util.List;

import com.dxc.createnewmotopolicy.entity.Claim;

public interface ClaimDAO {
	public List<Claim> listClaim();
	public void addClaim(Claim claim);
	public Claim findClaimByNumber(String number);
	public void updateClaimInfor(Claim claim);
}
