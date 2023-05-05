package br.com.estudo.alurachallengebackendsemana1.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

@Entity(name = "Video")
@Table(name = "videos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Length(max = 100)
    private String title;
    @NotBlank
    @Length(max = 255)
    private String description;
    @NotBlank
    @URL
    @Length(max = 255)
    private String url;
}
