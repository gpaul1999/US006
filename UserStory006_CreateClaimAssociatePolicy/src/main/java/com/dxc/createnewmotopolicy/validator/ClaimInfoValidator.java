package com.dxc.createnewmotopolicy.validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dxc.createnewmotopolicy.dao.MotoPolicyDAO;
import com.dxc.createnewmotopolicy.entity.MotoPolicy;
import com.dxc.createnewmotopolicy.model.ClaimInfo;

@Component
public class ClaimInfoValidator implements Validator{
	@Autowired
	private MotoPolicyDAO policyDao;
	
	public boolean supports(Class<?> clazz) {
		return clazz == ClaimInfo.class;
	}

	public void validate(Object target, Errors errors) {
		ClaimInfo claimInfo = (ClaimInfo) target;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		//Validate for Policy Number
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "policyNumber", "NotEmpty.claimForm.policyNumber");
		String policyNumber = claimInfo.getPolicyNumber();
		MotoPolicy policy = policyDao.findPolicyByCode(policyNumber);
		if (policyNumber.length()>0) {
			if (policy==null) {
				errors.rejectValue("policyNumber", "NotExist.claimForm.policyNumber");
			}else if (policy.getStatus().equals("PN")) {
				errors.rejectValue("policyNumber", "Status.claimForm.policyNumber");
			}
		}
		
		//Validate for Occurred Date
		ValidationUtils.rejectIfEmpty(errors, "occurredDate", "NotEmpty.claimForm.occuredDate");
		String occuredDate = claimInfo.getOccurredDate();
		
		if (occuredDate.length()>0) {
			try {
				Date occured = dateFormat.parse(occuredDate);
				if (occured.compareTo(new Date())>0) {
					errors.rejectValue("occurredDate", "Greater.claimForm.occuredDate");
				}	
				if (policy!=null) {
					Date inceptionDate = policy.getInceptionDate();
					Date expiryDate = policy.getExpiryDate();
					if (occured.compareTo(inceptionDate)<0 || occured.compareTo(expiryDate)>0) {
						errors.rejectValue("occurredDate", "NotInPeriod.claimForm.occuredDate");
					}
				}
			} catch (Exception e) {
				errors.rejectValue("occurredDate", "WrongFormat.claimForm.occuredDate");

			}
		}
		
		//Validate for Reserve Currency
		String reserveCurrency = claimInfo.getReserveCurrency();
		if (reserveCurrency.equalsIgnoreCase("NONE")) {
			errors.rejectValue("reserveCurrency", "NotEmpty.claimForm.reserveCurrency");
		}
		
		//Validate for Reserve Amount
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reserveAmount", "NotEmpty.claimForm.reserveAmount");
		String reserveAmount = claimInfo.getReserveAmount();
		if (reserveAmount.length()>0) {
			try {
				double reserve = Double.parseDouble(reserveAmount);
				if (reserve<0) {
					errors.rejectValue("reserveAmount", "Lower.claimForm.reserveAmount");
				}
				if (policy!=null) {
					double sumInsured = policy.getSumInsured();
					if (reserve>sumInsured) {
						errors.rejectValue("reserveAmount", "Greater.claimForm.reserveAmount");
					}
				}
			} catch (Exception e) {
				errors.rejectValue("reserveAmount", "NotANumber.claimForm.reserveAmount");
			}
		}
		
		
	}

}
