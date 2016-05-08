package me.zhukov.votepic.data;

import com.google.gson.annotations.SerializedName;

/**
 * @author Michael Zhukov
 */
public class Meta {

    @SerializedName("status")
    private int status;

    @SerializedName("msg")
    private String message;

    public Meta(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
