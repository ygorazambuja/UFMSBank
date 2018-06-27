package br.ufms.model.bean;

import br.ufms.model.dao.EntidadeBase;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Agencia implements EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Banco", nullable = false)
    private Banco banco;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "numero")
    private Set<ContaBancaria> contaBancarias = new HashSet<>(0);

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


    public Set<ContaBancaria> getContaBancarias() {
        return contaBancarias;
    }

    public void setContaBancarias(Set<ContaBancaria> contaBancarias) {
        this.contaBancarias = contaBancarias;
    }


}
