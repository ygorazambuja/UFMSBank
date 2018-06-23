package br.ufms.model.bean;

import br.ufms.model.dao.EntidadeBase;

import javax.persistence.*;

@Entity
public class Agencia implements EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Banco banco;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }
}
