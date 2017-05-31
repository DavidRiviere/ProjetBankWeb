<%@page pageEncoding="UTF-8" contentType="text/html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404</title>
</head>
<body>
	<p>La page n'existe plus</p>
	<nav>
		<ul>
			<li><a href="<c:url value="/"/>">créer un nouveau compte</a></li>
		</ul>
	</nav>
</body>
</html>