<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="${pageContext.request.contextPath }/">
<title>User Story 003 - Create New Moto Policy</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/style.css" />" />
</head>
<body>
	<jsp:include page="header.jsp" />
	<jsp:include page="menu.jsp" />

	<div class="page-title">Add New Moto Policy</div>
	<c:if test="${not empty errorMessage }">
		<div class="error-message">${errorMessage }</div>
	</c:if>

	<form:form modelAttribute="policyForm" method="POST"
		enctype="multipart/form-data">
		<table style="text-align: left;">
			<form:input path="policyNumber" type="hidden" />
			<tr>
				<td>Inception Date *</td>
				<td><form:input id ="inceptionDate" path="inceptionDate" /></td>
				<td><form:errors path="inceptionDate" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Expiration Date *</td>
				<td><form:input id="expiryDate" path="expiryDate" /></td>
				<td><form:errors path="expiryDate" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Policy Owner Code *</td>
				<td><form:input path="policyOwner" /></td>
				<td><form:errors path="policyOwner" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Engine Number *</td>
				<td><form:input path="engineNumber" /></td>
				<td><form:errors path="engineNumber" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Chassis Number *</td>
				<td><form:input path="chassisNumber" /></td>
				<td><form:errors path="chassisNumber" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Vehicle Registration Number *</td>
				<td><form:input path="vehicleRegisNumber" /></td>
				<td><form:errors path="vehicleRegisNumber"
						class="error-message"></form:errors></td>
			</tr>
			<tr>
				<td>Billing Currency *</td>
				<td><form:select path="billingCurrency">
						<form:option value="NONE">Select</form:option>
						<form:options items="${lsCurrency }"></form:options>
					</form:select></td>
				<td><form:errors path="billingCurrency" class="error-message"></form:errors></td>
			</tr>
			<tr>
				<td>Sum Insured *</td>
				<td><form:input id="sumInsured" path="sumInsured" /></td>
				<td><form:errors path="sumInsured" class="error-message" /></td>
			</tr>
			<tr>
				<td>Rate *</td>
				<td><form:input id="rate" path="rate" /></td>
				<td><form:errors path="rate" class="error-message" /></td>
			</tr>
			<tr>
				<td>Annual Premium *</td>
				<td><form:input id="annualPre" path="annualPre" readonly="true" /></td>
				<td><form:errors path="annualPre" class="error-message" /></td>
			</tr>
			<tr>
				<td>Posted Premium *</td>
				<td><form:input id="postedPre" path="postedPre" readonly="true" /></td>
				<td><form:errors path="postedPre" class="error-message" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" name="save" value="Save"><input type="submit" name="issue" value="Issue"></td>
				<td><button id="premiumBtn" type="button" onclick="calculate()">Refresh</button></td>

			</tr>
		</table>
	</form:form>



	<jsp:include page="footer.jsp" />

	<script type="text/javascript">
		var annualPre = document.getElementById("annualPre");
		var postedPre = document.getElementById("postedPre");
		function calculate(){
			//get value from FORM
			let sum = document.getElementById("sumInsured").value;
			let rate = document.getElementById("rate").value;
			let inceptionDate = document.getElementById("inceptionDate").value;
			let expiryDate = document.getElementById("expiryDate").value;
			// Calculate date value
			let inDate = convertFromStringToDate(inceptionDate).getTime();
			let exDate = convertFromStringToDate(expiryDate).getTime();
			let dateCount = Math.ceil((exDate - inDate) / (24*60*60*1000));
			//Calculate Premium value
			annualPre.value = sum*rate/100;
			postedPre.value = sum*rate/100*dateCount/365;
		}
		function convertFromStringToDate(responseDate) {
		    let dateComponents = responseDate.split('/');
		    return(new Date(dateComponents[2], (dateComponents[1]-1), dateComponents[0]))
		}
	</script>
</body>
</html>