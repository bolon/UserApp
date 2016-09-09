package com.nnd.bolon.dynedassesment.main;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nnd.bolon.dynedassesment.R;
import com.nnd.bolon.dynedassesment.R2;
import com.nnd.bolon.dynedassesment.dependency.App;
import com.nnd.bolon.dynedassesment.dependency.data.user.User;
import com.nnd.bolon.dynedassesment.main.fragment.ListUsersFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements ListUsersFragment.OnListFragmentInteractionListener {
    @Inject
    Realm realm;

    @BindView(R2.id.viewPagerMain)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.get(getApplicationContext()).getInjector().inject(this);
        ButterKnife.bind(this);

        setView();
    }

    private void setView() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(setListUserFragment(), "List User");
        viewPager.setAdapter(adapter);
        //viewPager.setOffscreenPageLimit(1); //not needed currently
    }

    private ListUsersFragment setListUserFragment(){
        //TODO : set value from here not from fragment
        /*
        RealmResults<User> realmResults = realm.where(User.class).findAll();

        List<User> result = realmResults;
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("KEY", (ArrayList<? extends Parcelable>) result);
        */
        ListUsersFragment listUsersFragment = new ListUsersFragment();
        //listUsersFragment.setArguments(bundle);

        return listUsersFragment;
    }


    public static Intent createIntent(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    public void onListFragmentInteraction(List<User> listUser) {

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }
}
