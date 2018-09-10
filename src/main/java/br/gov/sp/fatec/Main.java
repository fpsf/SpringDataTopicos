package br.gov.sp.fatec;

import br.gov.sp.fatec.entities.Categoria;
import br.gov.sp.fatec.service.ServicoApp;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;

/**
 * @author Fernando Passacantilli Silva Ferrari
 * @author Graziele Galvão Silva
 * @author Leonardo Livans Souza
 * @author Rafael Marinho de Andrade
 * @author Thiago de Oliveira Alves
 * @author Vinicius de Souza Cardoso
 */
public class Main {

    public static void main(String[] args){

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(	"applicationContext.xml");

        // Cria o Serviço
        ServicoApp servicoCategoria = (ServicoApp) context.getBean("servicoApp");

        // Cria a Categoria
        servicoCategoria.InserirCategoria("Manga");

        // Insere Livros na Categoria Criada
        servicoCategoria.InserirLivrosPorCategoria("an", new String[]{"Boku no Hero Academia", "Sankarea"});

        // Retorna os Dados
        StringBuilder saida = new StringBuilder();
        for (Categoria item : servicoCategoria.CatPorLivro("Manga")) {
            saida.append(item.toString());
        }
        JOptionPane.showMessageDialog(null, saida);

        // Limpa o Banco
        servicoCategoria.DeletarTudo();
    }

}
