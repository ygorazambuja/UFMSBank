
package br.ufms.model.bean;

import br.ufms.model.dao.EntidadeBase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ContaBancaria implements EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer numero;
    private Double saldo;
    private String correntista;
    
    protected ContaBancaria() {
        
    }

    public Integer getId() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
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
    
}
