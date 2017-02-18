package net.angrycode.wehub.utils;

import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

import static android.content.Context.INPUT_METHOD_SERVICE;


/**
 * Keyboard utilities
 */
public class Keyboard {

    public static void show(final View view, long delay) {
        if (view == null) {
            return;
        }
        InputMethodManager manager = (InputMethodManager) view.getContext().getSystemService(INPUT_METHOD_SERVICE);
        if (view.getWidth() == 0 && view.getHeight() == 0) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    removeViewTreeObserver(view, this);
                    if (delay <= 0) {
                        manager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
                    } else {
                        view.postDelayed(() -> manager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT), delay);
                    }
                }
            });
        } else {
            if (delay <= 0) {
                manager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
            } else {
                view.postDelayed(() -> manager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT), delay);
            }
        }

    }

    public static void hide(final View view) {
        if (view == null) {
            return;
        }
        InputMethodManager manager = (InputMethodManager) view.getContext().getSystemService(INPUT_METHOD_SERVICE);
        if (manager != null) {
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 移除View上的ViewTreeObserver，兼容方法
     */
    public static void removeViewTreeObserver(View view, ViewTreeObserver.OnGlobalLayoutListener l) {
        if (view != null && view.getViewTreeObserver() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(l);
            } else {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(l);
            }
        }
    }
} 