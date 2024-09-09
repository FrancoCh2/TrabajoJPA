package org.example;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Domicilio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCalle;

    private  int nummero;

    @OneToOne(mappedBy = "domicilio")
    private Cliente cliente;


}
