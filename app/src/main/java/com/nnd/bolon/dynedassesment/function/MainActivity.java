package com.nnd.bolon.dynedassesment.function;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.nnd.bolon.dynedassesment.R;
import com.nnd.bolon.dynedassesment.R2;
import com.nnd.bolon.dynedassesment.dependency.App;
import com.nnd.bolon.dynedassesment.dependency.data.user.User;
import com.nnd.bolon.dynedassesment.function.about.AboutActivity;
import com.nnd.bolon.dynedassesment.function.detail.DetailUser;
import com.nnd.bolon.dynedassesment.function.showlistuser.ListUsersFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements ListUsersFragment.OnListFragmentInteractionListener {
    @Inject
    Realm realm;

    @BindView(R2.id.viewPagerMain)
    ViewPager viewPager;
    @BindView(R2.id.toolbar)
    Toolbar toolbar;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.get(getApplicationContext()).getInjector().inject(this);
        ButterKnife.bind(this);

        setView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        setIcon(menu.findItem(R.id.action_about), FontAwesomeIcons.fa_info_circle);
        setIcon(menu.findItem(R.id.action_logout), FontAwesomeIcons.fa_sign_out);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                startActivity(AboutActivity.createIntent(getApplicationContext()));
            case R.id.action_logout:
                Timber.i(item.getTitle().toString());
        }

        return true;
    }

    private void setView() {
        setViewPager();
        setToolbar();
    }

    private void setToolbar() {
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
    }

    private void setViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(setListUserFragment(), "List User");
        viewPager.setAdapter(adapter);
    }

    private ListUsersFragment setListUserFragment() {
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

    private void setIcon(MenuItem item, FontAwesomeIcons icon) {
        item.setIcon(new IconDrawable(this, icon)
                .color(Color.WHITE)
                .actionBarSize());
    }

    @Override
    public void onItemClicked(User user) {
        startActivity(DetailUser.createIntent(getApplicationContext(), user.getId()));
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
