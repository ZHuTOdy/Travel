package com.travel.pojo;

/**
 * @ClassName ResultInfo
 * @Description TODO 返回结果信息
 * @Author TOdyZHu
 * @Date 2019-07-29 11:11
 **/
public class ResultInfo {
    private boolean flag;
    private Object data;
    private String errorMsg;

    public ResultInfo() {
    }

    public ResultInfo(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
