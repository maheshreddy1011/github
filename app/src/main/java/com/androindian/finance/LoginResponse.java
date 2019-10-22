package com.androindian.finance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("data")
    @Expose
    private LoginresArray data;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public LoginresArray getData() {
        return data;
    }

    public void setData(LoginresArray data) {
        this.data = data;
    }

}
