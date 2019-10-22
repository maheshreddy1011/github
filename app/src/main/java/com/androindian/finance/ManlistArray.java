package com.androindian.finance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ManlistArray {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("stype")
    @Expose
    private String stype;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("enddate")
    @Expose
    private String enddate;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("status")
    @Expose
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
