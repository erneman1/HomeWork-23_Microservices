package ua.cursor.filmrate.dto.base;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class UserBaseDTO {
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String login;
    @NonNull
    private String password;
}
