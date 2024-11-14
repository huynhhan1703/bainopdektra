/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.HoaDAO;
import dao.LoaiDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Hoa;

/**
 *
 * @author mygam
 */
@WebServlet(name = "ManageProduct", urlPatterns = {"/ManageProduct"})
@MultipartConfig
public class ManageProduct extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession();
        if(session.getAttribute("username")==null)
        {
        request.getRequestDispatcher("login.jsp").forward(request, response);}
        
        HoaDAO hoaDao = new HoaDAO();
        LoaiDAO loaiDao = new LoaiDAO();
        String method = request.getMethod();

        String action = "LIST";
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }
        switch (action) {
            case "LIST":
                //Trả về giao diện liệt kê danh sách sản phẩm
                //System.out.println("LIST");
                int pageSize = 5;
                int pageIndex = 1;
                if (request.getParameter("page") != null) {
                    pageIndex = Integer.parseInt(request.getParameter("page"));
                }
                int sumpage = (int) Math.ceil((double) hoaDao.getAll().size() / pageSize);

                request.setAttribute("dsHoa", hoaDao.getBypage(pageIndex, pageSize));
                request.setAttribute("sumpage", sumpage);
                request.setAttribute("pageIndex", pageIndex);
                request.getRequestDispatcher("admin/list_product.jsp").forward(request, response);
                break;
            case "ADD":
                //Trả về giao diện thêm mới
                //System.out.println("ADD");
                if (method.equals("GET")) {
                    //Trả về giao diện
                    request.setAttribute("dsLoai", loaiDao.getAll());//Chuyển dữ liệu cho jsp
                    request.getRequestDispatcher("admin/add_product.jsp").forward(request, response);
                } else if (method.equals("POST")) {
                    //Xử lý thêm mới
                    //B1. Lấy thông tin sản phẩm cần thêm
                    String tenhoa = request.getParameter("tenhoa");
                    double gia = Double.parseDouble(request.getParameter("gia"));
                    Part part = request.getPart("hinh");
                    int maloai = Integer.parseInt(request.getParameter("maloai"));
                    //B2.Xử lý upload file hình
                    String realPatch = request.getServletContext().getRealPath("assets/images/products");//Đường dẫn tuyệt đối
                    String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();//Tên 
                    part.write(realPatch + "/" + filename);
                    //Thêm sản phẩm vào cơ sở dữ liệu
                    Hoa objInsert = new Hoa(0, tenhoa, gia, filename, maloai, new Date(new java.util.Date().getTime()));
                    if (hoaDao.Insert(objInsert)) {
                        //Thông báo thêm thất bại
                        request.setAttribute("error", "Thêm sản phẩm thất bại");
                    } else {
                        //Thông báo thêm thành công
                        request.setAttribute("success", "Đã thêm sản phẩm thành công");
                    }
                    //Chuyển tiếp người dùng về action=LIST để liệt kê lại danh sách sản phẩm
                    request.getRequestDispatcher("ManageProduct?action=LIST").forward(request, response);
                }
                break;
            case "EDIT":
                //Trả về giao diện cập nhật sản phẩm
                if (method.equalsIgnoreCase("get")) {
                    int mahoa = Integer.parseInt(request.getParameter("mahoa"));
                    request.setAttribute("hoa", hoaDao.getById(mahoa));
                    request.setAttribute("dsLoai", loaiDao.getAll());
                    request.getRequestDispatcher("admin/edit_product.jsp").forward(request, response);
                } else if (method.equalsIgnoreCase("post")) {
                    int mahoa = Integer.parseInt(request.getParameter("mahoa"));
                    String tenhoa = request.getParameter("tenhoa");
                    double gia = Double.parseDouble(request.getParameter("gia"));
                    Part part = request.getPart("hinh");
                    int maloai = Integer.parseInt(request.getParameter("maloai"));
                    String filename = request.getParameter("oldImg");
                    //B2.Xử lý upload file hình(nếu có)
                    if (part.getSize() > 0) {
                        String realPatch = request.getServletContext().getRealPath("assets/images/products");//Đường dẫn tuyệt đối
                        filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();//Tên 
                        part.write(realPatch + "/" + filename);
                    }
                    //Thêm sản phẩm vào cơ sở dữ liệu
                    Hoa objUpdate = new Hoa(mahoa, tenhoa, gia, filename, maloai, new Date(new java.util.Date().getTime()));
                    if (hoaDao.Update(objUpdate)) {
                        //Thông báo thêm thất bại
                        request.setAttribute("error", "Cập nhật sản phẩm thất bại");
                    } else {
                        //Thông báo thêm thành công
                        request.setAttribute("success", "Đã cập nhật sản phẩm thành công");
                    }
                    //Chuyển tiếp người dùng về action=LIST để liệt kê lại danh sách sản phẩm
                    request.getRequestDispatcher("ManageProduct?action=LIST").forward(request, response);
                }
                break;
            case "DELETE":
                //Trả về giao diện xóa sản phẩm
                //b1. Lấy mã sản phẩm 
                int mahoa = Integer.parseInt(request.getParameter("mahoa"));
                //b2.Xóa sản phẩm
                if (hoaDao.Delete(mahoa)) {
                    //Thông báo thêm thất bại
                    request.setAttribute("error", "Xóa sản phẩm thất bại");
                } else {
                    //Thông báo thêm thành công

                    request.setAttribute("success", "Xóa sản phẩm Thành công");
                }
                //Chuyển tiếp người dùng về action=LIST để liệt kê lại danh sách sản phẩm
                request.getRequestDispatcher("ManageProduct?action=LIST").forward(request, response);
                break;
        }

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManageProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageProduct at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
