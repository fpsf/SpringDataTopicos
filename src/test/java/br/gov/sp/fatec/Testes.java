package br.gov.sp.fatec;

import br.gov.sp.fatec.entities.Categoria;
import br.gov.sp.fatec.repository.RepoCategoria;
import br.gov.sp.fatec.service.ServicoApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@Transactional
public class Testes {

    private static final String catnome = "Manga";
    private static final String[] livros = new String[]{"Tokyo Ghoul", "Onepunchman"};

    @Autowired
    private RepoCategoria repoCategoria;
    @Autowired
    private ServicoApp servicoApp;

    /*
    public void setRepoCategoria(RepoCategoria repo){
        this.repoCategoria = repo;
    }
    */

    @Test
    public void repoInsercaoOk(){
        Categoria categ = new Categoria(catnome);
        repoCategoria.save(categ);
        assertNotNull(categ.getNome());
    }

    @Test
    public void repoExclusaoOk(){
        Categoria categ = new Categoria(catnome);
        repoCategoria.delete(categ);
        assertNull(categ.getNome());
    }

    // Insere livros e acha a categoria de determinado livro
    @Test
    public void servicoInserirEAcharOk(){
        servicoApp.InserirLivrosPorCategoria(catnome, livros);
        List<Categoria> categoriaList = servicoApp.CatPorLivro("Onepunchman");
        assertNotNull(categoriaList);
    }

    @Test
    public void servicoDeletarOk(){
        servicoApp.DeletarTudo();
        List<Categoria> cats = repoCategoria.findAll();
        assertNull(cats.get(0));
    }


}
