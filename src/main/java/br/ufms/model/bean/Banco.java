package br.ufms.model.bean;

import br.ufms.model.dao.EntidadeBase;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Banco.getAll", query = "SELECT  b from Banco  b"),
        @NamedQuery(name = "Banco.getPorId", query = "SELECT b from Banco  b where b.id = :parameter")
})
public class Banco implements EntidadeBase {

    @Id
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL},
            mappedBy = "banco", targetEntity = Agencia.class)
    private Set<Agencia> agencias = new HashSet<>(0);


    public Integer getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public Banco() {

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

    public Banco(String nome) {
        this.nome = nome;
    }

    public Banco(String nome, Set<Agencia> agencias) {
        this.nome = nome;
        this.agencias = agencias;
    }

    public void setNome(String nome) {
        if (nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser Nulo!");
        } else {
            this.nome = nome;
        }
    }
}
