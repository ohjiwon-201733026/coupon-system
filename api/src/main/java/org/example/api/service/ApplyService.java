package org.example.api.service;

import org.example.api.domain.Coupon;
import org.example.api.repository.CouponCountRepository;
import org.example.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;

    public ApplyService(CouponRepository couponRepository, CouponCountRepository couponCountRepository) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
    }

    public void apply(Long userId) {
        long count = couponCountRepository.increment();

        if(count > 100) {
            System.out.println("count > 100 ");
            return;
        }

        System.out.println("Send message : " + userId);
        couponRepository.save(new Coupon(userId));
    }
}
