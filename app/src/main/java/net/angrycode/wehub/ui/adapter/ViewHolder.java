package net.angrycode.wehub.ui.adapter;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 *
 * Created by lancelot on 2016/12/2.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    View itemView;
    SparseArray<View> views = new SparseArray<>();
    public ViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(@IdRes int resId) {

        View v = views.get(resId);
        if (null == v && itemView!=null) {
            v = itemView.findViewById(resId);
            views.put(resId, v);
        }
        return (T) v;
    }
}
