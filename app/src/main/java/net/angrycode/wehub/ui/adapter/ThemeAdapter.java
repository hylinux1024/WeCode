package net.angrycode.wehub.ui.adapter;

import android.view.View;
import android.widget.TextView;

import net.angrycode.wehub.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lancelot on 2016/12/18.
 */

public class ThemeAdapter extends BaseSimpleRecycleAdapter<ThemeAdapter.Theme> {
    int mCurrTheme;

    public ThemeAdapter() {
        List<Theme> list = new ArrayList<>();
        list.add(new Theme(R.style.AppTheme));
        list.add(new Theme(R.style.AppTheme_Green));
        list.add(new Theme(R.style.AppTheme_Red));
        list.add(new Theme(R.style.AppTheme_Purple));
        list.add(new Theme(R.style.AppTheme_Gray));
        setData(list);
    }

    @Override
    public int getItemLayout(int viewType) {
        return R.layout.item_theme;
    }

    public void setCurrTheme(int currTheme) {
        mCurrTheme = currTheme;
        notifyDataSetChanged();
    }

    @Override
    public void onRender(ViewHolder holder, int position) {
        Theme theme = getItem(position);
        holder.getView(R.id.iv_theme).setBackgroundResource(theme.color);
        holder.getView(R.id.iv_check).setVisibility(mCurrTheme == theme.getStyle() ? View.VISIBLE : View.INVISIBLE);
        TextView textView = holder.getView(R.id.tv_theme_name);
        textView.setText(theme.name);
    }


    public static class Theme {
        private int color;
        private int name;
        private int style;

        public Theme(int style) {
            this.style = style;
            switch (style) {
                case R.style.AppTheme:
                    color = R.color.primary;
                    name = R.string.theme_default;
                    break;
                case R.style.AppTheme_Gray:
                    color = R.color.primary_gray;
                    name = R.string.theme_gray;
                    break;
                case R.style.AppTheme_Green:
                    color = R.color.primary_green;
                    name = R.string.theme_green;
                    break;
                case R.style.AppTheme_Purple:
                    color = R.color.primary_purple;
                    name = R.string.theme_purple;
                    break;
                case R.style.AppTheme_Red:
                    color = R.color.primary_red;
                    name = R.string.theme_red;
                    break;
            }
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int getName() {
            return name;
        }

        public void setName(int name) {
            this.name = name;
        }

        public int getStyle() {
            return style;
        }

        public void setStyle(int style) {
            this.style = style;
        }
    }
}
