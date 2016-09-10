package com.nnd.bolon.dynedassesment.function.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.nnd.bolon.dynedassesment.R;
import com.nnd.bolon.dynedassesment.R2;
import com.nnd.bolon.dynedassesment.dependency.App;
import com.nnd.bolon.dynedassesment.dependency.data.user.User;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class DetailUser extends AppCompatActivity {
    final static String USER_ID_KEY = "user_id";
    final static String NAME_KEY = "user_fullname";

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.username)
    TextView username;
    @BindView(R2.id.email)
    TextView email;
    @BindView(R2.id.address)
    TextView address;
    @BindView(R2.id.phone)
    TextView phone;
    @BindView(R2.id.website)
    TextView website;
    @BindView(R2.id.company)
    TextView company;
    @BindView(R2.id.geolocation)
    TextView geolocation;
    @Inject
    Realm realm;

    private User user;

    public static Intent createIntent(Context context, String userId, String userName) {
        Intent intent = new Intent(context, DetailUser.class);
        intent.putExtra(USER_ID_KEY, userId);
        intent.putExtra(NAME_KEY, userName);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        App.get(getApplicationContext()).getInjector().inject(this);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        setToolbar(intent.getStringExtra(NAME_KEY));

        user = realm.where(User.class).equalTo("id", intent.getStringExtra(USER_ID_KEY)).findFirst();
        updateView();
    }

    private void updateView() {
        username.setText(user.getUsername());
        email.setText(user.getEmail());
        address.setText(user.getAddress().getCity() + " (" + user.getAddress().getZipcode() + ")");
        phone.setText(user.getPhone());
        website.setText(user.getWebsite());
        company.setText(user.getCompany().getName());
        geolocation.setText(user.getAddress().getGeo().getLat() + ", " + user.getAddress().getGeo().getLng());
    }

    private void setToolbar(String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(view -> DetailUser.this.finish());
    }
}
