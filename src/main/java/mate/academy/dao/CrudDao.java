package mate.academy.dao;

public interface CrudDao<T, ID> {
    T create(T entity);

    T readById(ID id);

    void update(T entity);

    void delete(T entity);
}
