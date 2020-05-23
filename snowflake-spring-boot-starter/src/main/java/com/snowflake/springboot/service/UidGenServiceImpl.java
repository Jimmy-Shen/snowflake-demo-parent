package com.snowflake.springboot.service;

import com.snowflake.spring.UidGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成唯一ID服务实现类
 *
 * @author shenhongjun
 * @since 2020/5/21
 */
@Service
public class UidGenServiceImpl implements UidGenService {

    // 最大数量
    public static final int MAX_AMOUNT = 1000;

    /**
     *  dao operation
     */
    @Resource
    private UidGenerator uidGenerator;

    /**
     * 获取唯一ID
     *
     * @return 返回long类型，64位长整型
     */
    @Override
    public Long fetchUid() {
        return uidGenerator.getUID();
    }

    /**
     * 批量获取ID
     * @param amount 数量,最大数量不超过100
     * @return 返回批量ID集合
     */
    @Override
    public List<Long> fetchUids(int amount) {
        if (amount > MAX_AMOUNT) {
            throw new IllegalArgumentException(String.format("生成数量不能超过%d个", amount));
        }

        ArrayList<Long> uids = new ArrayList<Long>(amount);
        for (int i = 0; i < amount; i++) {
            uids.add(uidGenerator.getUID());
        }
        return uids;
    }
}
