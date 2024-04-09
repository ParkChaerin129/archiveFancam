package com.fancam.fancam.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
@Entity
public class UserInfoDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="useridx")
    private Long userIdx;

    @Column
    private String nickname;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String pwd;

}
