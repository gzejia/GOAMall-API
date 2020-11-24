package com.nuon.goamall.service;

import com.nuon.goamall.model.Coupon;

import java.util.List;

public interface CouponService {

    List<Coupon> getByCategory(Long cid);

    List<Coupon> getWholeStoreCoupons();
}
