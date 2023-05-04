package br.com.estudo.alurachallengebackendsemana1.domain.entities;

import jakarta.persistence.*;

@Entity(name = "Video")
@Table(name = "videos")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String url;
}
