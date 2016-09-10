package com.nnd.bolon.dynedassesment.function.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nnd.bolon.dynedassesment.R;
import com.nnd.bolon.dynedassesment.R2;
import com.nnd.bolon.dynedassesment.dependency.App;
import com.nnd.bolon.dynedassesment.dependency.data.user.User;
import com.nnd.bolon.dynedassesment.dependency.network.NetworkInterface;
import com.nnd.bolon.dynedassesment.function.MainActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    @Inject
    NetworkInterface networkInterface;
    @Inject
    Realm realm;

    @BindView(R2.id.progress_me)
    ProgressBar progressBar;
    @BindView(R2.id.textAck)
    TextView textAck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        App.get(getApplicationContext()).getInjector().inject(this);
        ButterKnife.bind(this);

        fetchData();
    }

    private void fetchData() {
        Call<List<User>> getListUser = networkInterface.getUser2();
        getListUser.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                saveToDb(response.body());
                doTransition();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                updateAck(false);
            }
        });
    }

    private void doTransition() {
        updateAck(true);
        startActivity(MainActivity.createIntent(getBaseContext()));
        overridePendingTransition(R.anim.slide1, R.anim.slide2);
        this.finish();
    }

    private void saveToDb(List<User> listUsers) {
        realm.beginTransaction();
        realm.insertOrUpdate(listUsers);
        realm.commitTransaction();
    }

    private void updateAck(boolean success){
        progressBar.setVisibility(View.GONE);
        if(success){
            textAck.setText(R.string.ack_success);
        } else
            textAck.setText(R.string.ack_fail);

        textAck.setVisibility(View.VISIBLE);

    }
}
