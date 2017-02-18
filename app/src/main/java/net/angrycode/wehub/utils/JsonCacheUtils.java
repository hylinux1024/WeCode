package net.angrycode.wehub.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.File;

/**
 * 接口缓存数据
 * Created by lancelot on 2016/12/3.
 */

public class JsonCacheUtils {

    /**
     * 读取
     *
     * @param lang
     * @return
     */
    public static String readFile(Context context,String lang) {
        if (TextUtils.isEmpty(lang)) {
            return null;
        }
        String fileName = getFileName(context, lang);
        String txt = FileUtils.readFile(fileName);
        return txt;
    }

    /**
     * @param json
     * @param lang
     */
    public static void write2File(Context context,String json, String lang) {
        String fileName = getFileName(context, lang);
        FileUtils.write2File(json, fileName);

    }

    /**
     * @param context
     * @param lang
     * @return
     */
    public static String getFileName(@NonNull Context context, @NonNull String lang) {
        File cacheDir = context.getCacheDir();
        String fileName = MD5Utils.MD5(lang);
        return new File(cacheDir, fileName).getAbsolutePath();
    }
}
