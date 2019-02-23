package com.mcheat.validate.test;

import com.mcheat.validate.handler.chain.annotation.Order;
import com.mcheat.validate.handler.chain.handler.AbstractHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author McHeat
 * @Date 2019/1/31 18:05
 * @Version 1.0.0
 */
@Slf4j
@Order(priority = 1)
public class NullHandler extends AbstractHandler<Object, Response> {

    @Override
    public void doHandler(Object obj, Response resp) {
        log.info("空处理器，无任何动作");
    }

    /**
     * 是否允许异步
     *
     * @return true：允许异步；false：不允许异步
     */
    @Override
    public boolean isAsync() {
        return false;
    }
}
