package mate.academy.service.serviceImpl;

import mate.academy.Test;
import mate.academy.dao.TrainDao;
import mate.academy.model.Train;
import mate.academy.service.TrainService;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TrainServiceImpl implements TrainService {
    private final TrainDao trainDao;
    private EntityManager entityManager;

    public TrainServiceImpl(TrainDao trainDao) {
        this.trainDao = trainDao;
    }

    @Override
    public Train create(Train train) {
        return trainDao.create(train);
    }

    @Override
    public Train readById(Long id) {
        return trainDao.readById(id);
    }

    @Override
    public void update(Train train) {
        trainDao.update(train);
    }

    @Override
    public void delete(Train train) {
        trainDao.delete(train);
    }

    @Override
    public void deleteById(Long id) {
        delete(readById(id));
    }

    @Override
    public List<Train> getTrains() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Train> cq = cb.createQuery(Train.class);
        Root<Train> rootEntry = cq.from(Train.class);
        CriteriaQuery<Train> all = cq.select(rootEntry);
        TypedQuery<Train> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }
}
