package net.angrycode.wehub.bean;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import net.angrycode.wehub.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lancelot on 2016/12/17.
 */

public class Lang {
    private String key;
    private String name;

    public Lang() {
    }

    public Lang(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<Lang> buildLangs(String json) {
        List<Lang> list = new ArrayList<>();
        try {
            Gson gson = new Gson();
            gson.fromJson(json, Lang.class);
        } catch (JsonSyntaxException e) {
            LogUtils.log(e.getMessage());
        }
        return list;
    }
}
