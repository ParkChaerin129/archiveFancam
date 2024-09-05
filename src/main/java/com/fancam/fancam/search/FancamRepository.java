package com.fancam.fancam.search;

import com.fancam.fancam.model.FancamInfoDto;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface FancamRepository extends JpaRepository<FancamInfoDto,Long> {

    @Query("SELECT f, SUM(CASE WHEN l.status = 'ACTIVE' THEN 1 ELSE 0 END) FROM FancamInfoDto f LEFT JOIN LikeInfoDto l ON f.fancamidx = l.likeInfoDtoId.fancamidx WHERE f.status='ACTIVE' GROUP BY f.fancamidx")
    List<Object[]> howManyLikesFancam();

    @Query("SELECT f, SUM(CASE WHEN l.status = 'ACTIVE' THEN 1 ELSE 0 END) FROM FancamInfoDto f LEFT JOIN LikeInfoDto l ON f.fancamidx = l.likeInfoDtoId.fancamidx WHERE f.status='ACTIVE' AND f.fancamidx = :fancamidx GROUP BY f.fancamidx")
    List<Object[]> fancamInfo(@Param("fancamidx") Long fancamidx);

    @Query("SELECT t.tagName FROM TagInfoDto t JOIN TaggingInfoDto tg ON t.tagIdx = tg.taggingInfoDtoId.tagidx JOIN FancamInfoDto f ON tg.taggingInfoDtoId.fancamidx = f.fancamidx WHERE f.fancamidx = :fancamidx")
    List<String> findTagNamesByPostId(@Param("fancamidx") Long fancamidx);

    List<FancamInfoDto> findByNameContaining(String namePart);

}
