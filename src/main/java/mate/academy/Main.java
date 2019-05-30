package mate.academy;

import mate.academy.model.Train;
import mate.academy.service.TrainService;
import mate.academy.service.factory.ServiceFactory;

public class Main {
    public static void main(String[] args) {
        TrainService trainService = ServiceFactory.getTrainService();
        Train hundai = Train
                .builder()
                .name("Hyundai")
                .number(321)
                .adressFrom("Zaporizhia")
                .adressTo("Kiev")
                .build();
        trainService.create(hundai);
        hundai.setName("Tarpan");
        trainService.update(hundai);
        System.out.println(trainService.getTrains());
        System.out.println(trainService.readById(1L));
        trainService.delete(hundai);
        System.out.println(trainService.getTrains());
    }
}
