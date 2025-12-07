package org.nda.hiber.ord;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Customer {

    @Id
    private int id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<BookOrder> orders = new ArrayList<>();

}
