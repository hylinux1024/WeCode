package net.angrycode.wehub.ui.adapter;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.angrycode.wehub.R;
import net.angrycode.wehub.bean.Lang;
import net.angrycode.wehub.bean.Langs;
import net.angrycode.wehub.ui.component.drag.OnItemMoveListener;
import net.angrycode.wehub.utils.LogUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LangAdapter extends BaseSimpleRecycleAdapter<Lang> implements OnItemMoveListener {
    public static final int VIEW_TYPE_SELECTED_HEADER = 0;//已选头部
    public static final int VIEW_TYPE_LANG_SEL_ITEM = 1;//已选item
    public static final int VIEW_TYPE_UNSELECTED_HEADER = 2;//未选头部
    public static final int VIEW_TYPE_LANG_UNSEL_ITEM = 3;//未选item
    private ItemTouchHelper mItemTouchHelper;
    private int mSelCount = 0;
    private int mUnSelCount = 0;
    private boolean mIsEdited;

    public LangAdapter(ItemTouchHelper helper) {
        mItemTouchHelper = helper;
    }


    public void setLangs(Langs langs) {
        List<Lang> selLangList = langs.getSelList();
        List<Lang> unSelLangList = langs.getUnselList();
        mSelCount = selLangList.size();
        mUnSelCount = unSelLangList.size();
        List<Lang> data = new ArrayList<>();
        data.add(new Lang("header1", "header1"));
        data.addAll(selLangList);
        data.add(new Lang("header2", "header2"));
        data.addAll(unSelLangList);

        setData(data);
    }

    public Langs getLangs() {
        List<Lang> all = getData();
        Langs langs = new Langs();
        List<Lang> selLangList = new ArrayList<>();
        List<Lang> unSelLangList = new ArrayList<>();
        for (int i = 1; i < mSelCount + 1; ++i) {
            selLangList.add(all.get(i));
        }
        for (int i = mSelCount + 2; i < getItemCount(); ++i) {
            unSelLangList.add(all.get(i));
        }
        langs.setSelList(selLangList);
        langs.setUnselList(unSelLangList);
        return langs;
    }

    public boolean isEdited() {
        return mIsEdited;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_SELECTED_HEADER;
        } else if (position == mSelCount + 1) {
            return VIEW_TYPE_UNSELECTED_HEADER;
        } else if (position > 0 && position < mSelCount + 1) {
            return VIEW_TYPE_LANG_SEL_ITEM;
        }
        return VIEW_TYPE_LANG_UNSEL_ITEM;
    }

    @Override
    public int getItemLayout(int viewType) {
        switch (viewType) {
            case VIEW_TYPE_SELECTED_HEADER://头部
                return R.layout.item_selected_langs_header;
            case VIEW_TYPE_LANG_SEL_ITEM://数据
            case VIEW_TYPE_LANG_UNSEL_ITEM:
                return R.layout.item_lang;
            case VIEW_TYPE_UNSELECTED_HEADER://未选头部
                return R.layout.item_other_langs_header;

        }
        return 0;
    }

    @Override
    public void onRender(ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_SELECTED_HEADER:

                break;
            case VIEW_TYPE_LANG_SEL_ITEM://已选
                TextView tvLang = holder.getView(R.id.tv_lang);
                ImageView imgEdit = holder.getView(R.id.iv_edit);
                tvLang.setText(getItem(position).getName());
                imgEdit.setVisibility(position == 1 ? View.GONE : View.VISIBLE);
                tvLang.setOnTouchListener((view, motionEvent) -> {
                    if (position == 1) {
                        return false;
                    }
                    if (MotionEventCompat.getActionMasked(motionEvent) == MotionEvent.ACTION_DOWN) {
                        mItemTouchHelper.startDrag(holder);
                    }
                    return false;
                });
                imgEdit.setOnClickListener(view -> {
                    LogUtils.log("position:" + position + ",adapter:" + holder.getAdapterPosition() + ",layout:" + holder.getLayoutPosition());
                    Lang lang = getData().remove(holder.getAdapterPosition());
                    getData().add(lang);
                    mSelCount--;
                    mUnSelCount++;

                    notifyDataSetChanged();
                    mIsEdited = true;

                });
                break;
            case VIEW_TYPE_UNSELECTED_HEADER:

                break;
            case VIEW_TYPE_LANG_UNSEL_ITEM://未选数据
                TextView tvLang2 = holder.getView(R.id.tv_lang);
                ImageView imgEdit2 = holder.getView(R.id.iv_edit);
                tvLang2.setText(getItem(position).getName());
                imgEdit2.setVisibility(View.INVISIBLE);
                tvLang2.setOnClickListener(view -> {
                    mSelCount++;
                    mUnSelCount--;
                    Lang lang = getData().remove(position);
                    getData().add(mSelCount, lang);

                    notifyDataSetChanged();
                    mIsEdited = true;

                });
                break;
        }
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition == 1 || toPosition == 1) {
            return false;
        }
        if (fromPosition - 1 >= mSelCount || toPosition - 1 >= mSelCount) {
            return false;
        }
//        Collections.swap(mList, fromPosition, toPosition);
        Lang from = mList.remove(fromPosition);
        mList.add(toPosition,from);
//        Lang to = mList.get(toPosition);

        notifyItemMoved(fromPosition, toPosition);
        mIsEdited = true;
        return true;

    }

}
