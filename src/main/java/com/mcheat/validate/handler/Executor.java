package com.mcheat.validate.handler;

import com.mcheat.validate.handler.chain.response.BaseResponse;

import java.util.function.Supplier;

/**
 * 执行器
 *
 * @Author McHeat
 * @Date 2019/2/1 14:47
 * @Version 1.0.0
 */
public interface Executor<S, T extends BaseResponse> {

    /**
     * 执行处理器链
     *
     * @param req      请求参数
     * @param supplier 返回类构造器
     * @return 校验结果
     */
    T execute(S req, Supplier<T> supplier);
}
