package br.com.estudo.alurachallengebackendsemana1.dtos.video;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Video;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VideoDTO {

    private Long id;
    private String title;
    private String description;
    private String url;

    public VideoDTO(Video video){
        this.id= video.getId();
        this.title= video.getTitle();
        this.description= video.getDescription();
        this.url= video.getUrl();
    }

}
