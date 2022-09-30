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
<title>User Story 006 - Create Claim</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/style.css" />" />
</head>
<body>
	<jsp:include page="header.jsp" />
	<jsp:include page="menu.jsp" />

	<div class="page-title">Edit Claim</div>
	<c:if test="${not empty errorMessage }">
		<div class="error-message">${errorMessage }</div>
	</c:if>

	<form:form modelAttribute="claimForm" method="POST"
		enctype="multipart/form-data">
		<table style="text-align: left;">
			<form:input path="claimNumber" type="hidden"
				value="${claimInfo.claimNumber }" />
			<tr>
				<td>Policy Number *</td>
				<td><form:input id="
				policyNumber" path="policyNumber"
						value="${claimInfo.policyNumber }" readonly="true" /></td>
				<td><form:errors path="policyNumber" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Occurred Date *</td>
				<fmt:formatDate value="${claimInfo.occurredDate }" type="date" pattern="dd/MM/yyyy" var="ocDate"/>
				<td><form:input id ="occurredDate" path="occurredDate" readonly="true" value="${ocDate }"/></td>
				<td><form:errors path="occurredDate" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Policy Owner *</td>
				<td><form:input id="policyOwner" path="policyOwner"
						readonly="true" value="${claimInfo.policyOwner }" /></td>
				<td><form:errors path="policyOwner" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Engine Number *</td>
				<td><form:input id="engineNumber" path="engineNumber"
						readonly="true" value="${claimInfo.engineNumber }" /></td>
				<td><form:errors path="engineNumber" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Chassis Number *</td>
				<td><form:input id="chassisNumber" path="chassisNumber"
						readonly="true" value="${claimInfo.chassisNumber }" /></td>
				<td><form:errors path="chassisNumber" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Vehicle Registration Number *</td>
				<td><form:input id="vehicleRegisNumber"
						path="vehicleRegisNumber" readonly="true"
						value="${claimInfo.vehicleRegisNumber }" /></td>
				<td><form:errors path="vehicleRegisNumber"
						class="error-message"></form:errors></td>
			</tr>
			<tr>
				<td>Sum Insured *</td>
				<td><form:input id="sumInsured" path="sumInsured"
						readonly="true" value="${claimInfo.sumInsured }" /></td>
				<td><form:errors path="sumInsured" class="error-message" /></td>
			</tr>
			<tr>
				<td>Reserve Currency *</td>
				<td><form:input path="reserveCurrency" readonly="true"
						value="${claimInfo.reserveCurrency }" /></td>
				<td><form:errors path="reserveCurrency" class="error-message"></form:errors></td>
			</tr>
			<tr>
				<td>Reserve Amount *</td>
				<td><form:input id="reserveAmount" path="reserveAmount"
						value="${claimInfo.reserveAmount }" /></td>
				<td><form:errors path="reserveAmount" class="error-message" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" name="save" value="Save"></td>
			</tr>
		</table>
	</form:form>



	<jsp:include page="footer.jsp" />

</body>
</html>