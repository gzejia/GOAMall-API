package com.nuon.goamall.api.v1;

import com.nuon.goamall.core.LocalUser;
import com.nuon.goamall.core.UnifyResponse;
import com.nuon.goamall.core.interceptors.ScopeLevel;
import com.nuon.goamall.enumeration.CouponStatus;
import com.nuon.goamall.exception.ParameterException;
import com.nuon.goamall.model.Coupon;
import com.nuon.goamall.service.CouponServiceImpl;
import com.nuon.goamall.vo.CouponPureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/coupon")
@Validated
public class CouponController {

    @Autowired
    CouponServiceImpl couponService;

    /**
     * @param cid 二级分类ID
     * @return 获取分类对应的优惠券
     */
    @GetMapping("/by/category/{cid}")
    public List<CouponPureVO> getCouponListByCategory(@PathVariable Long cid) {
        List<Coupon> coupons = this.couponService.getByCategory(cid);
        if (coupons.isEmpty()) {
            return Collections.emptyList();
        }
        List<CouponPureVO> vos = CouponPureVO.getList(coupons);
        return vos;
    }

    /**
     * @return 获取全场通用券
     */
    @GetMapping("/whole_store")
    public List<CouponPureVO> getWholeStoreCouponList() {
        List<Coupon> coupons = this.couponService.getWholeStoreCoupons();
        if (coupons.isEmpty()) {
            return Collections.emptyList();
        }
        return CouponPureVO.getList(coupons);
    }

    /**
     * 收藏优惠券
     *
     * @param id 优惠券ID
     */
    @ScopeLevel()
    @PostMapping("/collect/{id}")
    public void collectCoupon(@PathVariable Long id) {
        Long uid = LocalUser.getUser().getId();
        couponService.collectOneCoupon(uid, id);
        UnifyResponse.createSuccess(0);
    }

    @ScopeLevel
    @GetMapping("/myself/by/status/{status}")
    public List<CouponPureVO> getMyCouponByStatus(@PathVariable Integer status) {
        Long uid = LocalUser.getUser().getId();
        List<Coupon> couponList;

        //触发机制 时机 过期
        CouponStatus statusValue = CouponStatus.toType(status);
        if (null == statusValue) {
            throw new ParameterException(40001);
        }

        switch (statusValue) {
            case AVAILABLE:
                couponList = couponService.getMyAvailableCoupons(uid);
                break;
            case USED:
                couponList = couponService.getMyUsedCoupons(uid);
                break;
            case EXPIRED:
                couponList = couponService.getMyExpiredCoupons(uid);
                break;
            default:
                throw new ParameterException(40001);
        }
        return CouponPureVO.getList(couponList);
    }


}
