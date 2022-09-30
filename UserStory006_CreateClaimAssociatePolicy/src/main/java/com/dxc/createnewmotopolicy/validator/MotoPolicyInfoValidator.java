package com.dxc.createnewmotopolicy.validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dxc.createnewmotopolicy.dao.ClientDAO;
import com.dxc.createnewmotopolicy.dao.MotoPolicyDAO;
import com.dxc.createnewmotopolicy.entity.Client;
import com.dxc.createnewmotopolicy.entity.MotoPolicy;
import com.dxc.createnewmotopolicy.model.MotoPolicyInfo;

@Component
public class MotoPolicyInfoValidator implements Validator{
	@Autowired
	private MotoPolicyDAO policyDao;
	@Autowired
	private ClientDAO clientDao;
	
	public boolean supports(Class<?> clazz) {
		return clazz == MotoPolicyInfo.class;
	}

	public void validate(Object target, Errors errors) {
		MotoPolicyInfo policyInfor = (MotoPolicyInfo) target;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		//Validate for Inception Date
		ValidationUtils.rejectIfEmpty(errors, "inceptionDate", "NotEmpty.policyForm.inceptionDate");
		String inceptionDate = policyInfor.getInceptionDate();
		Date incepDate = new Date();
		if (inceptionDate.length()>0) {
			try {
				incepDate = dateFormat.parse(inceptionDate);
				if (incepDate.compareTo(new Date())>0) {
					errors.rejectValue("inceptionDate", "Greater.policyForm.inceptionDate");
				}
			} catch (Exception e) {
				errors.rejectValue("inceptionDate", "WrongFormat.policyForm.inceptionDate");
			}
		}
		
		//Validate for Expiry Date
		ValidationUtils.rejectIfEmpty(errors, "expiryDate", "NotEmpty.policyForm.expiryDate");
		String expiryDate = policyInfor.getExpiryDate();
		if (expiryDate.length()>0) {
			try {
				Date expiry = dateFormat.parse(expiryDate);
				if (expiry.compareTo(incepDate)<0) {
					errors.rejectValue("expiryDate", "Greater.policyForm.expiryDate");
				}
			} catch (Exception e) {
				errors.rejectValue("expiryDate", "WrongFormat.policyForm.expiryDate");
			}
		}
		
		//Validate for Policy Owner
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "policyOwner", "NotEmpty.policyForm.policyOwner");
		String clientOwner = policyInfor.getPolicyOwner();
		if (clientOwner.length()>0) {
			Client client = clientDao.findClient(clientOwner);
			if (client==null) {
				errors.rejectValue("policyOwner", "NotExist.policyForm.policyOwner");
			}
		}
		
		//Validate for Engine Number
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "engineNumber", "NotEmpty.policyForm.engineNumber");
		String engineNumber = policyInfor.getEngineNumber();
		MotoPolicy enginePolicy = policyDao.findPolicyByEngine(engineNumber);
		if (enginePolicy!=null) {
			if (enginePolicy.getPolicyNumber().equals(policyInfor.getPolicyNumber())==false) {
				errors.rejectValue("engineNumber", "Duplicate.policyForm.engineNumber");
			}
		}
		
		//Validate for Chassis Number
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "chassisNumber", "NotEmpty.policyForm.chassisNumber");
		String chassisNumber = policyInfor.getChassisNumber();
		MotoPolicy chassisPolicy = policyDao.findPolicyByChassis(chassisNumber);
		if (chassisPolicy!=null) {
			if (chassisPolicy.getPolicyNumber().equals(policyInfor.getPolicyNumber())==false) {
				errors.rejectValue("chassisNumber", "Duplicate.policyForm.chassisNumber");

			}
		}
		
		//Validate for Vehicle Registration Number
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vehicleRegisNumber", "NotEmpty.policyForm.vehicleRegisNumber");
		
		//Validate for Billing Currency
		String billingCurrency = policyInfor.getBillingCurrency();
		if (billingCurrency.equalsIgnoreCase("NONE")) {
			errors.rejectValue("billingCurrency", "NotEmpty.policyForm.billingCurrency");
		}
		
		//Validate for Sum Insured
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sumInsured", "NotEmpty.policyForm.sumInsured");
		String sumInsured = policyInfor.getSumInsured();
		if (sumInsured.length()>0) {
			try {
				double insured = Double.parseDouble(sumInsured);
				if (insured<0) {
					errors.rejectValue("sumInsured", "Greater.policyForm.sumInsured");
				}
			} catch (Exception e) {
				errors.rejectValue("sumInsured", "NotANumber.policyForm.sumInsured");
			}
		}
		
		//Validate for rate
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rate", "NotEmpty.policyForm.rate");
		String rateStr = policyInfor.getRate();
		if (rateStr.length()>0) {
			try {
				double rate = Double.parseDouble(rateStr);
				if (rate<0) {
					errors.rejectValue("rate", "Greater.policyForm.rate");
				}
			} catch (Exception e) {
				errors.rejectValue("rate", "NotANumber.policyForm.rate");
			}
		}
		
		
		//Validate for Premium
		ValidationUtils.rejectIfEmpty(errors, "annualPre", "NotEmpty.userForm.annualPre");
		ValidationUtils.rejectIfEmpty(errors, "postedPre", "NotEmpty.userForm.postedPre");
		
	}
	
	
}
