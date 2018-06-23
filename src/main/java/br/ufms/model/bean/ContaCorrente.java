
package br.ufms.model.bean;

import javax.persistence.Entity;

@Entity
public class ContaCorrente extends ContaBancaria {

    private Double limite;


    public Double getLimite() {
        return limite;
    }


    public void setLimite(Double limite) {
        this.limite = limite;
    }
    
}
