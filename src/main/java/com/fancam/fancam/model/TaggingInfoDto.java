package com.fancam.fancam.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tagging")
@Entity
public class TaggingInfoDto {

    @EmbeddedId
    private TaggingInfoDtoId taggingInfoDtoId;
/*
    @Column(name = "fancamidx")
    private Long fancamIdx;


    @Column(name="tagidx")
    private Long tagIdx;*/

}
