package org.nda.hiber.ord;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Head {

    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

}
