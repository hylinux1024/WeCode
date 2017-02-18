package net.angrycode.wehub.utils;

import android.content.Context;
import android.text.TextUtils;

import net.angrycode.wehub.bean.GsonBuilder;
import net.angrycode.wehub.bean.Langs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lancelot on 2016/12/17.
 */

public class LangsUtils {
    private static final String FILE_NAME = "langs";

    public static boolean write2sdcard(Context context, String langsJson) {
        File file = new File(context.getExternalCacheDir(), FILE_NAME);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            out.write(langsJson.getBytes());
            out.flush();
        } catch (IOException e) {
            LogUtils.e(e);
            return false;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                LogUtils.e(e);
            }
        }
        return true;
    }

    public static boolean write2sdcard(Context context, Langs langs) {
        String json = GsonBuilder.toJson(langs);
        return write2sdcard(context, json);
    }

    public static String readFromAssets(Context context) {
        String retVal = null;
        try {
            InputStream in = context.getAssets().open(FILE_NAME);
            retVal = FileUtils.read(in);
        } catch (IOException e) {
            LogUtils.e(e);
        }
        return retVal;
    }

    public static String readLangJson(Context context) {
        File file = new File(context.getExternalCacheDir(), FILE_NAME);
        String json = FileUtils.readFile(file.getAbsolutePath());
        if (TextUtils.isEmpty(json)) {
            json = readFromAssets(context);
            write2sdcard(context, json);
        }
        return json;
    }


}
