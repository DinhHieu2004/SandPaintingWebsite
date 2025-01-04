package com.example.web.service;

import com.example.web.dao.OrderDao;
import com.example.web.dao.OrderItemDao;
import com.example.web.dao.PaintingDao;
import com.example.web.dao.PaymentDao;
import com.example.web.dao.cart.Cart;
import com.example.web.dao.cart.CartPainting;
import com.example.web.dao.model.Order;
import com.example.web.dao.model.OrderItem;
import com.example.web.dao.model.Payment;
import java.time.LocalDateTime;

public class CheckoutService {
    private OrderDao orderDao;
    private OrderItemDao orderItemDao;
    private PaymentDao paymentDao;
    private PaintingDao paintingDao;

    public CheckoutService(){
        orderDao = new OrderDao();
        orderItemDao = new OrderItemDao();
        paymentDao = new PaymentDao();
        paintingDao = new PaintingDao();

    }
    public void processCheckout(Cart cart, int userId, int paymentMethodId) throws Exception {

        Order order = new Order();
        order.setUserId(userId);
        order.setTotalAmount(cart.getTotalPrice());
        order.setStatus("chờ");
        int orderId = orderDao.createOrder(order);

        for (CartPainting item : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setPaintingId(item.getProductId());
            orderItem.setSizeId(item.getSizeId());
            orderItem.setPrice(item.getTotalPrice());
            orderItemDao.addOrderItem(orderItem);
            paintingDao.updateQuanity(item.getProductId(), item.getSizeId(),item.getQuantity());

        }

        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setUserId(userId);
        payment.setMethodId(paymentMethodId);
        payment.setPaymentStatus(paymentMethodId == 1 ? "đã thanh toán" : "chờ");
        payment.setPaymentDate(LocalDateTime.now());
        paymentDao.createPayment(payment);
    }

    public static void main(String[] args) throws Exception {
        CartPainting c1 = new CartPainting(1, "tranh canh bien","1","20x60 cm",2,300000.0,"vbdrbvkj");
        CartPainting c2 = new CartPainting(2, "tranh canh bien","3","20x60 cm",2,300000.0,"vbdrbvkj");

        Cart cart = new Cart();
        cart.addToCart(c1);
        cart.addToCart(c2);

        CheckoutService service = new CheckoutService();
        service.processCheckout(cart,1, 1);

    }

}
