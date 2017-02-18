package net.angrycode.wehub.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by lancelot on 2016/11/14.
 */

public class SnackbarUtils {
    private SnackbarUtils() {

    }

    public static final void showSnack(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                .show();
    }
}
