<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
			<form:input path="policyNumber" type="hidden" value="${policyInfor.policyNumber }" />
			<tr>
				<td>Inception Date *</td>
				<fmt:formatDate value="${policyInfor.inceptionDate }" type="date" pattern="dd/MM/yyyy" var="inDate"/>
				<td><form:input id ="inceptionDate" path="inceptionDate" readonly="true" value="${inDate }"/></td>
				<td><form:errors path="inceptionDate" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Expiration Date *</td>
				<fmt:formatDate value="${policyInfor.expiryDate }" type="date" pattern="dd/MM/yyyy" var="exDate"/>
				<td><form:input id ="expiryDate" path="expiryDate" readonly="true" value="${exDate }"/></td>
				<td><form:errors path="expiryDate" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Policy Owner Code *</td>
				<td><form:input path="policyOwner" readonly="true" value="${policyInfor.policyOwner }"/></td>
				<td><form:errors path="policyOwner" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Engine Number *</td>
				<td><form:input path="engineNumber" readonly="true" value="${policyInfor.engineNumber }"/></td>
				<td><form:errors path="engineNumber" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Chassis Number *</td>
				<td><form:input path="chassisNumber" readonly="true" value="${policyInfor.chassisNumber }"/></td>
				<td><form:errors path="chassisNumber" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Vehicle Registration Number *</td>
				<td><form:input path="vehicleRegisNumber" readonly="true" value="${policyInfor.vehicleRegisNumber }"/></td>
				<td><form:errors path="vehicleRegisNumber"
						class="error-message"></form:errors></td>
			</tr>
			<tr>
				<td>Billing Currency *</td>
				<td><form:input path="billingCurrency" readonly="true" value="${policyInfor.billingCurrency }"/></td>
				<td><form:errors path="billingCurrency" class="error-message"></form:errors></td>
			</tr>
			<tr>
				<td>Sum Insured *</td>
				<td><form:input id="sumInsured" path="sumInsured" value="${policyInfor.sumInsured }"/></td>
				<td><form:errors path="sumInsured" class="error-message" /></td>
			</tr>
			<tr>
				<td>Rate *</td>
				<td><form:input id="rate" path="rate" readonly="true" value="${policyInfor.rate }"/></td>
				<td><form:errors path="rate" class="error-message" /></td>
			</tr>
			<tr>
				<td>Annual Premium *</td>
				<td><form:input id="annualPre" path="annualPre" readonly="true" value="${policyInfor.annualPremium }"/></td>
				<td><form:errors path="annualPre" class="error-message" /></td>
			</tr>
			<tr>
				<td>Posted Premium *</td>
				<td><form:input id="postedPre" path="postedPre" readonly="true" value="${policyInfor.postedPremium }"/></td>
				<td><form:errors path="postedPre" class="error-message" /></td>
			</tr>
			<tr>
				<td>Status *</td>
				<c:if test="${policyInfor.status == 'PN' }">
    				<td><input readonly="true" value="Pending"/></td>
				</c:if> <c:if test="${policyInfor.status == 'IF' }">
    				<td><input readonly="true" value="In force"/></td>
				</c:if>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Submit"></td>
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