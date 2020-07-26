package com.mywhm.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Storage {
    private Long id; //id
    private Long productId; //产品id

    private Integer total; // 总库存
    private Integer used;  // 已有库存
    private Integer residue;// 剩余库存
}
