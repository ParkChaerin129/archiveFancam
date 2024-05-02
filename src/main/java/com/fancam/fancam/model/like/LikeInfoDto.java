package com.fancam.fancam.model.like;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Likes")
@Entity
public class LikeInfoDto {

    @EmbeddedId
    private LikeInfoDtoId likeInfoDtoId;

    @Column
    private String status;


}
