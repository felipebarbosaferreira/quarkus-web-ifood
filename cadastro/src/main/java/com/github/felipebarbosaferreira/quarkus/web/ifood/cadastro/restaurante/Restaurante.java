package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.restaurante;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.localizacao.Localizacao;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;


@Entity
@Table(name = "restaurante")
public class Restaurante extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    
    public String proprietario;
    
    public String cnpj;
    
    public String nome;
    
    @ManyToOne
    public Localizacao localizacao;
    
    @CreationTimestamp
    public Date dataCriacao;
    
    @UpdateTimestamp
    public Date dataAtualizacao;

}
