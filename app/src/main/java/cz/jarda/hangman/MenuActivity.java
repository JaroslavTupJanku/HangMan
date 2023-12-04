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
        Intent intent = new Intent(this, SelectCategoryActivity.class);
        startActivity(intent);
    }

    public void showScore(View v)
    {
        Intent intent = new Intent(this, ShowScoreActivity.class);
        startActivity(intent);
    }

    public void closeApp(View v)
    {
        finish();
    }

}