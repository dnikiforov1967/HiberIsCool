package org.nda.hiber;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.util.UUID;

@Entity
@Table(name="events")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Abracadabra {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="name", unique = true)
    private String name;
}
