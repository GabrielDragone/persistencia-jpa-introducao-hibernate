package br.com.alura.loja.testes;

import br.com.alura.loja.modelo.Produto;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CadastroDeProduto {

    public static void main(String[] args) {
        Produto produto = criarProduto(
                "Xiaomi Redmi",
                "Muito legal",
                new BigDecimal("800")
        );

        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("loja"); //verificar o persistence.xml

        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(produto);
        em.getTransaction().commit();
        em.close();
    }

    private static Produto criarProduto(String nome, String descricao, BigDecimal preco) {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        return produto;
    }
}
