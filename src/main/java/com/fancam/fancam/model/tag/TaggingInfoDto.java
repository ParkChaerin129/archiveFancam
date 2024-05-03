package com.fancam.fancam.model.tag;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tagging")
@Entity
public class TaggingInfoDto {

    @EmbeddedId
    private TaggingInfoDtoId taggingInfoDtoId;

    @Column
    private String status;
/*
    @Column(name = "fancamidx")
    private Long fancamIdx;


    @Column(name="tagidx")
    private Long tagIdx;*/

}
