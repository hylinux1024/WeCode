package net.angrycode.wehub.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import net.angrycode.wehub.BuildConfig;
import net.angrycode.wehub.R;
import net.angrycode.wehub.bean.AppUpdate;
import net.angrycode.wehub.bean.Langs;
import net.angrycode.wehub.event.LangEditedEvent;
import net.angrycode.wehub.event.ThemeChangeEvent;
import net.angrycode.wehub.mvp.presenter.MainPresenter;
import net.angrycode.wehub.mvp.view.MainView;
import net.angrycode.wehub.ui.adapter.MainPagerAdapter;
import net.angrycode.wehub.utils.AppUtils;
import net.angrycode.wehub.utils.IntentUtils;
import net.angrycode.wehub.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, MainView {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    TextView mVersionTv;

    ActionBarDrawerToggle mToggle;
    MainPagerAdapter mAdapter;

    MainPresenter mPresenter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        initView();

        mPresenter = new MainPresenter(this);
        mPresenter.getLangs();

        mPresenter.checkAppUpdate();

    }

    private void initView() {
        //drawer
        mToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        mNavView.setNavigationItemSelectedListener(this);

        mVersionTv = (TextView) mNavView.getHeaderView(0).findViewById(R.id.tv_version);
//        mVersionTv.setText(getString(R.string.version_info, BuildConfig.VERSION_NAME));
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_language:
                IntentUtils.launch(this, LanguagesActivity.class);
                break;
            case R.id.nav_favorite:
                IntentUtils.launch(this, FavoritesActivity.class);
                break;
            case R.id.nav_theme:
                IntentUtils.launch(this, ThemeActivity.class);
                break;
            case R.id.nav_about:
                IntentUtils.launch(this, AboutActivity.class);
                break;
        }
        mDrawerLayout.postDelayed(() -> {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }, 300);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                SearchActivity.launch(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected boolean isShowHomeAsUpIndicator() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateLangsEvent(LangEditedEvent event) {
        if (event.getLangs() == null || event.getLangs().getSelList() == null) {
            return;
        }
        onGetLangsFinished(event.getLangs());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onThemeChangedEvent(ThemeChangeEvent event) {
        recreate();
    }

    @Override
    public void onGetLangsFinished(Langs langs) {
        //viewpager

        mAdapter = new MainPagerAdapter(getSupportFragmentManager(), langs.getSelList());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
//        int tabWidth = mTabLayout.getMeasuredWidth();
//        int screenWidth = AppUtils.getScreenWidth(this);
//        View tabView = mTabLayout.getChildAt(mTabLayout.getTabCount()-1);
//        if (tabWidth < screenWidth) {
//
//            mTabLayout.setTabMode(TabLayout.MODE_FIXED);
//        } else {
//            mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        }
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onRequestLoading() {
        showLoading();
    }

    @Override
    public void onRequestFinished() {
        dismissLoading();
    }

    @Override
    public void onCheckAppUpdateFinish(AppUpdate appUpdate) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String version = appUpdate.getData().getVersion();
        builder.setTitle(R.string.update);
        builder.setMessage(R.string.app_has_new_version);
        builder.setPositiveButton(R.string.btn_update, (dialog, which) -> {
            AppUtils.downLoad(this, appUpdate.getData().getUrl());
        });
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void onRequestError(int code, String message) {
        ToastUtils.show(this, message);
        dismissLoading();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mDrawerLayout.removeDrawerListener(mToggle);
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.destroy();
        super.onDestroy();
    }
}
