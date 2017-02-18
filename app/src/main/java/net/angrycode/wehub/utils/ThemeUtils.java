package net.angrycode.wehub.utils;

import android.content.Context;
import android.content.SharedPreferences;

import net.angrycode.wehub.R;

/**
 * Created by lancelot on 2016/12/18.
 */

public class ThemeUtils {
    private static final String FILE_NAME = "theme";
    private static final String KEY = "key";

    private ThemeUtils() {

    }

    public static void putTheme(Context context, int theme) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(KEY, theme).apply();
    }

    public static int getTheme(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        int theme = sp.getInt(KEY, R.style.AppTheme_Green);
        return theme;
    }

}
