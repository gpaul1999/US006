<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

	<div class="page-title">Policy Information</div>
	<c:if test="${not empty errorMessage }">
		<div class="error-message">${errorMessage }</div>
	</c:if>

	<table style="text-align: left;">
		<tr>
			<td>Policy No</td>
			<td><a>${policyInfor.policyNumber }</a></td>
		</tr>
		<tr>
			<td>Inception Date</td>
			<td><a>${policyInfor.inceptionDate }</a></td>
		</tr>
		<tr>
			<td>Expiration Date</td>
			<td><a>${policyInfor.expiryDate }</a></td>
		</tr>
		<tr>
			<td>Policy Owner</td>
			<td><a>${policyInfor.policyOwner }</a></td>
		</tr>
		<tr>
			<td>Engine No</td>
			<td><a>${policyInfor.engineNumber }</a></td>
		</tr>
		<tr>
			<td>Chassis No</td>
			<td><a>${policyInfor.chassisNumber }</a></td>
		</tr>
		<tr>
			<td>Vehicle Registration No</td>
			<td><a>${policyInfor.vehicleRegisNumber }</a></td>
		</tr>
		<tr>
			<td>Billing Currency</td>
			<td><a>${policyInfor.billingCurrency }</a></td>
		</tr>
		<tr>
			<td>Sum Insured</td>
			<td><a>${policyInfor.sumInsured }</a></td>
		</tr>
		<tr>
			<td>Rate</td>
			<td><a>${policyInfor.rate }</a></td>
		</tr>
		<tr>
			<td>Annual Premium</td>
			<td><a>${policyInfor.annualPremium }</a></td>
		</tr>
		<tr>
			<td>Posted Premium</td>
			<td><a>${policyInfor.postedPremium }</a></td>
		</tr>
		<tr>
			<td>Status</td>
			<c:if test="${policyInfor.status == 'PN' }">
				<td>Pending</td>
			</c:if>
			<c:if test="${policyInfor.status == 'IF' }">
				<td>In force</td>
			</c:if>
		</tr>
		<tr>
			<td><c:if test="${policyInfor.status == 'PN' }">
					<th><security:authorize access="hasRole('ROLE_ADMIN')">
							<a
								href="${pageContext.request.contextPath}/edit_policy?policyNumber=${policyInfor.policyNumber}">
								Modify </a>
						</security:authorize></th>
				</c:if></td>
			<td><c:if test="${policyInfor.status == 'PN' }">
					<th><security:authorize access="hasRole('ROLE_ADMIN')">
							<a
								href="${pageContext.request.contextPath}/issue_policy?policyNumber=${policyInfor.policyNumber}">
								Issue </a>
						</security:authorize></th>
				</c:if></td>
		</tr>
	</table>



	<jsp:include page="footer.jsp" />
</body>
</html>