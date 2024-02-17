package org.nda.hiber;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Component
@RequiredArgsConstructor
@Slf4j
public class Mallow {

    private final XxxRepo repo;
    private final ISpecialJpa specialJpa;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Abracadabra mallowString() {
        Abracadabra assa = Abracadabra.builder().name("Assa").build();
        return repo.saveAndFlush(assa);
    }

    @Transactional(rollbackFor = BEx.class)
    public Abracadabra mallow() throws BEx {
        Abracadabra assa = Abracadabra.builder().name("Assa").build();
        try {
            Abracadabra abracadabra = repo.saveAndFlush(assa);
            System.out.println("I am here "+TransactionAspectSupport.currentTransactionStatus().isRollbackOnly());
            return abracadabra;
        } catch(Exception e) {
            System.out.println("Transaction status after "+ TransactionAspectSupport.currentTransactionStatus().isRollbackOnly());
            log.warn("Problem {}", e.getMessage());
            specialJpa.request("Assa");
            throw e;
        }
    }

}
