package club.jming.musicAdmin.exception;

import club.jming.musicCommon.domain.R;
import club.jming.musicCommon.exception.BizCodeEnume;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一异常处理
 */
@Slf4j
@RestControllerAdvice(basePackages = "club.jming.musicAdmin.controller")
public class MusicAdminControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        Map<String, String> map = new HashMap<>();
        bindingResult.getFieldErrors().forEach((item) -> {
            map.put(item.getField(), item.getDefaultMessage());
        });
        log.error("数据校验advice补祸异常:{},异常类型:{}", exception.getMessage(), exception.getClass());
        return R.error(BizCodeEnume.VALID_EXCEPTION.getCode(), BizCodeEnume.VALID_EXCEPTION.getMsg()).put("data", map);
    }

    /**
     * IO异常
     * @param throwable
     * @return
     */
    @ExceptionHandler(value = IOException.class)
    public R handleException(IOException throwable) {
        throwable.printStackTrace();
        return R.error(BizCodeEnume.IO_EXCEPTION.getCode(), BizCodeEnume.IO_EXCEPTION.getMsg());
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable) {
        throwable.printStackTrace();
        return R.error(BizCodeEnume.UNKNOW_EXCEPTION.getCode(), BizCodeEnume.UNKNOW_EXCEPTION.getMsg());
    }
}