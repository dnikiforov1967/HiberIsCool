package org.nda.hiber.ord;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Entity
public class BookOrder {

    public static final Map<String, RuntimeException> EXCEPTION_MAP = Map.of(
            "BOOK_UK", new IllegalArgumentException("Ugly")
    );

    @Id
    private int id;
}
