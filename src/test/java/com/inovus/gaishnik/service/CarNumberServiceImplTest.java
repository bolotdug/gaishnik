package com.inovus.gaishnik.service;

import com.inovus.gaishnik.GaishnikApplicationTests;
import com.inovus.gaishnik.exception.LastNumberException;
import com.inovus.gaishnik.model.CarNumber;
import com.inovus.gaishnik.service.impl.CarNumberServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;

class CarNumberServiceImplTest extends GaishnikApplicationTests{

    @Autowired
    private CarNumberServiceImpl service;

    @Test
    void nextOnTheLastElement() throws LastNumberException {
        service.setCurrentCarNumber(createLastNumber());
        LastNumberException exception = assertThrows(LastNumberException.class, () -> service.next(), "Expected to return nothing");

        assertEquals("Last number", exception.getMessage());
    }

    @Test
    void randomTest() {
        CarNumber createdCarNumber = service.random();
    }

    @Test
    void createCarNumberTest() {

    }

    private CarNumber createLastNumber(){
        return CarNumber.builder()
                .number(999)
                .firstCharacter('Х')
                .secondCharacter('Х')
                .thirdCharacter('Х')
                .build();
    }
}