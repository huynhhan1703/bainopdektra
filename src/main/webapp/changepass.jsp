<%-- 
    Document   : list.jsp
    Created on : Sep 15, 2023, 8:14:39 AM
    Author     : KHOACNTT
--%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Hoa"%>
<%@page import="dao.HoaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--nhung noi dung header.jsp-->
<jsp:include page="shared/header.jsp" />
<!--nhung noi dung nav.jsp-->
<jsp:include page="shared/nav.jsp" />
<div class="container">
     <h2>Change Password</h2>
    <form action="ChangePassServlet" method="post">
        <div>
            <label>Old Password</label>
            <input type="password" name="oldpassword" required="" class="form-control">
        </div>
        <div>
            <label>New Password</label>
            <input type="password" name="newpassword" required="" class="form-control">
        </div>
        <div>
            <label>Confirm Password</label>
            <input type="password" name="confirmpassword" required="" class="form-control">
        </div>
        <div class="mt-2">
            <button type="submit" class="btn btn-primary">Change</button>
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
</div><!-- /.container -->
<!--nhung noi dung footer.jsp-->
<jsp:include page="shared/footer.jsp" />   
