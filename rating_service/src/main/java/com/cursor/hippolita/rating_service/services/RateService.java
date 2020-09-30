package com.cursor.hippolita.rating_service.services;

import com.cursor.hippolita.rating_service.entity.Rate;
import com.cursor.hippolita.rating_service.repository.RateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RateService {

    private final RateRepository rateRepository;

    public Rate getRateByIg(long id){
        return rateRepository.findById(id);
    }

    public List<Rate> getAllRates(){
        return rateRepository.findAll();
    }

    public Rate saveRate(Rate rate){
        return rateRepository.save(rate);
    }

    public void deleteRate(Rate rate){
        rateRepository.delete(rate);
    }

}
