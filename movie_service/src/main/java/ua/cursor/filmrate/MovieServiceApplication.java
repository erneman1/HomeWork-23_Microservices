package ua.cursor.filmrate;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ua.cursor.filmrate.entity.Category;
import ua.cursor.filmrate.entity.Movie;
import ua.cursor.filmrate.entity.User;
import ua.cursor.filmrate.entity.enums.Role;
import ua.cursor.filmrate.repository.CategoryRepository;
import ua.cursor.filmrate.repository.UserRepository;
import ua.cursor.filmrate.service.MovieService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@RequiredArgsConstructor
public class MovieServiceApplication {

    private final UserRepository userRepository;
    private final MovieService movieService;
    private final CategoryRepository categoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(MovieServiceApplication.class, args);
    }

    @PostConstruct
    void aaa() {
        userRepository.save(new User(
                null,
                "user",
                "user",
                "$2y$12$7AcCI6hb8d49xum72Eo3FuLXKsGHm7lWswiHmR29no9bfYe8/me5a",
                Role.ROLE_USER
        ));

        userRepository.save(new User(
                null,
                "admin",
                "admin",
                "$2y$12$1C2lTYmL2As6VnX4BggJdeEZWo5u1GNB56HsWwZY8.aarE06g/JKu",
                Role.ROLE_ADMIN
        ));

        categoryRepository.save(new Category(null, "Horror", new HashSet<>()));
        categoryRepository.save(new Category(null, "Detective", new HashSet<>()));
        categoryRepository.save(new Category(null, "Drama", new HashSet<>()));
        categoryRepository.save(new Category(null, "Comedy", new HashSet<>()));
        categoryRepository.save(new Category(null, "Fantastic", new HashSet<>()));
        categoryRepository.save(new Category(null, "Fantasy", new HashSet<>()));
        categoryRepository.save(new Category(null, "Thriller", new HashSet<>()));
        categoryRepository.save(new Category(null, "Action", new HashSet<>()));
        categoryRepository.save(new Category(null, "Travel", new HashSet<>()));

        movieService.saveMovie(
                new Movie(
                        null,
                        "The Devil All The Time",
                        "Tom Holland",
                        "Sinister characters converge around a young man devoted to protecting those he loves in a postwar backwoods town teeming with corruption and brutality.",
                        0,
                        new ArrayList<>(),
                        Set.copyOf(categoryRepository.findAll())
                )
        );

        movieService.saveMovie(
                new Movie(
                        null,
                        "The Godfather",
                        "Francis Ford Coppola",
                        "Francis Ford Coppola's epic features Marlon Brando in his Oscar-winning role as the patriarch of the Corleone family. Director Coppola paints a chilling portrait of the Sicilian clan's rise and near fall from power in America, masterfully balancing the story between the Corleone's family life and the ugly crime business in which they are engaged. Based on Mario Puzo's best-selling novel and featuring career-making performances by Al Pacino, James Caan and Robert Duvall, this searing and brilliant film garnered ten Academy Award nominations, and won three including Best Picture of 1972.",
                        0,
                        new ArrayList<>(),
                        Set.of(categoryRepository.findById(3L).get(), categoryRepository.findById(7L).get())
                )
        );

        movieService.saveMovie(
                new Movie(
                        null,
                        "Vertigo",
                        "Alfred Hitchcock",
                        "Vertigo creates a dizzying web of mistaken identity, passion and murder after an acrophobic detective rescues a mysterious blonde from the bay.",
                        0,
                        new ArrayList<>(),
                        Set.of(categoryRepository.findById(7L).get())
                )
        );

        movieService.saveMovie(
                new Movie(
                        null,
                        "Parasite",
                        "Joon-ho Bong",
                        "Meet the Park Family: the picture of aspirational wealth. And the Kim Family, rich in street smarts but not much else. Be it chance or fate, these two houses are brought together and the Kims sense a golden opportunity. Masterminded by college-aged Ki-woo, the Kim children expediently install themselves as tutor and art therapist, to the Parks. Soon, a symbiotic relationship forms between the two families. The Kims provide “indispensable” luxury services while the Parks obliviously bankroll their entire household. When a parasitic interloper threatens the Kims’ newfound comfort, a savage, underhanded battle for dominance breaks out, threatening to destroy the fragile ecosystem between the Kims and the Parks.",
                        0,
                        new ArrayList<>(),
                        Set.of(categoryRepository.findById(3L).get(), categoryRepository.findById(7L).get(), categoryRepository.findById(4L).get())
                )
        );

    }
}
