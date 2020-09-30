package ua.cursor.filmrate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.cursor.filmrate.dto.MovieDTO;
import ua.cursor.filmrate.entity.Movie;
import ua.cursor.filmrate.entity.Rate;
import ua.cursor.filmrate.service.CategoryService;
import ua.cursor.filmrate.service.MovieService;
import ua.cursor.filmrate.service.RateFeignClient;
import ua.cursor.filmrate.service.mapper.CategoryMapper;
import ua.cursor.filmrate.service.mapper.MovieMapper;

@Controller
@RequestMapping("/admin/movies")
@RequiredArgsConstructor
public class AdminController {
    private final MovieService movieService;
    private final CategoryService categoryService;
    private final RateFeignClient rateFeignClient;
    private final MovieMapper movieMapper;
    private final CategoryMapper categoryMapper;

    @PostMapping
    public String saveMovie(@ModelAttribute("movieDTO") MovieDTO movieDTO) {
        movieService.saveMovie(movieMapper.toMovieEntityFromDTO(movieDTO));
        return "redirect:/movies";
    }

    @PostMapping("/edit/{id}")
    public String updateMovie(@PathVariable("id") long id, @ModelAttribute("movie") Movie movie) {
        movieService.updateMovie(id, movie);
        return "redirect:/movies";
    }

    @PostMapping("/delete/{id}")
    public String deleteMovie(@PathVariable long id) {
        Movie movie = movieService.getMovieById(id);
        Rate rate = rateFeignClient.getRateById(movie.getRateId());
        rateFeignClient.deleteRating(rate);
        movieService.deleteMovie(id);
        return "redirect:/movies";
    }

    @GetMapping("/add")
    public String getAddMovieForm(Model model) {
        model.addAttribute("movie", new MovieDTO());
        model.addAttribute("categoriesAll", categoryMapper.toCategoryDTOs(categoryService.getAll()));
        return "add_movie";
    }

    @GetMapping("/edit/{id}")
    public String getUpdateMovieForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("movie", movieService.getMovieById(id));
        model.addAttribute("categoriesAll", categoryMapper.toCategoryDTOs(categoryService.getAll()));
        return "update_movie";
    }
}
