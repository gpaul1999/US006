package com.dxc.createnewmotopolicy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.dxc.createnewmotopolicy.dao.ClaimDAO;
import com.dxc.createnewmotopolicy.dao.MotoPolicyDAO;
import com.dxc.createnewmotopolicy.entity.Claim;
import com.dxc.createnewmotopolicy.entity.MotoPolicy;

@Controller
@Transactional
@EnableWebMvc
public class MainController {
	
	@Autowired
	private MotoPolicyDAO motoPolicyDao;
	
	@Autowired
	private ClaimDAO claimDao;
	
	@RequestMapping("/403")
	public String accessDenied() {
		return "/403";
	}
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	@RequestMapping("/policy_list")
	public String policyListHandler(Model model) {
		List<MotoPolicy> list = motoPolicyDao.listMotoPolicy();
		model.addAttribute("listMotoPolicy", list);
		return "moto_policy_list";
	}
	@RequestMapping(value = { "/policy_info" }, method = RequestMethod.GET)
	public String agentInfor(Model model, @RequestParam(value = "policyNumber") String policyNumber) {
		MotoPolicy policy = motoPolicyDao.findPolicyByCode(policyNumber);
		System.out.println(policy);
		model.addAttribute("policyInfor", policy);
		return "policy_info";
	}
	@RequestMapping("/claim_list")
	public String claimListHandler(Model model) {
		List<Claim> list = claimDao.listClaim();
		model.addAttribute("listClaim", list);
		return "claim_list";
	}
}
