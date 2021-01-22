package br.com.dimed.busIntegration.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "Localizacao")
public class Location {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Localizacao_SEQ")
    @Column(name = "id", nullable = false)
    protected Long id;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;
}
