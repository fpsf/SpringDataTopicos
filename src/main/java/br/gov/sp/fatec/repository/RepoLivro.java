package br.gov.sp.fatec.repository;

import br.gov.sp.fatec.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoLivro extends JpaRepository<Livro, Integer> {

    @Query("select l from Livro l where l.nome like %?1%")
    List<Livro> findByNome(String nome);

    Livro findOneByNomeContains(String qry);

}
