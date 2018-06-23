package br.ufms.model.bean;

import br.ufms.model.dao.EntidadeBase;

import javax.persistence.*;
import java.util.List;

@Entity
public class Banco implements EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer id;

    private String nome;

    @OneToMany
    private List<Agencia> agencias;

    public Integer getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Agencia> getAgencias() {
        return agencias;
    }

    public void setAgencias(List<Agencia> agencias) {
        this.agencias = agencias;
    }
}
