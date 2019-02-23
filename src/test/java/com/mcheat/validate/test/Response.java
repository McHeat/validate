package com.mcheat.validate.test;

import com.mcheat.validate.handler.chain.response.BaseResponse;

/**
 * @Author McHeat
 * @Date 2019/1/31 16:45
 * @Version 1.0.0
 */
public class Response implements BaseResponse {

    private boolean isOk = false;


    public Response() {
        
    }

    @Override
    public boolean isOk() {
        return false;
    }


    public void setOk() {
        this.isOk = true;
    }
}
