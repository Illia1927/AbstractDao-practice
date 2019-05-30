package mate.academy.service;

import mate.academy.model.Train;

import java.util.List;

public interface TrainService {
    Train create(Train train);

    Train readById(Long id);

    void update(Train train);

    void delete(Train train);

    void deleteById(Long id);

    List<Train> getTrains();
}
