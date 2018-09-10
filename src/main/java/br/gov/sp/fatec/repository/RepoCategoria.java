package br.gov.sp.fatec.repository;

import br.gov.sp.fatec.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoCategoria extends JpaRepository<Categoria, Integer> {

    @Query("select c from Categoria c where c.nome like %?1%")
    List<Categoria> findByNome(String nome);

    Categoria findOneByNomeContains(String nome);

}
