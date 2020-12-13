package com.nuon.goamall.service;

import com.nuon.goamall.enumeration.CouponStatus;
import com.nuon.goamall.exception.NotFoundException;
import com.nuon.goamall.exception.ParameterException;
import com.nuon.goamall.model.Activity;
import com.nuon.goamall.model.Coupon;
import com.nuon.goamall.model.UserCoupon;
import com.nuon.goamall.repository.ActivityRepository;
import com.nuon.goamall.repository.CouponRepository;
import com.nuon.goamall.repository.UserCouponRepository;
import com.nuon.goamall.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CouponServiceImpl implements CouponService{

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    UserCouponRepository userCouponRepository;

    @Override
    public List<Coupon> getByCategory(Long cid) {
        Date now = new Date();
        return couponRepository.findByCategory(cid, now);
    }

    public List<Coupon> getWholeStoreCoupons() {
        Date now = new Date();
        return couponRepository.findByWholeStore(true, now);
    }

    public List<Coupon> getMyAvailableCoupons(Long uid) {
        Date now = new Date();
        return this.couponRepository.findMyAvailable(uid, now);
    }

    public List<Coupon> getMyUsedCoupons(Long uid) {
        Date now = new Date();
        return this.couponRepository.findMyUsed(uid, now);
    }

    public List<Coupon> getMyExpiredCoupons(Long uid) {
        Date now = new Date();
        return this.couponRepository.findMyExpired(uid, now);
    }

    @Override
    public void collectOneCoupon(Long uid, Long couponId){
        this.couponRepository
                .findById(couponId)
                .orElseThrow(() -> new NotFoundException(40003));

        Activity activity = this.activityRepository
                .findByCouponListId(couponId)
                .orElseThrow(() -> new NotFoundException(40010));

        Date now = new Date();
        Boolean isIn = CommonUtil.isInTimeLine(now, activity.getStartTime(), activity.getEndTime());
        if(!isIn){
            throw new ParameterException(40005);
        }

        this.userCouponRepository
                .findFirstByUserIdAndCouponId(uid, couponId)
                .ifPresent((uc)-> {throw new ParameterException(40006);});

        UserCoupon userCouponNew = UserCoupon.builder()
                .userId(uid)
                .couponId(couponId)
                .status(CouponStatus.AVAILABLE.getValue())
                .createTime(now)
                .build();
        userCouponRepository.save(userCouponNew);
    }
}
