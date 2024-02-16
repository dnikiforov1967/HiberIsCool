package org.nda.hiber;

import jakarta.persistence.EntityManager;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditQuery;
import org.hibernate.envers.query.AuditQueryCreator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;



import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
public class JpaHiberTest {

    @Autowired
    private XxxRepo repo;
    @Autowired
    private Mallow mallow;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private Cover cover;


    @Test
    @Transactional
    void amba() {

    }

    @Test
    @Transactional
    void amba2() throws Exception {
        Abracadabra m = mallow.mallowString();
        entityManager.clear();
        assertThrows(Exception.class, ()->mallow.mallow());
    }

}
