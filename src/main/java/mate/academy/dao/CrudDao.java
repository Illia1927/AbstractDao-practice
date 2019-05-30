package mate.academy.dao;

import java.io.Serializable;

public interface CrudDao<T, ID extends Serializable> {
    T create(T entity);

    T readById(ID id);

    void update(T entity);

    void update(ID id, T entity);

    void delete(T entity);

}
