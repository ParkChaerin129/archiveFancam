package com.fancam.fancam.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Fancam")
@Entity
public class FancamInfoDto {

    @Column(name="Name")
    private String name;
    @Column
    private String date;
    @Column
    private String member;
    @Column
    private String fancam_url;
    @Column
    private String status;
    //@Column(name="createAt")
    //private String create_at;
    //@Column(name="updateAt")
    //private String update_at;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fancamidx;

}
