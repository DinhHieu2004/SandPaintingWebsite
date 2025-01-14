package com.example.web.service;

import com.example.web.dao.ReviewDao;
import com.example.web.dao.model.ProductReview;

public class PrivewService {
    private ReviewDao reviewDao = new ReviewDao();


    public boolean canReview(int userId, int paintingId, int orderItemId) throws Exception {
        return !reviewDao.hasReviewed(userId, paintingId, orderItemId);
    }

    public void submitReview(ProductReview review) throws Exception {
        if (canReview(review.getUserId(), review.getPaintingId(), review.getOrderItemId())) {
            reviewDao.addReview(review);
        } else {
            throw new Exception("Bạn đã đánh giá sản phẩm này từ đơn hàng này.");
        }
    }
}
