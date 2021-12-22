package com.inovus.gaishnik.service.impl;

import com.inovus.gaishnik.model.CarNumber;
import com.inovus.gaishnik.repository.CarNumberRepository;
import com.inovus.gaishnik.service.CarNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class CarNumberServiceImpl implements CarNumberService {

    private final CarNumberRepository carNumberRepository;
    private CarNumber currentCarNumber;

    @Override
    public CarNumber next() {

        char[] letters = new char[]{'А', 'Е', 'Т', 'О', 'Р', 'Н', 'У', 'К', 'Х', 'С', 'В', 'М'};

        do {
            if (currentCarNumber == null) {
                currentCarNumber = new CarNumber("А000АА 116 RUS", 0, 'А', 'А', 'А');
                if (!carNumberRepository.existsById("А000АА 116 RUS")) {
                    createCarNumber(currentCarNumber);
                    return currentCarNumber;
                }
            }
            if (currentCarNumber.getNumber() == 999) {
                currentCarNumber.setNumber(0);
                if (currentCarNumber.getThirdCharacter().equals('Х')) {
                    currentCarNumber.setThirdCharacter('А');
                } else {

                }

            } else {
                currentCarNumber.setNumber(currentCarNumber.getNumber() + 1);
            }
        } while (carNumberRepository.existsById(currentCarNumber.toString()));


        createCarNumber(currentCarNumber);
        return currentCarNumber;
    }

    @Override
    public CarNumber random() {


        char[] letters = new char[]{'А', 'Е', 'Т', 'О', 'Р', 'Н', 'У', 'К', 'Х', 'С', 'В', 'М'};

        CarNumber randomCarNumber;
        do {
            int number = ThreadLocalRandom.current().nextInt(1000);
            Character firstCharacter = letters[ThreadLocalRandom.current().nextInt(12)];
            Character secondCharacter = letters[ThreadLocalRandom.current().nextInt(12)];
            Character thirdCharacter = letters[ThreadLocalRandom.current().nextInt(12)];
            randomCarNumber = CarNumber.builder()
                    .number(number)
                    .firstCharacter(firstCharacter)
                    .secondCharacter(secondCharacter)
                    .thirdCharacter(thirdCharacter)
                    .build();
        } while (carNumberRepository.existsById(randomCarNumber.toString()));
        createCarNumber(randomCarNumber);
//        currentCarNumber = randomCarNumber;
        return randomCarNumber;
    }

    @Override
    public CarNumber createCarNumber(CarNumber carNumber) {
        carNumber.setFullNumber(carNumber.toString());
        return carNumberRepository.save(carNumber);
    }
}
