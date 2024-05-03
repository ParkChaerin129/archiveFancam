package com.fancam.fancam.model.folder;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Folder")
@Entity
public class FolderInfoDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="folderidx")
    private Long folderIdx;

    @Column(name="useridx")
    private Long userIdx;

    @Column(name="foldername")
    private String folderName;

    @Column
    private String status;

}
