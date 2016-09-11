package com.nnd.bolon.dynedassesment.function.about;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.nnd.bolon.dynedassesment.R;
import com.nnd.bolon.dynedassesment.R2;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity {
    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.profileImage)
    ImageView profileImage;
    @BindViews({R2.id.containerName, R2.id.containerPreferences})
    List<CardView> listContainer;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ButterKnife.bind(this);

        setToolbar();
        setViewAnimation();
    }


    private void setViewAnimation() {
        Animation profileAnimation = AnimationUtils.loadAnimation(this, R.anim.profile_image_animation);
        profileImage.setAnimation(profileAnimation);

        Animation containerAnimation;

        for (int i = 0; i < listContainer.size(); i++) {
            if (i % 2 == 0) {
                containerAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_text_right);
            } else {
                containerAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_text_left);
            }

            listContainer.get(i).setAnimation(containerAnimation);
        }
    }

    private void setToolbar() {
        toolbar.setTitle(R.string.about);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(view -> AboutActivity.this.finish());
    }

}
