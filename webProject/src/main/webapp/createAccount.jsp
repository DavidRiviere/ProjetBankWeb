<%@page pageEncoding="UTF-8" contentType="text/html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${not empty error}">
		<c:out value="${error}" />
	</c:if>
	<form method="POST" action='<c:url value="/accounts"/>'>
		<label>Nom du compte</label> <input name="accountName"
			value="<c:out value="${param['accountName']}"/>"> <br /> <label>Numéro
			du compte</label> <input name="accountNumber"
			value="<c:out value="${param['accountNumber']}"/>"><br /> <label>solde
			initial</label> <input name="accountBalanceInteger"
			value="<c:out value="${param['accountBalanceInteger']}"/>">,<input
			name="accountBalanceFraction"
			value="<c:out value="${param['accountalanceFraction']}"/>" size="2"
			maxlength="2"> <br />
		<button type="submit">submit</button>
	</form>
</body>
</html>