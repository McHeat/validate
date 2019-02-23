package com.mcheat.validate.handler.chain.handler;

import com.mcheat.validate.handler.TaskThreadPoolExecutor;
import com.mcheat.validate.handler.chain.HandlerChain;
import com.mcheat.validate.handler.chain.response.BaseResponse;

/**
 * @Author McHeat
 * @Date 2019/2/1 10:05
 * @Version 1.0.0
 */
public class AbstractHandler<S, T extends BaseResponse> implements Handler<S, T> {

    /**
     * 业务处理
     *
     * @param req          请求参数
     * @param resp         结果参数
     * @param handlerChain 处理器链
     */
    @Override
    public final void handler(S req, T resp, HandlerChain handlerChain) {
        if (isAsync()) {
            /**
             * TODO 处理都会进行resp的修改，在修改isOk()为false时，如何阻止其他handler的处理并立即返回？
             * 未有好的实施方案？
             */
            TaskThreadPoolExecutor.getExecutor().execute(() -> doHandler(req, resp));
            handlerChain.doHandler(req, resp);
            if (!resp.isOk()) {
                return;
            }
        } else {
            doHandler(req, resp);
            if (resp.isOk()) {
                handlerChain.doHandler(req, resp);
            }
        }


    }

    public void doHandler(S req, T resp) {

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
