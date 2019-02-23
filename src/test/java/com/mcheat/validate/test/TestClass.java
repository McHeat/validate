package com.mcheat.validate.test;

import com.mcheat.validate.handler.HandlerExecutor;
import com.mcheat.validate.handler.chain.HandlerChain;
import com.mcheat.validate.handler.chain.handler.Handler;
import com.mcheat.validate.handler.chain.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author McHeat
 * @Date 2019/1/31 16:27
 * @Version 1.0.0
 */
@Slf4j
public class TestClass {

    @Test
    public void test() {
        HandlerChain handlerChain = new HandlerChain();

        Handler handler = new ParamHandler();

        handlerChain.addHandler(handler);
        Handler handler2 = new NullHandler();
        handlerChain.addHandler(handler2);

        HandlerExecutor enterHandler = new HandlerExecutor<Object, BaseResponse>();
        enterHandler.setHandlerChain(handlerChain);
        Response resp = (Response) enterHandler.execute(null, () -> new Response());


        log.info("验证结果为：{}", resp.isOk());
    }

}
