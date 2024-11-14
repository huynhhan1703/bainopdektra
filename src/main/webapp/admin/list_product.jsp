<%-- 
    Document   : list_product
    Created on : Oct 22, 2024, 2:11:45 PM
    Author     : ADMIN
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Hoa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="../shared/header.jsp" />

<jsp:include page="../shared/nav.jsp" />

<jsp:include page="../shared/notification.jsp"/>


<style>.container h2{
        margin:10px 0 -10px 0;
        color: red;
        font-weight: bold;

    }</style>
<div class="container">
    <h2 > Danh sách sản phẩm</h2>
    <div class="mb-2 text-end">
        <a href="ManageProduct?action=ADD" class="btn btn-success"> <i class="bi bi-plus-circle"></i> Thêm mới</a>
    </div>

    <table class="table table-bordered table-striped">
        <tr>
            <th>Tên hoa</th>
            <th>Giá</th>
            <th>Hình ảnh</th>
            <th>Loại</th>
            <th>Action</th>
        </tr>  
        <%
            DecimalFormat fmt = new DecimalFormat("#,##0");
            ArrayList<Hoa> dsHoa = (ArrayList<Hoa>) request.getAttribute("dsHoa");
            for (Hoa x : dsHoa) {
        %>
        <tr>
            <td><%=x.getTenhoa()%></td>
            <td><%=fmt.format(x.getGia())%></td>
            <td> <img src="assets/images/products/<%=x.getHinh()%>" style="width: 100px">  </td>
            <td><%=x.getMaloai()%></td>
            <td>
                <a href="ManageProduct?action=EDIT&mahoa=<%=x.getMahoa()%>" class="btn btn-secondary"> <i class="bi bi-pencil-square"></i> Sửa</a>
                <a href="ManageProduct?action=DELETE&mahoa=<%=x.getMahoa()%>" class="btn btn-danger"
                   onclick="return confirm('Bạn có đồng ý xóa không ?')"

                   > <i class="bi bi-trash"></i> Xoá</a>
            </td>
        </tr>          
        <%
            }
        %>
    </table>
    <style>
        .pagination .page-link {
            background-color: white; /* Change this to your desired color */
            text-decoration: none; /* Remove underline */
        }
        .pagination .page-item.active .page-link {
            color: white; /* Change active page color */
            background-color: gray; /* Background color for active page */
        }
    </style>
    
    <ul class="pagination justify-content-center">
        <%
            int pagesum = (int) request.getAttribute("sumpage");
            int pageIndex = (int) request.getAttribute("pageIndex");
        %>
        <li class="page-item <%=pageIndex == 1 ? "disable" : ""%>"><a class="page-link" href="ManageProduct?page=1">First</a></li>
        <li class="page-item <%=pageIndex == 1 ? "disable" : ""%>"><a class="page-link" href="ManageProduct?page=<%=pageIndex == pagesum ? pageIndex - 1 : pageIndex%>">Previous</a></li>

        <%
            for (int i = 1; i <= pagesum; i++) {
        %>
        <li class="page-item <%=pageIndex == i ? "active" : ""%> " ><a class="page-link" href="ManageProduct?page=<%=i%>"><%=i%></a></li> 
            <%
                }
            %>
        <li class="page-item <%=pageIndex == pagesum ? "disable" : ""%>"><a class="page-link" href="ManageProduct?page=<%=pageIndex < pagesum ? pageIndex + 1 : pageIndex%>">Next</a></li>
        <li class="page-item <%=pageIndex == pagesum ? "disable" : ""%>"><a class="page-link" href="ManageProduct?page=<%=pagesum%>">LAST</a></li>

    </ul>

</div>

<jsp:include page="../shared/footer.jsp" />