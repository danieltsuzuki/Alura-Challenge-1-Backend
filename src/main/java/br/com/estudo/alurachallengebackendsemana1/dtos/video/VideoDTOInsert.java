package br.com.estudo.alurachallengebackendsemana1.dtos.video;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Video;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@NoArgsConstructor
public class VideoDTOInsert {
    @NotBlank
    @Length(max = 100)
    private String title;
    @NotBlank
    @Length(max = 255)
    private String description;
    @NotBlank
    @Length(max = 255)
    @URL
    private String url;
    private Long categoryId;

    public VideoDTOInsert(Video video) {
        this.title = video.getTitle();
        this.description = video.getDescription();
        this.url = video.getUrl();
        this.categoryId = video.getCategory().getId();
    }
}
