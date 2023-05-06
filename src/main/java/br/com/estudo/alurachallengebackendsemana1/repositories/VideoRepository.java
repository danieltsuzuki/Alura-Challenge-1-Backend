package br.com.estudo.alurachallengebackendsemana1.repositories;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {

}
