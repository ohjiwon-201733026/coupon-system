package org.example.api.service;

import org.example.api.domain.Coupon;
import org.example.api.producer.CouponCreateProducer;
import org.example.api.repository.AppliedUserRepository;
import org.example.api.repository.CouponCountRepository;
import org.example.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer couponCreateProducer;
    private final AppliedUserRepository appliedUserRepository;

    public ApplyService(CouponRepository couponRepository, CouponCountRepository couponCountRepository, CouponCreateProducer couponCreateProducer, AppliedUserRepository appliedUserRepository) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
        this.couponCreateProducer = couponCreateProducer;
        this.appliedUserRepository = appliedUserRepository;
    }

    public void apply(Long userId) {
        Long apply = appliedUserRepository.add(userId);

        if(apply != 1) {
            System.out.println("이미 발급 ");
            return;
        }

        long count = couponCountRepository.increment();

        if(count > 100) {
            System.out.println("count > 100 ");
            return;
        }

        System.out.println("Send message : " + userId);
        couponCreateProducer.create(userId);
    }
}
