package br.com.estudo.alurachallengebackendsemana1.repositories;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long> {

    Optional<Video> findByIdAndActiveTrue(Long id);

    @Query()
    Page<Video> findAllByActiveTrue(Pageable pageable);

    @Query(value = "SELECT * FROM videos WHERE category_id = :categoryId AND active = true", nativeQuery = true)
    Page<Video> findAllByCategoryIdAndActiveTrue(@Param("categoryId") Long id, Pageable pageable);

    @Query(value = "SELECT * FROM videos WHERE title LIKE %:title% ", nativeQuery = true)
    Page<Video> findByTitleContaining(@Param("title") String title, Pageable pageable);

}
