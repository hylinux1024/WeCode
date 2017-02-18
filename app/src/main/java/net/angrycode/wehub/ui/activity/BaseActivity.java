package net.angrycode.wehub.ui.activity;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import net.angrycode.wehub.R;
import net.angrycode.wehub.utils.ThemeUtils;

import butterknife.ButterKnife;

/**
 * Created by lancelot on 2016/5/30.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getLayoutResource();

    protected Toolbar mToolbar;
    protected TextView mToolbarTitle;

    protected ProgressDialog mLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(getCurrTheme());
        super.onCreate(savedInstanceState);
        int layoutResId = getLayoutResource();
        try {
            if (layoutResId != 0) {
                setContentView(layoutResId);
            }
        } catch (Exception e) {
            Log.d("base activity", e.getMessage());
        }
//        AppManager.getAppManager().addActivity(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        View v = findViewById(R.id.toolbar);
        if (v != null) {
            mToolbar = (Toolbar) v;
            setSupportActionBar(mToolbar);
            mToolbarTitle = (TextView) v.findViewById(R.id.toolbar_title);
            if (mToolbarTitle != null && getSupportActionBar() != null) {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
            if (getSupportActionBar() != null && isShowHomeAsUpIndicator()) {
//                getSupportActionBar().setIcon(R.drawable.app_divider_drawable);
//                getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

                getSupportActionBar().setHomeButtonEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            }

            mToolbar.setNavigationOnClickListener(view -> onBackPressed());

        }
    }

    public void showLoading() {
        if (isFinishing()) {
            return;
        }
        if (mLoading == null) {
            mLoading = new ProgressDialog(this);
        }
        mLoading.setCancelable(false);
        mLoading.setCanceledOnTouchOutside(false);
        mLoading.setMessage(getResources().getString(R.string.loading));
        mLoading.show();
    }

    public void showLoading(int msg) {

        if (mLoading == null) {
            mLoading = new ProgressDialog(this);
        }
        mLoading.setMessage(getResources().getString(msg));
        mLoading.show();
    }

    public void dismissLoading() {
        if (mLoading != null) {
            mLoading.dismiss();
        }
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == android.R.id.home) {
//            onBackPressed();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        int size = menu.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = menu.getItem(i);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    protected boolean isShowHomeAsUpIndicator() {
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if (mLoading != null && mLoading.isShowing()) {
            mLoading.dismiss();
        }
//        AppManager.getAppManager().removeActivity(this);
        super.onDestroy();
    }

    protected int getCurrTheme() {
        return ThemeUtils.getTheme(this);
    }
}
