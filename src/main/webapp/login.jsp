<%-- 
    Document   : login
    Created on : Nov 4, 2024, 10:54:26 AM
    Author     : ADMIN
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="shared/header.jsp" />
<jsp:include page="shared/nav.jsp" />
<div class="container">
    <form action="Login" method="post">
        <h2>Login System</h2>
        <div>
            <label>User name</label>
            <input type="text" name="username" required="" class="form-control">
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="password" required="" class="form-control">
        </div>
        <div class="mt-2">
            <button type="submit" class="btn btn-primary">Login</button>
        </div>
        <%
            if (request.getAttribute("error") != null) {
        %>
        <div class="text-danger mb-3">
            <%=request.getAttribute("error")%>
        </div>
        <%
            }
        %>
    </form>
</div>
<jsp:include page="shared/footer.jsp" />