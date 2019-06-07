package mate.academy;

import mate.academy.dao.jdbcDao.TrainDaoJdbc;
import mate.academy.dao.jdbcDao.TrainDaoJdbcImpl;
import mate.academy.model.Train;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        Train train = Train.builder()
                .name("Tarpan")
                .number(1488)
                .adressFrom("Kiev")
                .adressTo("Odessa")
                .build();
        TrainDaoJdbc trainDaoJdbc = new TrainDaoJdbcImpl(Train.class);
        trainDaoJdbc.save(train);
        Train trainFromDb = trainDaoJdbc.readById(2L);
        trainFromDb.setName("NYUNDAI");
        trainFromDb.setNumber(367);
        trainFromDb.setAdressTo("NIGERIA");
        trainDaoJdbc.update(trainFromDb);
        trainDaoJdbc.deleteById(1L);
        logger.info(trainDaoJdbc.getAll());
    }
}
