<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

<body>

<c:set var="data" value="Hello World!"/>

Length of the string <b>${data}</b>: ${fn:length(data)}

<br/><br/>

Uppercase version of the string  <b>${data}</b>: ${fn:toUpperCase(data)}

<br/><br/>

Does the string <b>${data}</b> start with <b>Hel</b>? : ${fn:startsWith(data, "Hel")}
</body>

</html>