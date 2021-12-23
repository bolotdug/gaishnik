package com.inovus.gaishnik.service;

import com.inovus.gaishnik.exception.LastNumberException;
import com.inovus.gaishnik.model.CarNumber;

public interface CarNumberService {
    CarNumber next() throws LastNumberException;
    CarNumber random();
    CarNumber createCarNumber(CarNumber carNumber);
//    CarNumber getCurrentCarNumber();
//    void setCurrentCarNumber(CarNumber currentCarNumber);
}
