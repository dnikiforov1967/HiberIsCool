package org.nda.hiber.utils;

import org.hibernate.exception.ConstraintViolationException;

import java.util.Map;
import java.util.Objects;

public final class DbExceptionProcessingUtility {

    private DbExceptionProcessingUtility() {
    }

    public static void proceedException(Throwable initialException, Map<String, RuntimeException> exceptionMap) {
        var currEx = initialException;
        while(Objects.nonNull(currEx)) {
            if (currEx.getClass() == ConstraintViolationException.class) {
                proceedConstraints((ConstraintViolationException)currEx, exceptionMap);
            }
            currEx = currEx.getCause();
        }
    }

    private static void proceedConstraints(ConstraintViolationException cve, Map<String, RuntimeException> exceptionMap) {
        RuntimeException exception = exceptionMap.entrySet().stream().filter(
                ent -> cve.getMessage().contains(ent.getKey())
        ).map(Map.Entry::getValue).findFirst().orElse(null);
        if (Objects.nonNull(exception)) {
            throw exception;
        }
    }
}
