package cz.jarda.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class MenuActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
    }

    public void newGame(View v)
    {
        Intent i = new Intent(this, SelectCategoryActivity.class);
        startActivity(i);
    }

    public void showScore(View v)
    {

    }

    public void closeApp(View v)
    {
        finish();
    }

}