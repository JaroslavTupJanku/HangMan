package cz.jarda.hangman;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowScoreActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        TextView labelScores = findViewById(R.id.labelScores);
        SharedPreferences preferences = getSharedPreferences("hangman_results", MODE_PRIVATE);
        labelScores.setText(preferences.getString("scores", ""));
    }

    public void clearData(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage("Do you really want to clear all score?");

        builder.setPositiveButton("Yes", (dialog, id) -> ClearAll());
        builder.setNegativeButton("No", (dialog, id) -> dialog.cancel());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void ClearAll()
    {
        SharedPreferences preferences = getSharedPreferences("hangman_results", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("scores", "");
        editor.apply();
        finish();
    }

    public void closeScoreActivity(View view)
    {
        finish();
    }
}