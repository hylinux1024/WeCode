package net.angrycode.wehub.bean;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import net.angrycode.wehub.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lancelot on 2016/12/3.
 */

public class GsonBuilder {

    /**
     * 解析数组[{}]
     *
     * @param jsonString
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> buildArray(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<>();
        try {
            Gson gson = new Gson();
            JsonArray array = new JsonParser().parse(jsonString).getAsJsonArray();
            for (JsonElement jsonElement : array) {
                list.add(gson.fromJson(jsonElement, cls));
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return list;
    }

    /**
     * 对象转换成json字符串
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    /**
     * 将Json数据解析成相应的映射对象
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T parseJson(String json, Class<T> type) {
        Gson gson = new Gson();
        T result = gson.fromJson(json, type);
        return result;
    }

}
