package com.snowflake.demo.snowflakedemo;

//import com.snowflake.springboot.service.UidGenService;

import com.alibaba.druid.support.json.JSONUtils;
import com.snowflake.springboot.service.UidGenService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Uid代码生成器，单元测试
 *
 * 功能测试范围：
 * 1、单个Uid测试
 * 2、批量Uid测试
 *
 * @author shenhongjun
 * @since 2020/5/22
 */
@RunWith(SpringRunner.class)
public class UidGenServiceTest extends SnowflakeDemoApplicationTests {

    /**
     * uid对象
     */
    @Resource
    private UidGenService uidGenService;

    /**
     * 数量(批量)
     */
    private int amount;

    /**
     * 前置条件
     *
     * 初始化批量生成Uid的数量
     */
    @Before
    public void before() {
        this.amount = 100;
    }

    /**
     * 单个Uid测试
     */
    @Test
    public void testFetchUid() {
        long uid = this.uidGenService.fetchUid();
        System.out.printf("生成snowflake的id值:%d", uid);
        Assert.assertTrue(uid > 0);
    }

    /**
     * 批量Uid测试
     */
    @Test
    public void testFetchUids() {
        List<Long> uids = this.uidGenService.fetchUids(this.amount);
        System.out.printf("批量生成snowflake的值:%s", JSONUtils.toJSONString(uids));
        Assert.assertTrue(uids.size() == this.amount);
    }
}
