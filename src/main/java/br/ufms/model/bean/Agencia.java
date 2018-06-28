package br.ufms.model.bean;

import br.ufms.model.dao.EntidadeBase;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Agencia.getPorBanco", query = "SELECT a from Agencia a where a.banco.id =:parameter")
})
public class Agencia implements EntidadeBase {

    @Id
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(name = "id", nullable = false)

    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "Banco", nullable = false)
    private Banco banco;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE}, mappedBy = "numero", targetEntity = ContaBancaria.class)
    private Set<ContaBancaria> contaBancarias = new HashSet<>(0);


    public Integer getId() {
        return this.id;
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
