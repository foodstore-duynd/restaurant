package com.restarant.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Tables.
 */
@Data
@Entity
@Table(name = "jhi_tables")
@SQLDelete(sql = "UPDATE jhi_tables SET deleteflag = 1 WHERE id = ?")
@Where(clause = "deleteflag = 0")
public class Tables implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private int status;

    @Column(name = "numberofchair")
    private Integer numberOfChair;

    @Column(name = "deleteflag")
    private long deleteflag;

    @OneToMany(mappedBy = "tables")
    @JsonIgnoreProperties(value = { "orderDetails", "tables", "orderTotal" }, allowSetters = true)
    private Set<TableOrder> tableOrders = new HashSet<>();

}
