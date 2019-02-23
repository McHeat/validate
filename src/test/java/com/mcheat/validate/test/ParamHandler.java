package com.mcheat.validate.test;

import com.mcheat.validate.handler.chain.HandlerChain;
import com.mcheat.validate.handler.chain.annotation.Order;
import com.mcheat.validate.handler.chain.handler.AbstractHandler;
import com.mcheat.validate.utils.AnnotationValidateUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author McHeat
 * @Date 2019/1/31 16:43
 * @Version 1.0.0
 */
@Slf4j
@Order(priority = 3)
public class ParamHandler extends AbstractHandler<Object,Response> {

    public void doHandler(Object obj, Response resp) {
        try {
            AnnotationValidateUtils.validate(obj);
            log.info("参数验证通过");
            Response response = new Response();
            response.setOk();
        } catch (Exception e) {
            log.error("参数校验失败", e);
        }
    }

    /**
     * 是否允许异步
     *
     * @return true：允许异步；false：不允许异步
     */
    @Override
    public boolean isAsync() {
        return true;
    }
}
