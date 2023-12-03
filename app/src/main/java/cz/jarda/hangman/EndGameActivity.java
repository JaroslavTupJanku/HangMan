package cz.jarda.hangman;

import static java.lang.String.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EndGameActivity extends AppCompatActivity
{
    TextView labelWord;
    TextView labelScore;
    EditText etPlayerName;

    int lastScore;
    boolean win;
    String playerName = "";
    String word = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR); // Disable screen rotation
        setContentView(R.layout.activity_end_game);

        labelWord = findViewById(R.id.labelWord);
        labelScore = findViewById(R.id.labelScore);
        etPlayerName = findViewById(R.id.etPlayerName);

        Intent intent = getIntent();
        lastScore = intent.getIntExtra("score", 0);
        labelScore.setText(valueOf(lastScore));
        win = intent.getBooleanExtra("isWin", false);
        word = intent.getStringExtra("word");

        if (word != null) {
            labelWord.setText(word.toUpperCase());
        }
    }

    public void saveScore(View v)
    {
        playerName = etPlayerName.getText().toString().trim();
        if (playerName.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Player name not entered...", Toast.LENGTH_LONG).show();
            return;
        }

        if (playerName.length() < 3) {
            Toast.makeText(getApplicationContext(), "The name must contain at least 3 characters!", Toast.LENGTH_LONG).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);                 //Dialog
        builder.setCancelable(true);
        builder.setMessage("Do you want to save result of player: " + playerName + " ?");
        builder.setPositiveButton("Ano", (dialog, id) -> saveScoreToMemory());
        builder.setNegativeButton("Ne", (dialog, id) -> dialog.cancel());

        AlertDialog dialog = builder.create();
        dialog.show();
    }




    private void saveScoreToMemory()
    {
        SharedPreferences preferences = getSharedPreferences("hangman_results", MODE_PRIVATE); //Private file for application
        String savedScores = preferences.getString("scores", ""); //if scores is not created yet, return default.

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("scores", savedScores + playerName + ": " + lastScore + "\n");
        editor.apply(); //Apply changes - async
        finish();
    }

    public void closeActivityEndGame(View view)
    {
        finish();
    }
}