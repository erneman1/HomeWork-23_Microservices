package ua.cursor.filmrate.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.cursor.filmrate.dto.MovieWithRateDto;
import ua.cursor.filmrate.entity.Movie;
import ua.cursor.filmrate.service.RateFeignClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MovieWithRateMapper {

    private final RateFeignClient rateFeignClient;
    private final CategoryMapper categoryMapper;

    public MovieWithRateDto toMovieWithRateDTO (Movie movie){
        MovieWithRateDto movieWithRateDto = new MovieWithRateDto();
        movieWithRateDto.setId(movie.getId());
        movieWithRateDto.setName(movie.getName());
        movieWithRateDto.setDirector(movie.getDirector());
        movieWithRateDto.setDescription(movie.getDescription());
        movieWithRateDto.setRate(rateFeignClient.getRateById(movie.getRateId()));
        movieWithRateDto.setCategories(
                Set.copyOf(
                        categoryMapper.toCategoryBaseDTOs(
                                new ArrayList<>(movie.getCategories())
                        )
                )
        );


        return movieWithRateDto;
    }

    public List<MovieWithRateDto> toMovieWithRateDTOs(List<Movie> movieList){
        List<MovieWithRateDto> list1 = new ArrayList<>();
        movieList.forEach(System.out::println);

        movieList.forEach(movie -> {
            list1.add(toMovieWithRateDTO(movie));
        });
        return list1;
    }
}
