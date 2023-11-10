package cz.jarda.hangman;

import androidx.appcompat.app.ActionBar;
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

        ActionBar actionBar = getSupportActionBar();                        //V teto aktivite se skryje toolBar, pokud ho tema podporuje
        if (actionBar != null)
        {
            actionBar.hide();
        }

        ImageView iw = findViewById(R.id.imageView);
        Animation logo = AnimationUtils.loadAnimation(this, R.anim.welcome_sc);
        iw.startAnimation(logo);

        logo.setAnimationListener(new Animation.AnimationListener()                                   //Toto je dalsi moznost. Nemusis tvorit a uspavat Task
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                startActivity(new Intent(WelcomeActivity.this, MenuActivity.class));      //Start a new activity
                finish();
            }
        });
    }
}