package net.angrycode.wehub.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import net.angrycode.wehub.R;
import net.angrycode.wehub.event.ThemeChangeEvent;
import net.angrycode.wehub.ui.adapter.ThemeAdapter;
import net.angrycode.wehub.ui.adapter.TrendsAdapter;
import net.angrycode.wehub.ui.component.DividerItemDecoration;
import net.angrycode.wehub.utils.ThemeUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * Created by lancelot on 2016/12/9.
 */

public class ThemeActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    ThemeAdapter mAdapter;
    int mCurrTheme;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_theme;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

    }

    private void init() {
        mCurrTheme = getCurrTheme();

        mAdapter = new ThemeAdapter();
        mAdapter.setCurrTheme(mCurrTheme);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this::onItemClick);
    }

    private void onItemClick(View itemView, int position) {
        ThemeAdapter.Theme theme = mAdapter.getItem(position);
        mToolbar.setBackgroundResource(theme.getColor());
        mCurrTheme = theme.getStyle();
        mAdapter.setCurrTheme(mCurrTheme);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_theme_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_finish:
                if (mCurrTheme != getCurrTheme()) {
                    ThemeUtils.putTheme(this, mCurrTheme);
                    EventBus.getDefault().post(new ThemeChangeEvent());
                }
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
