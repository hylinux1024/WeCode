package net.angrycode.wehub.utils;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;

import net.angrycode.wehub.R;

/**
 * Created by lancelot on 2016/12/20.
 */

public class Utils {
    public static void copyText(Context context,String text) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        }
        ToastUtils.show(context, R.string.text_copied);
    }
}
