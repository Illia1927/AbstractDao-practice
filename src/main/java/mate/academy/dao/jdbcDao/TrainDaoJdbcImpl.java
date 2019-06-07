package mate.academy.dao.jdbcDao;

import mate.academy.model.Train;

public class TrainDaoJdbcImpl extends AbstractDaoJdbc<Train, Long> implements TrainDaoJdbc {
    public TrainDaoJdbcImpl(Class<Train> clazz) {
        super(clazz);
    }
}
