package br.gov.sp.fatec.entities;

import javax.persistence.*;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "Categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    @OneToMany(mappedBy = "categoria_id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Livro> livros;

    public Categoria(){

    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Livro> getLivros() {
        return livros;
    }

    public void setLivros(Set<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        StringBuilder result = Optional.ofNullable(String.format(
                "Category[id=%d, name='%s']%n",
                id, nome)).map(StringBuilder::new).orElse(null);
        if (livros != null) {
            for(Livro item : livros) {
                result = (result == null ? new StringBuilder("null") : result).append(String.format(
                        "Book[id=%d, name='%s']%n",
                        item.getId(), item.getNome()));
            }
        }

        return result == null ? null : result.toString();
    }
}
