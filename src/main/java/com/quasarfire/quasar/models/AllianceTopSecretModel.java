package com.quasarfire.quasar.models;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "alliance_top_secret")
@ToString
@EqualsAndHashCode
@TypeDef(name = "json", typeClass = JsonStringType.class)
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

    @Type(type = "json")
    @Getter @Setter @Column(name="message", columnDefinition = "json")
    private List<String> message;
}
