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

import com.dxc.createnewmotopolicy.dao.MotoPolicyDAO;
import com.dxc.createnewmotopolicy.entity.MotoPolicy;
import com.dxc.createnewmotopolicy.model.MotoPolicyInfo;
import com.dxc.createnewmotopolicy.validator.MotoPolicyInfoValidator;

@Controller
@Transactional
@EnableWebMvc
public class PolicyController {
	private List<String> lsCurrency = Arrays.asList("VND", "USD", "SGD");
	private static final Log log = LogFactory.getLog(PolicyController.class);

	@Autowired
	private MotoPolicyDAO policyDao;

	@Autowired
	private MotoPolicyInfoValidator policyInforValidator;

	@InitBinder
	public void myInitBinder (WebDataBinder binder) {
		Object target = binder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target: " + target);

		if (target.getClass()==MotoPolicyInfo.class) {
			binder.setValidator(policyInforValidator);
		}
	}

	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping("/add_form")
	public String showForm (Model model) {
		MotoPolicyInfo policyInfo = new MotoPolicyInfo();
		model.addAttribute("policyForm", policyInfo);
		model.addAttribute("lsCurrency", lsCurrency);
		return "add_form";
	}

	@RequestMapping(value = {"/add_form"}, method = RequestMethod.POST, params = "save")
	@Transactional(propagation = Propagation.NEVER)
	public String addPolicy(Model  model, @ModelAttribute("policyForm") @Validated MotoPolicyInfo policyInfor, 
			BindingResult result, final RedirectAttributes redirectAttribute) {
		model.addAttribute("lsCurrency", lsCurrency);
		if(result.hasErrors()) {
			log.error("ADD NEW MOTO POLICY FAIL!");
			return "add_form";
		}

		//Parse String to Date for add policy
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date inceptionDate = null;
		Date expiryDate = null;
		try {
			inceptionDate = df.parse(policyInfor.getInceptionDate());
			expiryDate = df.parse(policyInfor.getExpiryDate());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Parse String to double for add policy
		double sumInsured = Double.parseDouble(policyInfor.getSumInsured());
		double rate = Double.parseDouble(policyInfor.getRate());
		double annualPre = Double.parseDouble(policyInfor.getAnnualPre());
		double postedPre = Double.parseDouble(policyInfor.getPostedPre());

		//Create New Policy
		MotoPolicy policy = new MotoPolicy(inceptionDate, expiryDate, 
				policyInfor.getPolicyOwner(), policyInfor.getEngineNumber(), policyInfor.getChassisNumber(), 
				policyInfor.getVehicleRegisNumber(), policyInfor.getBillingCurrency(), Double.parseDouble(String.format("%.2f", sumInsured)), 
				Double.parseDouble(String.format("%.5f", rate)), Double.parseDouble(String.format("%.2f", annualPre)), 
				Double.parseDouble(String.format("%.2f", postedPre)), "PN");

		System.out.println(policy);

		//Add Policy
		try {
			policyDao.addPolicy(policy);
			log.info("ADD NEW MOTO POLICY SUCESSFULLY!");
		} catch (Exception e) {
			String message = e.getMessage();
			model.addAttribute("message", message);
			log.error("ADD NEW MOTO POLICY FAIL!");
			return "add_form";
		}
		return "redirect:/result_add";
	}
	
	@RequestMapping("/result_add")
	private String showResult(Model model) {
		return "result_add";
	}
	
	@RequestMapping(value = {"/add_form"}, method = RequestMethod.POST, params = "issue")
	@Transactional(propagation = Propagation.NEVER)
	public String issuePolicy(Model  model, @ModelAttribute("policyForm") @Validated MotoPolicyInfo policyInfor, 
			BindingResult result, final RedirectAttributes redirectAttribute) {
		model.addAttribute("lsCurrency", lsCurrency);
		if(result.hasErrors()) {
			log.error("ADD NEW MOTO POLICY FAIL!");
			return "add_form";
		}

		//Parse String to Date for add policy
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date inceptionDate = null;
		Date expiryDate = null;
		try {
			inceptionDate = df.parse(policyInfor.getInceptionDate());
			expiryDate = df.parse(policyInfor.getExpiryDate());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Parse String to double for add policy
		double sumInsured = Double.parseDouble(policyInfor.getSumInsured());
		double rate = Double.parseDouble(policyInfor.getRate());
		double annualPre = Double.parseDouble(policyInfor.getAnnualPre());
		double postedPre = Double.parseDouble(policyInfor.getPostedPre());

		//Create New Policy
		MotoPolicy policy = new MotoPolicy(inceptionDate, expiryDate, 
				policyInfor.getPolicyOwner(), policyInfor.getEngineNumber(), policyInfor.getChassisNumber(), 
				policyInfor.getVehicleRegisNumber(), policyInfor.getBillingCurrency(), Double.parseDouble(String.format("%.2f", sumInsured)), 
				Double.parseDouble(String.format("%.5f", rate)), Double.parseDouble(String.format("%.2f", annualPre)), 
				Double.parseDouble(String.format("%.2f", postedPre)), "IF");

		System.out.println(policy);

		//Add Policy
		try {
			policyDao.addPolicy(policy);
			log.info("ADD NEW MOTO POLICY SUCESSFULLY!");
		} catch (Exception e) {
			String message = e.getMessage();
			model.addAttribute("message", message);
			log.error("ADD NEW MOTO POLICY FAIL!");
			return "add_form";
		}
		return "redirect:/result_issue";
	}

	@RequestMapping("/result_issue")
	private String showResultIssue(Model model) {
		return "result_issue";
	}
	

	@RequestMapping("/edit_policy")
	public String showEditForm (Model model, @RequestParam(value = "policyNumber") String policyNumber) {
		MotoPolicyInfo policyInfor = new MotoPolicyInfo();
		model.addAttribute("policyForm", policyInfor);
		MotoPolicy policy = policyDao.findPolicyByCode(policyNumber);
		System.out.println(policy);
		model.addAttribute("policyInfor", policy);
		return "edit_policy";
	}

	@RequestMapping(value = { "/edit_policy" }, method = RequestMethod.POST)
	@Transactional(propagation = Propagation.NEVER)
	public String editAgent(Model  model, @RequestParam(value = "policyNumber") String policyNumber, 
			@ModelAttribute("policyForm") @Validated MotoPolicyInfo policyInfor, 
			BindingResult result, final RedirectAttributes redirectAttribute) {
		if(result.hasErrors()) {
			log.error("MODIFY MOTO POLICY FAIL!");
			return "edit_policy";
		}

		//Parse String to Date for add policy
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date inceptionDate = null;
		Date expiryDate = null;
		try {
			inceptionDate = df.parse(policyInfor.getInceptionDate());
			expiryDate = df.parse(policyInfor.getExpiryDate());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Parse String to double for add policy
		double sumInsured = Double.parseDouble(policyInfor.getSumInsured());
		double rate = Double.parseDouble(policyInfor.getRate());
		double annualPre = Double.parseDouble(policyInfor.getAnnualPre());
		double postedPre = Double.parseDouble(policyInfor.getPostedPre());

		//Create New Policy
		MotoPolicy policy = new MotoPolicy(inceptionDate, expiryDate, 
				policyInfor.getPolicyOwner(), policyInfor.getEngineNumber(), policyInfor.getChassisNumber(), 
				policyInfor.getVehicleRegisNumber(), policyInfor.getBillingCurrency(), Double.parseDouble(String.format("%.2f", sumInsured)), 
				Double.parseDouble(String.format("%.5f", rate)), Double.parseDouble(String.format("%.2f", annualPre)), 
				Double.parseDouble(String.format("%.2f", postedPre)), "PN");
		policy.setPolicyNumber(policyNumber);
		System.out.println("See Policy before update: " + policy);
		try {
			policyDao.updatePolicyInfor(policy);
			log.info("MODIFY MOTO POLICY SUCESSFULLY!");
		} catch (Exception e) {
			String message = e.getMessage();
			model.addAttribute("message", message);
			log.error("MODIFY MOTO POLICY FAIL!");
			return "edit_policy";
		}
		return "redirect:/result_modify";
	}

	@RequestMapping("/result_modify")
	private String showResultModify(Model model) {
		return "result_modify";
	}
	
	@RequestMapping("/issue_policy")
	public String issueExistPolicy (Model model, @RequestParam(value = "policyNumber") String policyNumber) {
		MotoPolicy policy = policyDao.findPolicyByCode(policyNumber);
		policy.setStatus("IF");
		System.out.println(policy);
		policyDao.updatePolicyInfor(policy);
		log.info("ISSUE MOTO POLICY SUCESSFULLY!");
		return "issue_policy";
	}
	
}
