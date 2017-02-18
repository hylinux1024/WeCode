package net.angrycode.wehub.ui.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import net.angrycode.wehub.R;
import net.angrycode.wehub.bean.TrendingRepo;
import net.angrycode.wehub.utils.GlideUtils;

/**
 * Created by lancelot on 2016/12/2.
 */

public class TrendsAdapter extends BaseSimpleRecycleAdapter<TrendingRepo> {
    public TrendsAdapter() {
    }

    @Override
    public int getItemLayout(int viewType) {
        return R.layout.item_trends;
    }

    @Override
    public void onRender(ViewHolder holder, int position) {
        TrendingRepo repo = getItem(position);

        ImageView imageView = holder.getView(R.id.iv_user_face);
        TextView titleTv = holder.getView(R.id.tv_repo_name);
        TextView descTv = holder.getView(R.id.tv_repo_desc);
        TextView starCountTv = holder.getView(R.id.tv_stars_today);

        titleTv.setText(repo.getOwner() + "/" + repo.getName());
        descTv.setText(repo.getDesc());
        starCountTv.setText(String.valueOf(repo.getStars_today()) + " stars today");
        GlideUtils.loadImage(imageView.getContext(), repo.getAvatar(), imageView);
    }

    public void remove(TrendingRepo repo) {
        int len = mList.size();
        for (int i = 0; i < len; ++i) {
            TrendingRepo trend = mList.get(i);
            if (trend.equals(repo)) {
                mList.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(0,mList.size());
                break;
            }
        }
    }
}
