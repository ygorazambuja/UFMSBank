
package br.ufms.model.bean;

import br.ufms.model.dao.EntidadeBase;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "getPorNome", query = "SELECT c FROM ContaBancaria c where c.nomeUsuario = :nomeUsuario")
})
public class ContaBancaria implements EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer numero;
    private Double saldo;
    private String correntista;

    private String nomeUsuario;
    private String senha;


    protected ContaBancaria() {

    }

    public Integer getId() {
        return numero;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }


    public String getCorrentista() {
        return correntista;
    }

    public void setCorrentista(String correntista) {
        this.correntista = correntista;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
