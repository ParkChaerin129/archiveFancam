package com.fancam.fancam.model.tag;

import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tagname")
@Entity
public class TagInfoDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tagidx")
    private Long tagIdx;
    @Column
    private String tagName;

}
