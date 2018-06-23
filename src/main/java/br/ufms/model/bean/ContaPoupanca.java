
package br.ufms.model.bean;

import javax.persistence.Entity;

@Entity
public class ContaPoupanca extends ContaBancaria {

    private Character categoria;

    public Character getCategoria() {
        return categoria;
    }

    public void setCategoria(Character categoria) {
        if(categoria.equals('A') || categoria.equals('B') || categoria.equals('C'))
            this.categoria = categoria;
        else
            throw new IllegalArgumentException("Categoria não Existente");
    }
}
