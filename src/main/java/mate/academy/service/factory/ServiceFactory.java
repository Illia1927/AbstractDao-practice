package mate.academy.service.factory;

import mate.academy.dao.daoImpl.TrainDaoImpl;
import mate.academy.model.Train;
import mate.academy.service.TrainService;
import mate.academy.service.serviceImpl.TrainServiceImpl;
import mate.academy.utill.EntityManagerHolder;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;

public class ServiceFactory {
    private static final EntityManager entityManager;
    private static final Logger logger = Logger.getLogger(ServiceFactory.class);
    static {
        try {
            entityManager = EntityManagerHolder.getEntityManager();
        } catch (Throwable ex) {
            logger.error(ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static TrainService getTrainService() {
        return new TrainServiceImpl(new TrainDaoImpl(entityManager, Train.class));
    }
}
