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
<title>User Story 006 - Create Claim</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/style.css" />" />
</head>
<body>
	<jsp:include page="header.jsp" />
	<jsp:include page="menu.jsp" />

	<div class="page-title">Current Claim List</div>

	<table border="1" style="width: 100%">
		<tr>
			<th>Claim Number</th>
			<th>Policy Number</th>
			<th>Date Occurred</th>
			<th>Policy Owner</th>
			<th>Engine Number</th>
			<th>Chassis Number</th>
			<th>Vehicle Registration Number</th>
			<th>Sum Insured</th>
			<th>Reserve Currency</th>
			<th>Reserve Amount</th>
			<th>Status</th>
		</tr>
		<c:forEach items="${listClaim }" var="lsClaim">
			<tr>
				<th>${lsClaim.claimNumber }</th>
				<th>${lsClaim.policyNumber }</th>
				<th>${lsClaim.occurredDate }</th>
				<th>${lsClaim.policyOwner }</th>
				<th>${lsClaim.engineNumber }</th>
				<th>${lsClaim.chassisNumber }</th>
				<th>${lsClaim.vehicleRegisNumber }</th>
				<th>${lsClaim.sumInsured }</th>
				<th>${lsClaim.reserveCurrency }</th>
				<th>${lsClaim.reserveAmount }</th>
				<th>${lsClaim.status }</th>
				<c:if test="${lsClaim.status == 'Pending' }">
					<th><security:authorize access="hasRole('ROLE_ADMIN')">
						<a
							href="${pageContext.request.contextPath}/edit_claim?claimNumber=${lsClaim.claimNumber }">
							Modify </a>
					</security:authorize></th>
				</c:if>
				<c:if test="${lsClaim.status == 'Pending' }">
					<th><security:authorize access="hasRole('ROLE_ADMIN')">
						<a
							href="${pageContext.request.contextPath}/approve_claim?claimNumber=${lsClaim.claimNumber }">
							Approve </a>
					</security:authorize></th>
				</c:if>
				
				
			</tr>
		</c:forEach>
	</table>

	<jsp:include page="footer.jsp" />
</body>
</html>