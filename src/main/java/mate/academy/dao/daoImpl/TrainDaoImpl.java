package mate.academy.dao.daoImpl;

import mate.academy.dao.AbstractDao;
import mate.academy.dao.TrainDao;
import mate.academy.model.Train;

import javax.persistence.EntityManager;

public class TrainDaoImpl extends AbstractDao<Train, Long> implements TrainDao {
    public TrainDaoImpl(EntityManager entityManager, Class<Train> clazz) {
        super(entityManager, clazz);
    }
}
