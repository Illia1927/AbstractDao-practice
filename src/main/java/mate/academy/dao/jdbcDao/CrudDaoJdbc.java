package mate.academy.dao.jdbcDao;

import java.util.List;

public interface CrudDaoJdbc<T, ID> {

    T save(T entity);

    void deleteById(ID id);

    T readById(ID id);

    void update(T entity);

    List<T> getAll();
}
