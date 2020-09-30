package ua.cursor.filmrate.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Rate implements Comparable<Rate>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private Long votesCount;
    @NonNull
    private Double rateValue;

    @Override
    public int compareTo(Rate o) {
        return Double.compare(rateValue, o.getRateValue());
    }
}
