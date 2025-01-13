package com.example.web.controller.admin.discountController;

import com.example.web.dao.DiscountDao;
import com.example.web.dao.model.Discount;
import com.example.web.service.DiscountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@WebServlet("/admin/discounts/add")
public class AddDiscount extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String discountName = request.getParameter("discountName");
        double discountPercentage = Double.parseDouble(request.getParameter("discountPercentage"));
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String imageUrl = request.getParameter("imageUrl");

        // Xử lý dữ liệu: Nếu imageUrl rỗng thì gán giá trị mặc định
        if (imageUrl == null || imageUrl.isEmpty()) {
            imageUrl = "default-image.png"; // Gán ảnh mặc định nếu không cung cấp
        }

        // Chuyển đổi ngày tháng
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        // Tạo đối tượng Discount và lưu vào cơ sở dữ liệu
        Discount discount = new Discount();
        discount.setDiscountName(discountName);
        discount.setDiscountPercentage(BigDecimal.valueOf(discountPercentage));
        discount.setStartDate(start);
        discount.setEndDate(end);
        discount.setImageUrl(imageUrl);

        // Gọi phương thức để lưu vào cơ sở dữ liệu
        DiscountService service = new DiscountService();
        boolean isAdded = service.addDiscount(discount);

        // Kiểm tra xem việc thêm giảm giá có thành công không
        if (isAdded) {
            // Điều hướng lại trang danh sách giảm giá và tải lại danh sách
            response.sendRedirect(request.getContextPath() + "/admin/discount");
        } else {
            // Thông báo lỗi nếu thêm không thành công
            request.setAttribute("errorMessage", "Có lỗi xảy ra khi thêm chương trình giảm giá!");
            request.getRequestDispatcher("/admin/discount").forward(request, response);
        }
    }
}


