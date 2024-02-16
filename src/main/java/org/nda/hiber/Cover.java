package org.nda.hiber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Component
public class Cover {

    @Autowired
    private ISpecialJpa specialJpa;

    @Transactional
    public void cover(Abracadabra m) throws Exception {
        System.out.println("Before "+TransactionAspectSupport.currentTransactionStatus().isRollbackOnly());
        try {
            specialJpa.request("Assa");
        } catch(Exception e) {
            System.out.println("Right After "+TransactionAspectSupport.currentTransactionStatus().isRollbackOnly());
            throw e;
        }
    }
}
