package br.com.thiagosousa.ordersapi.model;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(length = 60)
    @Enumerated(EnumType.STRING)
    private RoleName name;
}
