<%-- 
    Document   : notification
    Created on : Oct 28, 2024, 9:00:53 AM
    Author     : mygam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getAttribute("success") != null) {

%>
<script>
    Swal.fire({
        title: "Good job!",
        text: "<%=request.getAttribute("success") %>",
        icon: "success"
    });
</script>
<%
    }
%>

<%
    if (request.getAttribute("error") != null) {

%>
<script>
    Swal.fire({
        title: "Ohh!",
        text: "<%=request.getAttribute("error") %>",
        icon: "error"
    });
</script>
<%
    }
%>
