<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul id="title">
<li>Login</li>
</ul>

<c:if test="${not empty sessionScope.SPRING_SECURITY_SAVED_REQUEST_KEY}">
<p class="error">Please log in to access the page you requested.</p>
</c:if>

<form name="login" action="<c:url value='/login' />" method="post">
<table class="general" style="width: auto;">
  <tr>
    <th>Username:</th>
    <td><input type="text" name="username" class="forminput" /></td>
  </tr>
  <tr>
    <th>Password:</th>
    <td><input type="password" name="password" class="forminput" /></td>
  </tr>
  <tr>
    <td colspan="2">
      <input type="checkbox" name="remember-me" />
      Remember me on this computer.
    </td>
  </tr>
  <tr>
    <td colspan="2">
      <input class="submit" type="submit" name="login" value="Login" />
    </td>
  </tr>
</table>
</form>
