package ua.cursor.filmrate.dto;

import lombok.*;
import ua.cursor.filmrate.dto.base.CategoryBaseDTO;
import ua.cursor.filmrate.entity.Rate;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieWithRateDto{

    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String director;

    @NonNull
    private String description;

    private Rate rate;

    private Set<CategoryBaseDTO> categories = new HashSet<>();

}



