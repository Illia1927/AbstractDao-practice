package mate.academy.service.factory;

import mate.academy.dao.daoImpl.TrainDaoImpl;
import mate.academy.model.Train;
import mate.academy.service.TrainService;
import mate.academy.service.serviceImpl.TrainServiceImpl;
import mate.academy.utill.EntityManagerHolder;

import javax.persistence.EntityManager;

public class ServiceFactory {
    private static final EntityManager entityManager;

    static {
        try {
            entityManager = EntityManagerHolder.getEntityManager();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static TrainService getTrainService() {
        return new TrainServiceImpl(new TrainDaoImpl(entityManager, Train.class));
    }
}
