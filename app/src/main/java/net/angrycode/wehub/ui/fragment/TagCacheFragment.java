package net.angrycode.wehub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import net.angrycode.wehub.R;
import net.angrycode.wehub.utils.TagPrefUtils;

import butterknife.BindView;
import me.gujun.android.taggroup.TagGroup;

/**
 * 搜索页面的历史记录
 * Created by lancelot on 2016/12/4.
 */

public class TagCacheFragment extends BaseFragment {
    @BindView(R.id.tag_group)
    TagGroup mTagGroup;
    OnTagClickListener mOnTagClickListener;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_tag_cache;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] tags = TagPrefUtils.getTags(getActivity());
        if (tags.length > 0) {
            mTagGroup.setTags(tags);
        }
        mTagGroup.setOnTagClickListener(tag -> {
            if (mOnTagClickListener != null) {
                mOnTagClickListener.onTagClick(tag);
            }
        });
    }

    public void setOnTagClickListener(OnTagClickListener listener) {
        mOnTagClickListener = listener;
    }

    public interface OnTagClickListener {
        void onTagClick(String tag);
    }
}
