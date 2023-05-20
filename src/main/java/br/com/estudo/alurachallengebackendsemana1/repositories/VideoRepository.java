package br.com.estudo.alurachallengebackendsemana1.repositories;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long> {

    Optional<Video> findByIdAndActiveTrue(Long id);

    @Query()
    List<Video> findAllByActiveTrue();

    @Query(value = "SELECT * FROM videos WHERE category_id = :categoryId AND active = true",nativeQuery = true)
    List<Video> findAllByCategoryIdAndActiveTrue(@Param("categoryId") Long id);
}
