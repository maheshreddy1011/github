package com.androindian.finance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ManlistRes {

    @SerializedName("data")
    @Expose
    private List<ManlistArray> data = null;
    @SerializedName("response")
    @Expose
    private String response;

    public List<ManlistArray> getData() {
        return data;
    }

    public void setData(List<ManlistArray> data) {
        this.data = data;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
