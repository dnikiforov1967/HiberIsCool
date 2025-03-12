package org.nda.hiber;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;


@SpringBootTest
public class JpaHiberTest {


    @Autowired
    private RaccoonHandler raccoonHandler;
    @Autowired
    private RaccoonRepo repo;
    @Autowired
    private RaccoonFacade raccoonFacade;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private EntityManager em;

    @Test
    @Sql(statements = {"insert into raccoon(name, fat, clean, version) values('Fred',false,false,1)"}, config = @SqlConfig(
            transactionMode = SqlConfig.TransactionMode.ISOLATED
    ))
    @Transactional
    void xa() {
        Raccoon raccoon = repo.findById("Fred").get();
        raccoon.setFat(true);
        repo.saveAndFlush(raccoon);
        em.clear();

        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        transactionTemplate.executeWithoutResult(ts->{
            Raccoon raccoon2 = repo.findById("Fred").get();
            System.out.println("Is new: "+ts.isNewTransaction());
            System.out.println("Fast: "+raccoon2.getFat());
        });

        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        transactionTemplate.executeWithoutResult(ts->{
            Raccoon raccoon2 = repo.findById("Fred").get();
            System.out.println("Is new: "+ts.isNewTransaction());
            System.out.println("Fast: "+raccoon2.getFat());
        });

    }


    @Test
    @Sql(statements = {"insert into raccoon(name, fat, clean, version) values('Fred',false,false,1)"}, config = @SqlConfig(
            transactionMode = SqlConfig.TransactionMode.ISOLATED
    ))
    void washAndFeedRaccoon() {
        raccoonHandler.washRaccoon("Fred");
        raccoonHandler.feedRaccoon("Fred");
        repo.flush();
    }

    @Test
    @Sql(statements = {"insert into raccoon(name, fat, clean, version) values('Fred',false,false,1)"}, config = @SqlConfig(
            transactionMode = SqlConfig.TransactionMode.ISOLATED
    ))
    @Transactional
    void buyAndFeedRaccoon() {
        Raccoon raccoon = new Raccoon();
        raccoon.setName("Fred");
        raccoon.setFat(true);
        raccoon.setClean(true);
        //raccoon.setVersion(2);
        //repo.upsert(raccoon);
        repo.save(raccoon);


        Raccoon raccoon1 = repo.findById("Fred").orElse(null);
        System.out.println(raccoon1.getFat());
        System.out.println(raccoon1.getVersion());
        System.out.println(raccoon1.getHeadCount());

        /*raccoon.setName("Dima");
        raccoon.setVersion(4);
        repo.upsert(raccoon);

        raccoon1 = repo.findById("Dima").orElse(null);
        System.out.println(raccoon1.getFat());
        System.out.println(raccoon1.getVersion());*/


    }


}
