package mate.academy.dao;

import javax.persistence.EntityManager;

public abstract class AbstractDao<T, ID> implements CrudDao<T, ID> {
    protected final EntityManager entityManager;
    protected final Class<T> clazz;

    public AbstractDao(EntityManager entityManager, Class<T> clazz) {
        this.entityManager = entityManager;
        this.clazz = clazz;
    }

    @Override
    public T create(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public T readById(ID id) {
        entityManager.getTransaction().begin();
        T entity = entityManager.find(clazz, id);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public void update(T entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(T entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }
}
