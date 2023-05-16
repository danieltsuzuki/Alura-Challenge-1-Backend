package br.com.estudo.alurachallengebackendsemana1.domain.entities;

import br.com.estudo.alurachallengebackendsemana1.dtos.video.VideoDTOInsert;
import jakarta.persistence.*;
import lombok.*;

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
    private String title;
    private String description;
    private String url;
    private Boolean active = true;

    public Video(VideoDTOInsert video) {
        this.url = video.getUrl();
        this.title = video.getTitle();
        this.description = video.getDescription();
    }
}
