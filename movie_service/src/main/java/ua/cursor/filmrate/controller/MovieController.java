package ua.cursor.filmrate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.cursor.filmrate.dto.FilterSelectedCategories;
import ua.cursor.filmrate.dto.MovieWithRateDto;
import ua.cursor.filmrate.dto.ReviewDTO;
import ua.cursor.filmrate.entity.Movie;
import ua.cursor.filmrate.entity.RateValue;
import ua.cursor.filmrate.service.CategoryService;
import ua.cursor.filmrate.service.MovieService;
import ua.cursor.filmrate.service.RateFeignClient;
import ua.cursor.filmrate.service.mapper.CategoryMapper;
import ua.cursor.filmrate.service.mapper.MovieWithRateMapper;
import ua.cursor.filmrate.service.mapper.ReviewMapper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final CategoryService categoryService;
    private final RateFeignClient rateFeignClient;
    private final ReviewMapper reviewMapper;
    private final CategoryMapper categoryMapper;
    private final MovieWithRateMapper movieWithRateMapper;

    @GetMapping
    public String getAllMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("filtered", new FilterSelectedCategories());
        model.addAttribute("rate", rateFeignClient);
        return "home";
    }

    @GetMapping("/sorted")
    public String getAllMoviesSortedByRating(Model model) {
        List<MovieWithRateDto> movieDTOs = movieWithRateMapper.toMovieWithRateDTOs(movieService.getAllMovies())
                .stream()
                .sorted(Comparator.comparing(MovieWithRateDto::getRate).reversed()).collect(Collectors.toList());
        model.addAttribute("movies", movieDTOs);
        return "sorted";
    }

    @GetMapping("/{id}")
    public String getMovieById(@PathVariable(value = "id") long id, Model model) {
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("reviews", movie.getReviews());
        model.addAttribute("rate", rateFeignClient.getRateById(movie.getRateId()));

        return "details";
    }

    @PostMapping("/{movieId}/add-review")
    public String addReview(@PathVariable("movieId") long movieId, @ModelAttribute("newRate") RateValue rateValue, ReviewDTO reviewDTO, Model model) {

        if (!reviewDTO.getMessage().isEmpty()) {
            Movie movie = movieService.getMovieById(movieId);
            movie.getReviews().add(reviewMapper.toReviewEntityFromDTO(reviewDTO));
            movieService.updateMovie(movie);
        }
        if (rateValue != null && rateValue.getValue() != 0) {
            movieService.addRate(movieId, rateValue.getValue());
        }
        return "redirect:/movies/" + movieId;
    }

    @GetMapping("/{movieId}/add-review")
    public String getReviewForm(@PathVariable long movieId, Model model) {
        model.addAttribute("review", new ReviewDTO());
        model.addAttribute("newRate", new RateValue());
        model.addAttribute("movie_id", movieId);
        return "review_form";
    }

    @PostMapping("/category")
    public String getAllInCategory(@ModelAttribute("filtered") FilterSelectedCategories filterSelectedCategories, Model model) {
        model.addAttribute("movies",
                movieService.getAllByCategoriesContains(
                        categoryMapper
                                .toCategoryEntitiesFromBaseDTOs(
                                        filterSelectedCategories
                                                .getCategories()))

        );
        model.addAttribute("categories", categoryMapper.toCategoryBaseDTOs(categoryService.getAll()));
        model.addAttribute("rate", rateFeignClient);
        model.addAttribute("filtered", filterSelectedCategories);
        return "filter";
    }

}
