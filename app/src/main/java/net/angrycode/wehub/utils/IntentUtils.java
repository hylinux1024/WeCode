package net.angrycode.wehub.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by lancelot on 2016/12/10.
 */

public class IntentUtils {
    public static void launch(Context context, Class clazz) {
        Intent intent = new Intent(context, clazz);
        if (context instanceof Activity) {

        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }
}
