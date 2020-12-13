package com.nuon.goamall.service;

import com.nuon.goamall.model.Coupon;

import java.util.List;

public interface CouponService {

    /**
     * @param cid 分类ID
     * @return 根据分类获取优惠券
     */
    List<Coupon> getByCategory(Long cid);

    /**
     * @return 获取全场痛用优惠券
     */
    List<Coupon> getWholeStoreCoupons();

    /**
     * 领取优惠券
     *
     * @param uid      用户ID
     * @param couponId 优惠券ID
     */
    void collectOneCoupon(Long uid, Long couponId);
}
