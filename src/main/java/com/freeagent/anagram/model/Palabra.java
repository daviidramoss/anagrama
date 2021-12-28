package com.freeagent.anagram.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_palabra", schema = "sch_general")
public class Palabra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_palabra;
    private String palabra;


}
