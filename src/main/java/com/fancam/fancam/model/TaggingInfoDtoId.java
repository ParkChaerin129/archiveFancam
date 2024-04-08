package com.fancam.fancam.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class TaggingInfoDtoId implements Serializable {
    private Long fancamidx;
    private Long tagidx;

    // 생성자, 접근자, 설정자 등 추가
}