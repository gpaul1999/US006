package com.dxc.createnewmotopolicy.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dxc.createnewmotopolicy.dao.ClaimDAO;
import com.dxc.createnewmotopolicy.dao.MotoPolicyDAO;
import com.dxc.createnewmotopolicy.entity.Claim;
import com.dxc.createnewmotopolicy.entity.MotoPolicy;
import com.dxc.createnewmotopolicy.model.ClaimInfo;
import com.dxc.createnewmotopolicy.validator.ClaimInfoValidator;

@Controller
@Transactional
@EnableWebMvc
public class ClaimController {
	private List<String> lsCurrency = Arrays.asList("VND", "USD", "SGD");
	private static final Log log = LogFactory.getLog(ClaimController.class);
	
	@Autowired
	private MotoPolicyDAO policyDao;
	
	@Autowired
	private ClaimDAO claimDao;
	
	@Autowired
	private ClaimInfoValidator claimInfoValidator;
	
	@InitBinder
	public void myInitBinder (WebDataBinder binder) {
		Object target = binder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target: " + target);

		if (target.getClass()==ClaimInfo.class) {
			binder.setValidator(claimInfoValidator);
		}
	}
	
	@RequestMapping("/add_claim")
	public String showForm (Model model) {
		ClaimInfo claimInfo = new ClaimInfo();
		model.addAttribute("claimForm", claimInfo);
		model.addAttribute("lsCurrency", lsCurrency);
		return "add_claim";
	}
	
	@RequestMapping(value = {"/add_claim"}, params = "refresh")
	public String showRefreshForm (Model model, @RequestParam(value = "policyNumber") String policyNumber, 
			@ModelAttribute("claimForm") @Validated ClaimInfo claimInfo, 
			BindingResult result, final RedirectAttributes redirectAttribute) {
		model.addAttribute("claimForm", claimInfo);
		model.addAttribute("lsCurrency", lsCurrency);
		MotoPolicy policy = policyDao.findPolicyByCode(policyNumber);
		model.addAttribute("policyInfo", policy);
		return "add_claim";
	}
	
	@RequestMapping(value = {"/add_claim"}, method = RequestMethod.POST, params = "save")
	@Transactional(propagation = Propagation.NEVER)
	public String addClaim(Model  model, @ModelAttribute("claimForm") @Validated ClaimInfo claimInfo, 
			BindingResult result, final RedirectAttributes redirectAttribute) {
		model.addAttribute("lsCurrency", lsCurrency);
		if(result.hasErrors()) {
			log.error("ADD NEW CLAIM FAIL!");
			return "add_claim";
		}

		//Parse String to Date for add claim
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date occurredDate = null;
		try {
			occurredDate = df.parse(claimInfo.getOccurredDate());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Parse String to double for add claim
		double sumInsured = Double.parseDouble(claimInfo.getSumInsured());
		double reserveAmount = Double.parseDouble(claimInfo.getReserveAmount());
		
		//Create New Claim
		Claim claim = new Claim(claimInfo.getPolicyNumber(), occurredDate, claimInfo.getPolicyOwner(), 
				claimInfo.getEngineNumber(), claimInfo.getChassisNumber(), claimInfo.getVehicleRegisNumber(), 
				Double.parseDouble(String.format("%.2f", sumInsured)), claimInfo.getReserveCurrency(), 
				Double.parseDouble(String.format("%.2f", reserveAmount)), "Pending");
		System.out.println(claim);

		//Add Policy
		try {
			claimDao.addClaim(claim);
			log.info("ADD NEW CLAIM SUCESSFULLY!");
		} catch (Exception e) {
			String message = e.getMessage();
			model.addAttribute("message", message);
			log.error("ADD NEW CLAIM FAIL!");
			return "add_claim";
		}
		return "redirect:/result_add_claim";
	}
	
	@RequestMapping(value = {"/add_claim"}, method = RequestMethod.POST, params = "approve")
	@Transactional(propagation = Propagation.NEVER)
	public String approveClaim(Model  model, @ModelAttribute("claimForm") @Validated ClaimInfo claimInfo, 
			BindingResult result, final RedirectAttributes redirectAttribute) {
		model.addAttribute("lsCurrency", lsCurrency);
		if(result.hasErrors()) {
			log.error("ADD NEW CLAIM FAIL!");
			return "add_claim";
		}

		//Parse String to Date for add claim
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date occurredDate = null;
		try {
			occurredDate = df.parse(claimInfo.getOccurredDate());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Parse String to double for add claim
		double sumInsured = Double.parseDouble(claimInfo.getSumInsured());
		double reserveAmount = Double.parseDouble(claimInfo.getReserveAmount());
		
		//Create New Claim
		Claim claim = new Claim(claimInfo.getPolicyNumber(), occurredDate, claimInfo.getPolicyOwner(), 
				claimInfo.getEngineNumber(), claimInfo.getChassisNumber(), claimInfo.getVehicleRegisNumber(), 
				Double.parseDouble(String.format("%.2f", sumInsured)), claimInfo.getReserveCurrency(), 
				Double.parseDouble(String.format("%.2f", reserveAmount)), "Active");
		System.out.println(claim);

		//Add Policy
		try {
			claimDao.addClaim(claim);
			log.info("ADD NEW CLAIM SUCESSFULLY!");
		} catch (Exception e) {
			String message = e.getMessage();
			model.addAttribute("message", message);
			log.error("ADD NEW CLAIM FAIL!");
			return "add_claim";
		}
		return "redirect:/result_approve_claim";
	}
	
	@RequestMapping("/result_add_claim")
	private String showSaveStatus(Model model) {
		return "result_add_claim";
	}
	
	@RequestMapping("/result_approve_claim")
	private String showApproveStatus(Model model) {
		return "result_approve_claim";
	}
	
	@RequestMapping("/edit_claim")
	public String showEditForm (Model model, @RequestParam(value = "claimNumber") String claimNumber) {
		ClaimInfo claimInfo = new ClaimInfo();
		model.addAttribute("claimForm", claimInfo);
		Claim claim = claimDao.findClaimByNumber(claimNumber);
		System.out.println(claim);
		model.addAttribute("claimInfo", claim);
		return "edit_claim";
	}
	
	@RequestMapping(value = {"/edit_claim"}, method = RequestMethod.POST)
	@Transactional(propagation = Propagation.NEVER)
	public String editClaim(Model  model, @RequestParam(value = "claimNumber") String claimNumber, 
			@ModelAttribute("claimForm") @Validated ClaimInfo claimInfo, 
			BindingResult result, final RedirectAttributes redirectAttribute) {
		model.addAttribute("lsCurrency", lsCurrency);
		if(result.hasErrors()) {
			log.error("EDIT CLAIM FAIL!");
			return "edit_claim";
		}

		//Parse String to Date for add claim
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date occurredDate = null;
		try {
			occurredDate = df.parse(claimInfo.getOccurredDate());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Parse String to double for add claim
		double sumInsured = Double.parseDouble(claimInfo.getSumInsured());
		double reserveAmount = Double.parseDouble(claimInfo.getReserveAmount());
		
		//Create New Claim
		Claim claim = new Claim(claimInfo.getPolicyNumber(), occurredDate, claimInfo.getPolicyOwner(), 
				claimInfo.getEngineNumber(), claimInfo.getChassisNumber(), claimInfo.getVehicleRegisNumber(), 
				Double.parseDouble(String.format("%.2f", sumInsured)), claimInfo.getReserveCurrency(), 
				Double.parseDouble(String.format("%.2f", reserveAmount)), "Pending");
		claim.setClaimNumber(claimNumber);
		System.out.println(claim);
		//Edit Policy
		try {
			claimDao.updateClaimInfor(claim);
			log.info("EDIT CLAIM SUCESSFULLY!");
		} catch (Exception e) {
			String message = e.getMessage();
			model.addAttribute("message", message);
			log.error("EDIT CLAIM FAIL!");
			return "edit_claim";
		}
		return "redirect:/result_modify_claim";
	}
	
	@RequestMapping("/result_modify_claim")
	private String showModifyStatus(Model model) {
		return "result_modify_claim";
	}
	
	@RequestMapping("/approve_claim")
	public String approveExistPolicy (Model model, @RequestParam(value = "claimNumber") String claimNumber) {
		Claim claim = claimDao.findClaimByNumber(claimNumber);
		claim.setStatus("Active");
		System.out.println(claim);
		claimDao.updateClaimInfor(claim);
		log.info("APPROVE CLAIM SUCESSFULLY!");
		return "result_approve";
	}
	
	
}
