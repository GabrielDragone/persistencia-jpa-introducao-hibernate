package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

public class CadastroDeProduto {

    public static void main(String[] args) {
        cadastrarProduto();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        Produto p = produtoDAO.buscarPorId(1l);
        System.out.println(">>>>> Descrição: " + p.getDescricao());

//        List<Produto> listaProdutos = produtoDAO.buscarTodos();
//        listaProdutos.forEach(produto -> System.out.println("Produto: " + produto.getNome()));

//        List<Produto> listaProdutos = produtoDAO.buscarPorNome("Xiaomi Redmi");
//        listaProdutos.forEach(produto -> System.out.println("Produto: " + produto.getNome()));

//        List<Produto> listaProdutos = produtoDAO.buscarPorNomeDaCategoria("CELULARES");
//        listaProdutos.forEach(produto -> System.out.println("Produto: " + produto.getNome()));

        BigDecimal precoDoProduto = produtoDAO.buscarPrecoDoProdutoPorNome("Xiaomi Redmi");
        System.out.println("O preço do produto é: " + precoDoProduto);
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES"); // ESTADO JPA TRANSIENT: Ainda não está sendo gerenciado pela JPA.
        Produto produto = new Produto(
                "Xiaomi Redmi",
                "Muito legal",
                new BigDecimal("800"),
                celulares
        );

        EntityManager em = JPAUtil.getEntityManager();

        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        em.getTransaction().begin();
        categoriaDAO.cadastrar(celulares);
        produtoDAO.cadastrar(produto);

//        em.persist(celulares); // ESTADO JPA MANAGED: Já está sendo gerenciado pela JPA. Tudo que é feito nesse estado, se houver commit/flush será sincronizado com o banco.
//        celulares.setNome("XPTO");
//
//        em.flush();
//        em.clear();

//        celulares = em.merge(celulares);
//        celulares.setNome("1234");
//        em.flush();

        em.getTransaction().commit();
        em.close(); // ESTADO JPA DETACHED: Após close/clear, não estará sendo gerenciado pela JPA mais, ou seja, qualquer alteração realizada não será feita no banco.
    }

}
