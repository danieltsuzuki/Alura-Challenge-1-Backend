package br.com.estudo.alurachallengebackendsemana1.dtos.video;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Video;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VideoDTOList {

    private Long id;

    private String title;

    private String url;

    private Long categoryId;

    public VideoDTOList(Video video) {
        this.id = video.getId();
        this.title = video.getTitle();
        this.url = video.getUrl();
        this.categoryId = video.getCategory().getId();
    }
}
