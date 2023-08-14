package com.example.studyeffectivejavapart1.chpater03.item15.code.class_and_interfaces.item;

import com.example.studyeffectivejavapart1.chpater03.item15.code.class_and_interfaces.member.MemberService;

public class ItemService {

    private MemberService memberService;

    boolean onSale;

    protected int saleRate;

    public ItemService(MemberService memberService) {
        if (memberService == null) {
            throw new IllegalArgumentException("MemberService should not be null.");
        }

        this.memberService = memberService;
    }

    MemberService getMemberService() {
        return memberService;
    }
}
