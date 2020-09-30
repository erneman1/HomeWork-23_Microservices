package ua.cursor.filmrate.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ua.cursor.filmrate.entity.Rate;

import javax.annotation.PostConstruct;
import java.util.List;

@FeignClient(name = "rating-service")
public interface RateFeignClient {
    @GetMapping("/rating/{rate_id}")
    Rate getRateById(@PathVariable(value = "rate_id") long rateId);

    @GetMapping("rating/all")
    List<Rate> getAllRate();

    @PostMapping("/rating/save")
    Rate saveRating(@ModelAttribute Rate rate);

    @PostMapping("/rating/delete")
    void deleteRating(@RequestBody Rate rate);
}
