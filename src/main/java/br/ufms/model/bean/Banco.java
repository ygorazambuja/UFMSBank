package br.ufms.model.bean;

import br.ufms.model.dao.EntidadeBase;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
public class Banco implements EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer id;

    private String nome;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "id")
    private Set<Agencia> agencias = new HashSet<>(0);


    public Integer getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.equals(null)) {
            throw new IllegalArgumentException("Nome não pode ser Nulo!");
        } else {
            this.nome = nome;
        }
    }


    public Set<Agencia> getAgencias() {
        return agencias;
    }


    public void setAgencias(Set<Agencia> agencias) {
        this.agencias = agencias;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
