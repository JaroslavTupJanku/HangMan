package cz.jarda.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class WelcomeActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Skrýt ActionBar, pokud je dostupný
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().hide();
        }

        // Nastavit animaci pro ImageView
        ImageView iw = findViewById(R.id.imageView);
        Animation welcomeAnimation = AnimationUtils.loadAnimation(this, R.anim.welcome_sc);
        iw.startAnimation(welcomeAnimation);

        // Listener pro animaci, přechází na MenuActivity po dokončení
        welcomeAnimation.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationEnd(Animation animation)
            {
                //Start a new activity
                startActivity(new Intent(WelcomeActivity.this, MenuActivity.class));
                finish();
            }

            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }
}