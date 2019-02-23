package com.mcheat.validate.handler.chain.response;

/**
 * @Author McHeat
 * @Date 2019/1/31 11:12
 * @Version 1.0.0
 */
public interface BaseResponse {

    /**
     * 验证是否通过
     *
     * @return true：验证通过， false：验证失败
     */
    boolean isOk();
}
