package br.gov.sp.fatec.entities;

import javax.persistence.*;

@Entity
public class Livro{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria_id;

    public Livro() {

    }

    public Livro(String nome) {
        this.nome = nome;
    }

    public Livro(String nome, Categoria categoria_id) {
        this.nome = nome;
        this.categoria_id = categoria_id;
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

    public Categoria getCategoria() {
        return categoria_id;
    }

    public void setCategoria(Categoria categoria_id) {
        this.categoria_id = categoria_id;
    }
}