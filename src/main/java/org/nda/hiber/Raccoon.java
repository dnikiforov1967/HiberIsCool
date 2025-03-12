package org.nda.hiber;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Raccoon {

    @Id
    private String name;

    @Column
    private Boolean fat;

    @Column
    private Boolean clean;

    @Column(name="headcount")
    private Integer headCount;

    @Version
    @Column
    private Integer version;

    @PrePersist
    @PreUpdate
    public void hcInit() {
        headCount = 1222;
    }

}
