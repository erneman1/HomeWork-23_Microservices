package com.cursor.hippolita.rating_service.controller;

import com.cursor.hippolita.rating_service.entity.Rate;
import com.cursor.hippolita.rating_service.services.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
@RequiredArgsConstructor
public class RateController {

    private final RateService rateService;

    @GetMapping("/{rate_id}")
    public Rate getRateById(@PathVariable(value = "rate_id") long rateId){
        return rateService.getRateByIg(rateId);
    }

    @GetMapping("/all")
    public List<Rate> getAllRate(){
        return rateService.getAllRates();
    }

    @PostMapping("save")
    public Rate saveRating(@RequestBody Rate rate){
        return rateService.saveRate(rate);
    }

    @PostMapping("/delete")
    void deleteRating(@RequestBody Rate rate){
        rateService.deleteRate(rate);
    }

}
