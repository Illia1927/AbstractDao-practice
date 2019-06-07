package mate.academy.dao.jdbcDao;

import mate.academy.utill.JdbcConnector;
import mate.academy.utill.StringConver;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDaoJdbc<T, ID> implements CrudDaoJdbc<T, ID> {
    private static final Logger logger = Logger.getLogger(AbstractDaoJdbc.class);
    protected final Connection connection = JdbcConnector.getConnection();
    protected final Class<T> clazz;
    private Field[] fields;

    public AbstractDaoJdbc(Class<T> clazz) {
        this.clazz = clazz;
        fields = this.clazz.getDeclaredFields();
    }

    @Override
    public T save(T entity) {
        String query = getSaveQuery(entity);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.error("Check your sql query " + e);
        }
        return entity;
    }

    @Override
    public void deleteById(ID id) {
        String query = getDeleteQuery(id);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.error("Check your sql query " + e);
        }
    }

    @Override
    public T readById(ID id) {
        String query = getSelectQuery(id);
        T entity = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                entity = clazz.newInstance();
                for (Field field : fields) {
                    field.setAccessible(true);
                    field.set(entity, resultSet.getObject(field.getName()));
                }
            }
        } catch (Exception e) {
            logger.error("Check your sql query " + e);
        }
        return entity;
    }

    @Override
    public void update(T entity) {
        String query = getUpdateQuery(entity);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.error("Check your sql query " + e);
        }
    }

    @Override
    public List<T> getAll() {
        String query = getSelectAllQuery();
        T eintity = null;
        List<T> entityList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                eintity = clazz.newInstance();
                for (Field field : fields) {
                    field.setAccessible(true);
                    field.set(eintity, resultSet.getObject(field.getName()));
                }
                entityList.add(eintity);
            }
        } catch (Exception e) {
            logger.error("Check your sql query " + e);
        }
        return entityList;
    }

    private String getSaveQuery(T entity) {
        StringBuilder query = new StringBuilder("INSERT INTO ");
        query.append(StringConver.toSnakeCase(clazz.getSimpleName()) + " VALUES (");
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                String buf;
                if (field.getName().equals("id")) {
                    buf = "NULL";
                } else if (!field.getType().isPrimitive()) {
                    buf = '\'' + field.get(entity).toString() + '\'';
                } else {
                    buf = field.get(entity).toString();
                }
                query.append(buf + ", ");

            } catch (IllegalAccessException e) {
                logger.error("Check this exception " + e);
            }
        }
        query = new StringBuilder(query.substring(0, query.length() - 2));
        query.append(");");
        return query.toString();
    }

    private String getDeleteQuery(ID id) {
        StringBuilder query = new StringBuilder("DELETE FROM ");
        query.append(StringConver.toSnakeCase(clazz.getSimpleName()) + " WHERE id = " + id + ';');
        return query.toString();
    }

    private String getSelectQuery(ID id) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(StringConver.toSnakeCase(clazz.getSimpleName()) + " WHERE id = " + id + ';');
        return query.toString();
    }

    private String getSelectAllQuery() {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(StringConver.toSnakeCase(clazz.getSimpleName()) + ';');
        return query.toString();
    }

    private String getUpdateQuery(T entity) {
        StringBuilder query = new StringBuilder("UPDATE ");
        query.append(StringConver.toSnakeCase(clazz.getSimpleName()) + " SET ");
        String buf = "";
        String bufer = "";
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.getName().equals("id")) {
                    bufer = "ID = " + field.get(entity);
                } else {
                    if (!field.getType().isPrimitive()) {
                        buf = field.getName() + " = '" + field.get(entity).toString() + '\'';
                    } else {
                        buf = field.getName() + " = " + field.get(entity).toString();
                    }
                    query.append(buf + ", ");
                }
            } catch (IllegalAccessException e) {
                logger.error("Check this exception " + e);
            }
        }
        query = new StringBuilder(query.substring(0, query.length() - 2));
        query.append(" WHERE " + bufer + " ;");
        return query.toString();
    }
}
