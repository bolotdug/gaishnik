package com.inovus.gaishnik.service;

import com.inovus.gaishnik.model.CarNumber;

public interface CarNumberService {
    CarNumber next();
    CarNumber random();
    CarNumber createCarNumber(CarNumber carNumber);
}
