package net.angrycode.wehub.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lancelot on 2016/12/17.
 */

public class Langs {
    @SerializedName("sel")
    private List<Lang> selList;
    @SerializedName("unsel")
    private List<Lang> unselList;

    public Langs() {
    }

    public Langs(List<Lang> selList, List<Lang> unselList) {
        this.selList = selList;
        this.unselList = unselList;
    }

    public List<Lang> getSelList() {
        return selList;
    }

    public void setSelList(List<Lang> selList) {
        this.selList = selList;
    }

    public List<Lang> getUnselList() {
        return unselList;
    }

    public void setUnselList(List<Lang> unselList) {
        this.unselList = unselList;
    }
}
