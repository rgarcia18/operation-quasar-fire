package com.quasarfire.quasar.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "alliance_top_secret")
@ToString
@EqualsAndHashCode
public class AllianceTopSecretModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id")
    private Long id;

    @Getter @Setter @Column(name = "satellite")
    private String satellite;

    @Getter @Setter @Column(name = "distance")
    private double distance;

    @Getter @Setter @Column(name = "message")
    private String message;
}
