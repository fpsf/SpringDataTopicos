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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void repoInsercaoOk(){
        Categoria categ = new Categoria(catnome);
        repoCategoria.save(categ);
        assertNotNull(categ.getNome());
    }

    @Test
    public void repoExclusaoOk(){
        Categoria categ = new Categoria(catnome);
        repoCategoria.save(categ);
        repoCategoria.delete(categ);
        categ = repoCategoria.findOneByNomeContains(catnome);
        assertNull(categ);
    }

    // Insere livros e acha a categoria de determinado livro
    @Test
    public void servicoInserirEAcharOk(){
        Categoria categ = new Categoria(catnome);
        repoCategoria.save(categ);
        servicoApp.InserirLivrosPorCategoria(catnome, livros);
        List<Categoria> categoriaList = servicoApp.CatPorLivro(livros[1]);
        assertNotNull(categoriaList);
    }

    @Test
    public void servicoDeletarOk(){
        Categoria categ = new Categoria(catnome);
        repoCategoria.save(categ);
        servicoApp.DeletarTudo();
        List<Categoria> cats = repoCategoria.findAll();
        assertEquals(cats.size(), 0);
    }


}
