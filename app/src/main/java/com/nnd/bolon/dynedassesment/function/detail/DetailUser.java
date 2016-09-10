package com.nnd.bolon.dynedassesment.function.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.nnd.bolon.dynedassesment.R;
import com.nnd.bolon.dynedassesment.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailUser extends AppCompatActivity {
    final static String USER_KEY = "user_id";
    @BindView(R2.id.toolbar)
    Toolbar toolbar;

    public static Intent createIntent(Context context, String userId) {
        Intent intent = new Intent(context, DetailUser.class);
        intent.putExtra(USER_KEY, userId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        ButterKnife.bind(this);

        setToolbar();
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);

        toolbar.setTitle(R.string.about);
        toolbar.setNavigationOnClickListener(view -> DetailUser.this.finish());
    }
}
