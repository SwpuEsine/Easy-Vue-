package com.puhui8.exception;

/**
 * 自定义异常
 *
 * @author
 * @create 2018-11-27 下午5:36
 **/
public class CustomException extends RuntimeException {
    /**
     * 说明：因为某些业务需要进行业务回滚。但spring的事务只针对RuntimeException的进行回滚操作。所以需要回滚就要继承RuntimeException。
     */
    private String id;

    public CustomException(String id){
        super("id 不能为空");
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
