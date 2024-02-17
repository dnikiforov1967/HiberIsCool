package org.nda.hiber;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.SQLException;

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
    public Abracadabra mallow() throws Throwable {
        Abracadabra assa = Abracadabra.builder().name("Assa").build();
        try {
            Abracadabra abracadabra = repo.saveAndFlush(assa);
            System.out.println("I am here "+TransactionAspectSupport.currentTransactionStatus().isRollbackOnly());
            return abracadabra;
        } catch(Exception e) {
            throw proceedException(e);
        }
    }

    Throwable proceedException(Throwable initalException) {
        System.out.println("Transaction status after "+ TransactionAspectSupport.currentTransactionStatus().isRollbackOnly());
        var exception = initalException;
        while(exception != null) {
            if (exception instanceof SQLException sqlException) {
                //23505 is standard across
                // See
                // H2 http://h2database.com/javadoc/org/h2/jdbc/JdbcSQLIntegrityConstraintViolationException.html
                // Postgresql https://www.postgresql.org/docs/current/errcodes-appendix.html
                // Oracle https://docs.oracle.com/javadb/10.8.3.0/ref/rrefexcept71493.html
                log.info("Error {}",sqlException.getErrorCode());
                String sqlState = sqlException.getSQLState();
                log.info("State {}", sqlState);
                String message = sqlException.getMessage().toUpperCase();
                log.info("Message {}", message);
                if ("23505".equals(sqlState) && message.contains("NAME_IS_UNIQUE")) {
                    return new BEx();
                } else {
                    return initalException;
                }
            } else {
                exception = exception.getCause();
            }
        }
        return initalException;
    }

}
