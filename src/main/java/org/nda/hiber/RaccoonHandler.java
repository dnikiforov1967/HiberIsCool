package org.nda.hiber;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class RaccoonHandler {

    private final RaccoonRepo repo;
    private final TransactionTemplate transactionTemplate;
    private final EntityManager em;
    private final TransactionManager tm;

    @Transactional
    public void feedRaccoon(final String raccoonName) {
        Raccoon raccoon = repo.findById(raccoonName).orElse(null);
        if (Objects.nonNull(raccoon)) {
            raccoon.setFat(true);
            repo.save(raccoon);
            repo.flush();
        }

    }

    @Transactional
    public void washRaccoon(final String raccoonName) {
        Raccoon raccoon = repo.findById(raccoonName).orElse(null);
        if (Objects.nonNull(raccoon)) {
            raccoon.setClean(true);
            repo.save(raccoon);
        }

    }

    @Transactional(propagation = Propagation.NESTED)
    public boolean buyRaccoon(String name) {
        try {
            Raccoon raccoon = new Raccoon();
            raccoon.setName(name);
            raccoon.setFat(true);
            em.persist(raccoon);
            em.flush();
            return true;
        } catch(Exception e) {
            System.out.println("Raccoon already has been bought");
            return false;
        }
    }



}
