package club.jming.musicCommon.exception;

public enum BizCodeEnume {

    /**
     * 系统未知异常
     */
    UNKNOW_EXCEPTION(10000,"系统未知异常"),

    /**
     * 参数格式校验异常
     */
    VALID_EXCEPTION(10001,"参数格式校验失败"),

    /**
     * IO异常
     */
    IO_EXCEPTION(10002,"IO异常");

    /**
     * 错误码
     */
    private int code;
    /**
     * 错误消息
     */
    private String msg;
    BizCodeEnume(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
