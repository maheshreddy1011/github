package com.androindian.finance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddGroupRes {
    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("call_back")
    @Expose
    private String callBack;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getCallBack() {
        return callBack;
    }

    public void setCallBack(String callBack) {
        this.callBack = callBack;
    }
}
