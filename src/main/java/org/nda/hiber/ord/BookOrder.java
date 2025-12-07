package org.nda.hiber.ord;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class BookOrder {

    @Id
    private int id;
}
