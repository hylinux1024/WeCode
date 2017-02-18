package net.angrycode.wehub.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lancelot on 2016/12/4.
 */

public class TagPrefUtils {
    private static final String FILE_NAME = "tag_file_name";
    private static final String KEY_TAG = "tag_key";

    /**
     * 获取Tags
     *
     * @param context
     * @return
     */
    public static String[] getTags(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        String tags = sp.getString(KEY_TAG, "");
        String[] list = new String[]{};
        if (!TextUtils.isEmpty(tags)) {
            list = tags.split(",");
        }
        return list;
    }

    public static void appendTags(Context context, String query) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        String[] tags = sp.getString(KEY_TAG, "").split(",");
        List<String> tagList = new ArrayList<>();
        for (int i = 0; i < tags.length; ++i) {
            if (!TextUtils.isEmpty(tags[i])) {
                tagList.add(tags[i]);
            }
        }
        if (!tagList.contains(query)) {
            tagList.add(query);
        }
        String tagStr = TextUtils.join(",", tagList);
        sp.edit().putString(KEY_TAG, tagStr).apply();
    }
}
