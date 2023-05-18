package br.com.estudo.alurachallengebackendsemana1.dtos.video;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Category;
import br.com.estudo.alurachallengebackendsemana1.domain.entities.Video;
import br.com.estudo.alurachallengebackendsemana1.dtos.category.CategoryDTOSimple;
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
    private Long categoryId;

    public VideoDTO(Video video){
        this.id= video.getId();
        this.title= video.getTitle();
        this.description= video.getDescription();
        this.url= video.getUrl();
        this.categoryId = video.getCategory().getId();
    }

}
