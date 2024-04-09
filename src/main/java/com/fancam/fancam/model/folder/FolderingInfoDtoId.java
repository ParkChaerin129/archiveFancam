package com.fancam.fancam.model.folder;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class FolderingInfoDtoId implements Serializable {

    private Long fancamidx;
    private Long folderidx;

}
