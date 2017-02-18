package net.angrycode.wehub.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by lancelot on 16/6/21.
 */
public class ToastUtils {
    /**
     * @param context
     * @param msg
     */
    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context, @StringRes int msgRes) {
        Toast.makeText(context, msgRes, Toast.LENGTH_SHORT).show();
    }

}
