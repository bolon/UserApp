package com.nnd.bolon.dynedassesment.function.about;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nnd.bolon.dynedassesment.R;
import com.nnd.bolon.dynedassesment.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class AboutActivity extends AppCompatActivity {
    @BindView(R2.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ButterKnife.bind(this);

        setToolbar();
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);

        toolbar.setTitle(R.string.about);
        toolbar.setNavigationOnClickListener(view -> {
            AboutActivity.this.finish();
        });
    }

    public static Intent createIntent(Context context){
        Intent intent = new Intent(context, AboutActivity.class);
        return intent;
    }

}
