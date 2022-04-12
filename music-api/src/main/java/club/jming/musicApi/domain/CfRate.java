package club.jming.musicApi.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Copyright (C), 2017-2022
 * Date: 2022-04-12 15:15
 * FileName: CfRate
 * Description: user-item评分表,用于kafka传输,采用json序列化
 * @author jmingXie
 */
@Data
public class CfRate implements Serializable {

    private Integer rateId;

    private Integer userId;

    private Integer musicId;

    /**
     * 评分
     */
    private Double cfRate;
}
