package net.angrycode.wehub.ui.component;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import net.angrycode.wehub.ui.adapter.LangAdapter;

/**
 * Created by lancelot on 2016/12/17.
 */

public class LangGridLayoutManager extends GridLayoutManager {

    public LangGridLayoutManager(Context context, final LangAdapter adapter, int spanCount) {
        super(context, spanCount);
        setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = adapter.getItemViewType(position);
                return viewType == LangAdapter.VIEW_TYPE_LANG_SEL_ITEM || viewType == LangAdapter.VIEW_TYPE_LANG_UNSEL_ITEM ? 1 : 4;
            }
        });
    }


}

