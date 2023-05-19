package br.com.estudo.alurachallengebackendsemana1.domain.entities;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.enums.Colour;
import br.com.estudo.alurachallengebackendsemana1.dtos.category.CategoryDTO;
import br.com.estudo.alurachallengebackendsemana1.dtos.category.CategoryDTOInsert;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Category")
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private Colour colour;
    @OneToMany(mappedBy = "category")
    private List<Video> list = new ArrayList<>();

    public Category(CategoryDTOInsert category) {
        this.title = category.getTitle();
        this.colour = category.getColour();
    }

    public Category(CategoryDTO category) {
        this.id = category.getId();
        this.colour = category.getColour();
        this.title = category.getTitle();
    }
}
