package net.angrycode.wehub.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.angrycode.wehub.bean.Lang;
import net.angrycode.wehub.ui.fragment.TrendsFragment;

import java.util.List;


/**
 * Created by lancelot on 2016/11/15.
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {
    //    private String[] mTabTitles = new String[]{"All", "C", "C++", "HTML", "Java", "JavaScript", "Kotlin", "Python", "PHP", "Swift"};
    private List<Lang> mLangs;

    public MainPagerAdapter(FragmentManager fm, List<Lang> list) {
        super(fm);
        mLangs = list;
    }
    public void setLangs(List<Lang> langs){
        mLangs = langs;
        notifyDataSetChanged();
    }
    @Override
    public Fragment getItem(int position) {
        return TrendsFragment.newInstance(mLangs.get(position).getKey());
    }

    @Override
    public int getCount() {
        return mLangs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mLangs.get(position).getName();
    }
}
