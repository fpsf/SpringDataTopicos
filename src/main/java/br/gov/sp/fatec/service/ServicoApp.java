package br.gov.sp.fatec.service;

import br.gov.sp.fatec.entities.Categoria;
import br.gov.sp.fatec.entities.Livro;
import br.gov.sp.fatec.repository.RepoCategoria;
import br.gov.sp.fatec.repository.RepoLivro;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("servicoApp")
public class ServicoApp {

    private ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext(	"applicationContext.xml");
    private RepoCategoria repoCategoria = (RepoCategoria) context.getBean("repoCategoria");
    private RepoLivro repoLivro = (RepoLivro) context.getBean("repoLivro");
    private Categoria categ;

    @Transactional
    public void InserirLivrosPorCategoria(String cat, String[] lista){
        categ = repoCategoria.findOneByNomeContains(cat);
        Set bookAs = new HashSet<Livro>(){{
            for (String aLista : lista) {
                add(new Livro(aLista, categ));
            }
        }};
        categ.setLivros(bookAs);
        repoCategoria.save(categ);
    }

    @Transactional
    public void InserirCategoria(String cat){
        categ = new Categoria(cat);
        repoCategoria.save(categ);
    }

    @Transactional
    public List<Categoria> CatPorLivro(String qry){
        List<Livro> cst = repoLivro.findByNome(qry);
        if(cst.size() == 0) {
            Livro lvr = repoLivro.findOneByNomeContains(qry);
            if(lvr == null){
                return repoCategoria.findAll();
            }
            return repoCategoria.findByNome(lvr.getNome());
        }
        return repoCategoria.findByNome(cst.get(0).getNome());
    }

    @Transactional
    public void DeletarTudo(){
        repoCategoria.deleteAll();
    }

}
