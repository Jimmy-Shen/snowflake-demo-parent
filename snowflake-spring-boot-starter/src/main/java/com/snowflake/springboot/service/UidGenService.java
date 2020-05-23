package com.snowflake.springboot.service;

import java.util.List;

/**
 * 生成唯一ID服务接口
 *
 * @author shenhongjun
 * @since 2020/5/22
 */
public interface UidGenService {

    /**
     * 获取唯一ID
     *
     * @return 返回long类型，64位长整型
     */
    Long fetchUid();

    /**
     * 批量获取ID
     * @param amount 数量,最大数量不超过100
     * @return 返回批量ID集合
     */
    List<Long> fetchUids(int amount);
}
