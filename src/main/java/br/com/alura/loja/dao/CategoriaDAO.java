package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;

public class CategoriaDAO {

    private EntityManager em;

    public CategoriaDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Categoria categoria) {
        this.em.persist(categoria);
    }

    public void atualizar(Categoria categoria) {
        this.em.merge(categoria); // pra garantir que essa categoria vai estar no modo merge. Mas aqui nao é alterado, eles ja chegam alterados. Quando chamar o flush ja atualiza.
    }

    public void delete(Categoria categoria) {
        categoria = em.merge(categoria); // primeiro reatribuimos a categoria através do merge, pois ela precisa estar no modo MANAGED.
        this.em.remove(categoria); // depois utilizamos o remove para remove-la.
    }


}
