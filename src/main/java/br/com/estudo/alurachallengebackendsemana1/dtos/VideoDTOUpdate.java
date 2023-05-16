package br.com.estudo.alurachallengebackendsemana1.dtos;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Video;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VideoDTOUpdate {

    private String title;

    private String description;


    public VideoDTOUpdate(Video video) {
        this.title = video.getTitle();
        this.description = video.getDescription();
    }

}
