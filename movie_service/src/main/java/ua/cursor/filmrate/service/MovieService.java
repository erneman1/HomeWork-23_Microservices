package ua.cursor.filmrate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.cursor.filmrate.entity.Category;
import ua.cursor.filmrate.entity.Movie;
import ua.cursor.filmrate.entity.Rate;
import ua.cursor.filmrate.repository.MovieRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final RateFeignClient rateFeignClient;

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Movie getMovieById(long id){
        return movieRepository.findById(id).get();
    }

    public void saveMovie(Movie movie){
        Rate rate = rateFeignClient.saveRating(new Rate(null, 0L, 0.0));
        movie.setRateId(rate.getId());
        movieRepository.save(movie);
    }

    public void updateMovie(Movie newMovie){
        movieRepository.save(newMovie);
    }

    public void updateMovie(long id, Movie newMovie){
        Movie movie = movieRepository.getById(id);
        movie.setName(newMovie.getName());
        movie.setDirector(newMovie.getDirector());
        movie.setDescription(newMovie.getDescription());
        movie.setCategories(newMovie.getCategories());
        movieRepository.save(movie);
    }

    public void deleteMovie(long id){
        movieRepository.deleteById(id);
    }

    public void addRate(long movieId, double rateValue){
        Movie movie = getMovieById(movieId);
        Rate rate = rateFeignClient.getRateById(movie.getRateId());
        double rateFromDB = rate.getRateValue();
        long votesCount = rate.getVotesCount();
        double tmpRate = rateFromDB * votesCount++ + rateValue;
        tmpRate /= votesCount;
        rate.setRateValue(tmpRate);
        rate.setVotesCount(votesCount);

        rateFeignClient.saveRating(rate);
    }

    public List<Movie> getAllByCategoriesContains(List<Category> categories){
        return movieRepository.getAllByCategoriesContains(categories);
    }

}
