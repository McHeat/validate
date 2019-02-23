package com.mcheat.validate.handler.chain.handler;

import com.mcheat.validate.handler.chain.HandlerChain;
import com.mcheat.validate.handler.chain.response.BaseResponse;

/**
 * 处理器
 *
 * @Author McHeat
 * @Date 2019/1/31 11:26
 * @Version 1.0.0
 */
public interface Handler<S, T extends BaseResponse> {

    /**
     * 业务处理
     *
     * @param req          请求参数
     * @param resp         结果参数
     * @param handlerChain 处理器链
     */
    void handler(S req, T resp, HandlerChain handlerChain);

    /**
     * 是否允许异步
     *
     * @return true：允许异步；false：不允许异步
     */
    boolean isAsync();

}
