<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
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

	<div class="page-title">Current Moto Policy List</div>

	<table border="1" style="width: 100%">
		<tr>
			<th>Policy Number</th>
			<th>Policy Owner</th>
			<th>Vehicle Registration Number</th>
			<th>Sum Insured</th>
			<th>Annual Premium</th>
			<th>Posted Premium</th>
			<th>Billing Currency</th>
			<th>Status</th>
		</tr>
		<c:forEach items="${listMotoPolicy }" var="lsPolicy">
			<tr>
				<th>${lsPolicy.policyNumber }</th>
				<th>${lsPolicy.policyOwner }</th>
				<th>${lsPolicy.vehicleRegisNumber }</th>
				<th>${lsPolicy.sumInsured }</th>
				<th>${lsPolicy.annualPremium }</th>
				<th>${lsPolicy.postedPremium }</th>
				<th>${lsPolicy.billingCurrency }</th>
				<c:if test="${lsPolicy.status == 'PN' }">
					<th>Pending</th>
				</c:if>
				<c:if test="${lsPolicy.status == 'IF' }">
					<th>In force</th>
				</c:if>
				<th><a
					href="${pageContext.request.contextPath}/policy_info?policyNumber=${lsPolicy.policyNumber}">
						Inquire</a></th>
				<c:if test="${lsPolicy.status == 'PN' }">
					<th><security:authorize access="hasRole('ROLE_ADMIN')">
						<a
							href="${pageContext.request.contextPath}/edit_policy?policyNumber=${lsPolicy.policyNumber}">
							Modify </a>
					</security:authorize></th>
				</c:if>
				<c:if test="${lsPolicy.status == 'PN' }">
					<th><security:authorize access="hasRole('ROLE_ADMIN')">
						<a
							href="${pageContext.request.contextPath}/issue_policy?policyNumber=${lsPolicy.policyNumber}">
							Issue </a>
					</security:authorize></th>
				</c:if>
				
				
			</tr>
		</c:forEach>
	</table>

	<jsp:include page="footer.jsp" />
</body>
</html>