package com.ice.campus.common.core.exception;


import com.ice.campus.common.core.constant.ErrorCode;
import lombok.Getter;
import org.apache.dubbo.rpc.RpcException;

import java.io.Serial;
import java.io.Serializable;

/**
 * 自定义业务异常类
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/3/9 18:06
 */
@Getter
public class BusinessRpcException extends RpcException implements Serializable {

    @Serial
    private static final long serialVersionUID = 2042011481468986419L;
    /**
     * 错误码
     */
    private final int code;

    public BusinessRpcException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessRpcException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessRpcException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

}
