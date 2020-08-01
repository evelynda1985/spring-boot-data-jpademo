package com.evecodeideas.com.springboot.app.models.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

//Para indicar que la clase es un POJO
@Entity
@Table(name = "clientes") //para el nombre que va a tener en la tabla. Estandar en minuscula y es en plural
public class Cliente implements Serializable {

    @Id //indica PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Como se generara el id
    @Getter @Setter
    private Long id;

    @Getter @Setter
    @NotEmpty
//    @Size(min=4, max=12)
    private String nombre;

    @Getter @Setter
    @NotEmpty
    private String apellido;

    @Getter @Setter
    @NotEmpty
    private String email;

    @Column(name = "create_at") //para indicar como se llamaria la columna en la DB, standar separado por _
    @Temporal(TemporalType.DATE) //para indicar como se guardara la fecha si solo fecha, o fecha y hora...
    @Getter @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Email
    private Date createAt;

    private static final long serialVersionUID = 1L;

//    @PrePersist //para ejecutar un metodo antes de persistir
//    public void prePersist(){
//        createAt = new Date();
//    }

}
