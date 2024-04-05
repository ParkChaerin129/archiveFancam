package com.fancam.fancam.model;

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
    private Long tagIdx;
    @Column
    private String tagName;

}
