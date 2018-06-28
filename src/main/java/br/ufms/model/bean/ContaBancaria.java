
package br.ufms.model.bean;

import br.ufms.model.dao.EntidadeBase;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "getPorNome", query = "SELECT c FROM ContaBancaria c where c.nomeUsuario = :nomeUsuario")
})
public class ContaBancaria implements EntidadeBase {

    @Id
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(name = "numero", nullable = false)
    private Integer numero;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "Agencia", nullable = false)
    private Agencia agencia;



    private Double saldo;
    @Column(nullable = false)
    private String correntista;

    @Column(nullable = false)
    private String nomeUsuario;
    @Column(nullable = false)
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

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }


}
