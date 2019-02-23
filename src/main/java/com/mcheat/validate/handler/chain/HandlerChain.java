package com.mcheat.validate.handler.chain;

import com.mcheat.validate.handler.chain.annotation.Order;
import com.mcheat.validate.handler.chain.handler.Handler;
import com.mcheat.validate.handler.chain.response.BaseResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理链实现
 *
 * @Author McHeat
 * @Date 2019/1/31 13:28
 * @Version 1.0.0
 */
public class HandlerChain {

    public static ThreadLocal<Integer> CUR_HANDLER_IDX = ThreadLocal.withInitial(() -> 0);

    private List<Handler> handlers = new ArrayList<>();

    public void init() {
        CUR_HANDLER_IDX.set(0);
    }

    public void doHandler(Object req, BaseResponse resp) {
        int curHandlerIdx = CUR_HANDLER_IDX.get();
        if (curHandlerIdx < handlers.size()) {
            CUR_HANDLER_IDX.set(curHandlerIdx + 1);
            Handler handler = handlers.get(curHandlerIdx);
            handler.handler(req, resp, this);
        }
    }

    public void destory() {
        CUR_HANDLER_IDX.remove();
    }

    /**
     * 批量添加处理器
     *
     * @param handlers 处理器列表
     * @return 处理器链
     */
    public HandlerChain addHandlers(List<Handler> handlers) {
        for (Handler handler : handlers) {
            this.handlers.add(handler);
        }
        return this;
    }

    /**
     * 在处理链中添加新的处理器
     *
     * @param handler 处理器
     * @return 处理链
     */
    public HandlerChain addHandler(Handler handler) {
        int handlerOrder = handlerPriority(handler);

        if (handlers.size() > 0) {
            handlers.add(searchHandlerIdx(handlerOrder), handler);
        } else {
            handlers.add(handler);
        }
        return this;
    }

    /**
     * @param priority
     * @return
     */
    private int searchHandlerIdx(int priority) {
        // 最终返回的插入索引
        int idx = 0;
        // 最小索引值
        int lo = 0, hi = handlers.size() - 1;
        while (lo <= hi) {
            // 中间索引值
            int mid = lo + (hi - lo) / 2;
            // 中间索引值对应的优先级
            int midPriority = handlerPriority(handlers.get(mid));

            // 新优先级小于当前优先级，则查找当前处理器之后的优先级情况
            if (priority < midPriority) {
                if (lo == hi) idx = mid + 1;
                lo = mid + 1;
            }
            // 新优先级大于当前优先级，则查找当前处理器之前的优先级情况
            else if (priority > midPriority) {
                if (lo == hi) idx = mid;
                hi = mid - 1;
            } else {
                return mid;
            }
        }

        return idx;
    }

    /**
     * 获取处理器的优先级
     *
     * @param handler 处理器
     * @return 处理器的优先级
     */
    private int handlerPriority(Handler handler) {
        Order orderAnno = handler.getClass().getAnnotation(Order.class);
        return orderAnno == null ? -1 : orderAnno.priority();
    }

}
