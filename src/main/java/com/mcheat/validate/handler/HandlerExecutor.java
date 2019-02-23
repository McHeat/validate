package com.mcheat.validate.handler;

import com.mcheat.validate.handler.chain.HandlerChain;
import com.mcheat.validate.handler.chain.response.BaseResponse;

import javax.validation.constraints.NotNull;
import java.util.function.Supplier;

/**
 * @Author McHeat
 * @Date 2019/1/31 16:12
 * @Version 1.0.0
 */
public class HandlerExecutor<S, T extends BaseResponse> implements Executor<S, T> {

    /**
     * 处理器链
     */
    private HandlerChain handlerChain;

    /**
     * 执行处理器链
     *
     * @param req      请求参数
     * @param supplier 返回类构造器
     * @return 校验结果
     */
    @Override
    public T execute(S req, Supplier<T> supplier) {
        assert handlerChain != null;
        try {
            T resp = supplier.get();
            // 初始化处理器链
            handlerChain.init();
            // 执行处理器链
            handlerChain.doHandler(req, resp);
            return resp;
        } finally {
            // 清空处理器链
            handlerChain.destory();
        }
    }

    /**
     * 设置处理器链
     *
     * @param handlerChain 处理器链
     */
    public void setHandlerChain(@NotNull HandlerChain handlerChain) {
        this.handlerChain = handlerChain;
    }

}
