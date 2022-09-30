<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

	<div class="page-title">Add New Claim</div>
	<c:if test="${not empty errorMessage }">
		<div class="error-message">${errorMessage }</div>
	</c:if>

	<form:form modelAttribute="claimForm" method="POST"
		enctype="multipart/form-data">
		<table style="text-align: left;">
			<form:input path="claimNumber" type="hidden" />
			<tr>
				<td>Policy Number *</td>
				<td><form:input id="policyNumber" path="policyNumber"
						onchange="getPolicyInfo()" value="${policyInfo.policyNumber }" /></td>
				<td><form:errors path="policyNumber" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Occurred Date *</td>
				<td><form:input id="occurredDate" path="occurredDate" /></td>
				<td><form:errors path="occurredDate" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Policy Owner *</td>
				<td><form:input id="policyOwner" path="policyOwner" readonly="true" value="${policyInfo.policyOwner }" /></td>
				<td><form:errors path="policyOwner" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Engine Number *</td>
				<td><form:input id="engineNumber" path="engineNumber" readonly="true" value="${policyInfo.engineNumber }" /></td>
				<td><form:errors path="engineNumber" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Chassis Number *</td>
				<td><form:input id="chassisNumber" path="chassisNumber" readonly="true" value="${policyInfo.chassisNumber }" /></td>
				<td><form:errors path="chassisNumber" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Vehicle Registration Number *</td>
				<td><form:input id="vehicleRegisNumber" path="vehicleRegisNumber" readonly="true" value="${policyInfo.vehicleRegisNumber }" /></td>
				<td><form:errors path="vehicleRegisNumber"
						class="error-message"></form:errors></td>
			</tr>
			<tr>
				<td>Sum Insured *</td>
				<td><form:input id="sumInsured" path="sumInsured"
						readonly="true" value="${policyInfo.sumInsured }" /></td>
				<td><form:errors path="sumInsured" class="error-message" /></td>
			</tr>
			<tr>
				<td>Reserve Currency *</td>
				<td><form:select path="reserveCurrency">
						<form:option value="NONE">Select</form:option>
						<form:options items="${lsCurrency }"></form:options>
					</form:select></td>
				<td><form:errors path="reserveCurrency" class="error-message"></form:errors></td>
			</tr>
			<tr>
				<td>Reserve Amount *</td>
				<td><form:input id="reserveAmount" path="reserveAmount" /></td>
				<td><form:errors path="reserveAmount" class="error-message" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" name="save" value="Save"><input
					type="submit" name="approve" value="Approve"><input id="refresh" type="submit" name="refresh" hidden></td>
			</tr>
		</table>
	</form:form>



	<jsp:include page="footer.jsp" />

	<script type="text/javascript">
		function getPolicyInfo() {
			document.getElementById("policyOwner").value = "";
			document.getElementById("engineNumber").value = "";
			document.getElementById("chassisNumber").value = "";
			document.getElementById("vehicleRegisNumber").value = "";
			document.getElementById("sumInsured").value = "";
			document.getElementById("refresh").click();
			document.getElementById("refresh").focus();
		}
	</script>
</body>
</html>