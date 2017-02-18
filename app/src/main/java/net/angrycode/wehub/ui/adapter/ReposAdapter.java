package net.angrycode.wehub.ui.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import net.angrycode.wehub.R;
import net.angrycode.wehub.bean.Repository;
import net.angrycode.wehub.utils.GlideUtils;

/**
 * Created by lancelot on 2016/11/13.
 */

public class ReposAdapter extends BaseSimpleRecycleAdapter<Repository> {

    public ReposAdapter() {

    }

    @Override
    public int getItemLayout(int viewType) {
        return R.layout.item_repo;
    }

    @Override
    public void onRender(ViewHolder holder, int position) {
        Repository repository = getItem(position);

        ImageView imageView = holder.getView(R.id.iv_user_face);
        TextView titleTv = holder.getView(R.id.tv_repo_name);
        TextView descTv = holder.getView(R.id.tv_repo_desc);
//        TextView watcherCountTv = holder.getView(R.id.tv_watcher_count);
        TextView starCountTv = holder.getView(R.id.tv_star_count);
        TextView forkCountTv = holder.getView(R.id.tv_fork_count);

        titleTv.setText(repository.getFull_name());
        descTv.setText(repository.getDescription());
//        watcherCountTv.setText(String.valueOf(repository.getWatchers()));
        starCountTv.setText(String.valueOf(repository.getWatchers_count()));
        forkCountTv.setText(String.valueOf(repository.getForks()));
        GlideUtils.loadImage(imageView.getContext(), repository.getOwner().getAvatar_url(), imageView);
    }


}
