<%@page pageEncoding="UTF-8" contentType="text/html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>account</title>
</head>
<body>
	<section>
		<c:out value="${account.name}" />
		&nbsp;:
		<fmt:formatNumber type="currency"
			currencySymbol="${account.balanceAmount.currency.symbol}"
			value="${account.balanceAmount.valueWithFractionDigits}" />
	</section>
	<nav>
		<ul>
			<li><a href="<c:url value="/"/>">créer un nouveau compte</a></li>
		</ul>
	</nav>
</body>
</html>