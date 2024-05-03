package com.fancam.fancam.model.folder;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Foldering")
@Entity
public class FolderingInfoDto {

    @EmbeddedId
    private FolderingInfoDtoId folderingInfoDtoId;

    @Column
    private String status;

}
