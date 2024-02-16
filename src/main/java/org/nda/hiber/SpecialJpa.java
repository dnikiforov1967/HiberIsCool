package org.nda.hiber;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SpecialJpa implements ISpecialJpa {

    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void request(final String name) throws BEx {
        log.info("A teper ja budu selectit {}", TransactionAspectSupport.currentTransactionStatus().isRollbackOnly());
        Boolean query = jdbcTemplate.query("select * from events where name = ?",
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setString(1, name);
                    }
                },
                new ResultSetExtractor<Boolean>() {
                    @Override
                    public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
                        return rs.first();
                    }
                });
        if (query) {
            log.info("Throwaem sejchas {}", TransactionAspectSupport.currentTransactionStatus().isRollbackOnly());
            throw new BEx();
        }
    }
}
