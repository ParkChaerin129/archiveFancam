package com.fancam.fancam.model.like;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class LikeInfoDtoId implements Serializable {

    private Long useridx;
    private Long fancamidx;

}
