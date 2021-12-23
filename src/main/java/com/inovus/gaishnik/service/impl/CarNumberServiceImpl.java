package com.inovus.gaishnik.service.impl;

import com.inovus.gaishnik.exception.LastNumberException;
import com.inovus.gaishnik.model.CarNumber;
import com.inovus.gaishnik.repository.CarNumberRepository;
import com.inovus.gaishnik.service.CarNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class CarNumberServiceImpl implements CarNumberService {

    private final CarNumberRepository carNumberRepository;
    private CarNumber currentCarNumber;

    public void setCurrentCarNumber(CarNumber currentCarNumber) {
        this.currentCarNumber = currentCarNumber;
    }

    public CarNumber getCurrentCarNumber() {
        return currentCarNumber;
    }

    @Override
    public CarNumber next() throws LastNumberException {

        char[] letters = new char[]{'А', 'Е', 'Т', 'О', 'Р', 'Н', 'У', 'К', 'Х', 'С', 'В', 'М'};
        Character[] letters1 = new Character[]{'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С','Т', 'У', 'Х'};
        List<Character> characterList = Arrays.asList(letters1);
        characterList.get(3);
        characterList.indexOf('А');

        if (currentCarNumber == null) {
            currentCarNumber = new CarNumber("А000АА 116 RUS", 0, 'А', 'А', 'А');
            if (!carNumberRepository.existsById("А000АА 116 RUS")) {
                createCarNumber(currentCarNumber);
                return currentCarNumber;
            }
        }

        do {
            if (currentCarNumber.getNumber() == 999) {
                currentCarNumber.setNumber(0);
                if (currentCarNumber.getThirdCharacter().equals('Х')) {
                    currentCarNumber.setThirdCharacter('А');
                    if (currentCarNumber.getSecondCharacter().equals('Х')) {
                        checkTheLastNumber();
                        currentCarNumber.setSecondCharacter('А');

                        int temp = characterList.indexOf(currentCarNumber.getFirstCharacter());
                        currentCarNumber.setFirstCharacter(characterList.get(++temp));
                    } else {
                        int temp = characterList.indexOf(currentCarNumber.getSecondCharacter());
                        currentCarNumber.setSecondCharacter(characterList.get(++temp));
                    }
                } else {
                    int temp = characterList.indexOf(currentCarNumber.getThirdCharacter());
                    currentCarNumber.setThirdCharacter(characterList.get(++temp));
                }

            } else {
                currentCarNumber.setNumber(currentCarNumber.getNumber() + 1);
            }
        } while (carNumberRepository.existsById(currentCarNumber.toString()));


        createCarNumber(currentCarNumber);
        return currentCarNumber;
    }

    private void checkTheLastNumber() throws LastNumberException {
        if (currentCarNumber.getFirstCharacter().equals('Х')){
            //check to the last element
            if (carNumberRepository.count() == 1726272){
                // TODO: throw OverLoadException
            }
            throw new LastNumberException("Last number");
        }
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
