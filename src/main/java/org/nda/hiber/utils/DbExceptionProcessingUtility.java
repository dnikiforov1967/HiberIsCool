package org.nda.hiber.utils;

import org.hibernate.exception.ConstraintViolationException;

import java.util.Map;
import java.util.Objects;

public final class DbExceptionProcessingUtility {

    private static final Map<String, RuntimeException> EXCEPTION_MAP = Map.of(
            "BOOK_UK", new IllegalArgumentException("Ugly")
    );

    private DbExceptionProcessingUtility() {
    }

    public static void proceedException(Throwable initialException) {
        var currEx = initialException;
        while(Objects.nonNull(currEx)) {
            if (currEx.getClass() == ConstraintViolationException.class) {
                proceedConstraints((ConstraintViolationException)currEx);
            }
            currEx = currEx.getCause();
        }
    }

    private static void proceedConstraints(ConstraintViolationException cve) {
        RuntimeException exception = EXCEPTION_MAP.entrySet().stream().filter(
                ent -> cve.getMessage().contains(ent.getKey())
        ).map(Map.Entry::getValue).findFirst().orElse(null);
        if (Objects.nonNull(exception)) {
            throw exception;
        }
    }
}
