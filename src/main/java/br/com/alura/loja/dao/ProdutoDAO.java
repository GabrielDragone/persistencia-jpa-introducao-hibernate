package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDAO {

    private EntityManager em;

    public ProdutoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto) {
        this.em.persist(produto);
    }

    public void atualizar(Produto produto) {
        this.em.merge(produto); // pra garantir que essa categoria vai estar no modo merge. Mas aqui nao é alterado, eles ja chegam alterados. Quando chamar o flush ja atualiza.
    }

    public Produto buscarPorId(Long idProduto) {
        return em.find(Produto.class, idProduto);
    }

    public List<Produto> buscarTodos() {
        String jpql = "SELECT p FROM Produto p"; // Passar o nome da entidade, não da tabela
        return em.createQuery(jpql, Produto.class).getResultList();
    }

    public List<Produto> buscarPorNome(String nome) {
        String jpql = "SELECT p FROM Produto p WHERE p.nome = ?1"; // Passar o nome do atributo, não da tabela
        return em.createQuery(jpql, Produto.class).
                setParameter(1, nome).
                getResultList();
    }

    public List<Produto> buscarPorNomeDaCategoria(String nome) {
        String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome"; // POO, acessamos o atributo da Categoria
        return em.createQuery(jpql, Produto.class).
                setParameter("nome", nome).
                getResultList();
    }

    public BigDecimal buscarPrecoDoProdutoPorNome(String nome) {
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome"; // Informa qual campo quer retornar
        return em.createQuery(jpql, BigDecimal.class).
                setParameter("nome", nome).
                getSingleResult();
    }
}
