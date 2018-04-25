package com.hz;



/**
 *
 */
public class ErrorInfo {
    /**
     * 默认错误码
     */
    public static final int UNKNOWN_ERROR_CODE = 500;

    private int returnCode;
    private String returnText;

    public ErrorInfo() {
    }

    public ErrorInfo(String returnText) {
        this.returnCode = UNKNOWN_ERROR_CODE;
        this.returnText = returnText;
    }

    public ErrorInfo(int returnCode, String returnText) {
        this.returnCode = returnCode;
        this.returnText = returnText;
    }

    public String getReturnText() {
        return returnText;
    }

    public void setReturnText(String returnText) {
        this.returnText = returnText;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String toString() {
        return returnCode + "-" + returnText;
    }
}
