package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.prato;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.restaurante.Restaurante;

@Entity
@Table(name = "prato")
public class Prato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    
    public String nome;
    
    public String descricao;
    
    @ManyToOne
    public Restaurante restaurante;
    
    public BigDecimal preco;

}
