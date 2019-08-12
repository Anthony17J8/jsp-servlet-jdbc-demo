<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="theLocale"
       value="${not empty param.theLocale ? param.theLocale :pageContext.request.locale}"
       scope="session"/>

<fmt:setLocale value="${theLocale}"/>

<fmt:setBundle basename="i18n.mylabels"/>

<html>

<body>

<a href="i18n-messages-test.jsp?theLocale=en_US">English (US)</a>
|
<a href="i18n-messages-test.jsp?theLocale=es_ES">Spanish (ES)</a>
|
<a href="i18n-messages-test.jsp?theLocale=en_US">German (DE)</a>

<hr>
<fmt:message key="label.greeting"/> <br/>

<fmt:message key="label.firstname"/><i>John</i> <br/>

<fmt:message key="label.lastname"/> <i>Doe</i> <br/>

<fmt:message key="label.welcome"/> <br/>
<hr>

Selected locale: ${theLocale}

</body>

</html>