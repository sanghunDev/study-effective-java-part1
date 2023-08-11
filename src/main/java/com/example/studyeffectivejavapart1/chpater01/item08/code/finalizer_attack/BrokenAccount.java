package com.example.studyeffectivejavapart1.chpater01.item08.code.finalizer_attack;

import java.math.BigDecimal;

public class BrokenAccount extends Account {

    public BrokenAccount(String accountId) {
        super(accountId);
    }

    @Override
    protected void finalize() throws Throwable {
        this.transfer(BigDecimal.valueOf(100), "keesun");
    }
}

