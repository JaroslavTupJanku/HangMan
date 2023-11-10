package cz.jarda.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainGameActivity extends AppCompatActivity
{
    ImageView imageTitleAnimation;
    ImageView imgHangman;
    EditText etInputChar;
    TextView labelIncorrectChars;

    private int score = 10000;
    private int guessCount = 0;
    private int occurrenceCount = 0;
    private int multiplier = 0;

    int categoryId = 0;
    private String word = "";
    private String misguessedChars = "";
    private String guessedChars = "";

    TextView[] wordChars = new TextView[10];

    int[] allowedChars = new int[] {
            97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110,
            111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122,
            283, 353, 357, 269, 345, 382, 253, 225, 237, 233, 367, 250, 243};






    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
    }

    public void addChar(View view)
    {
    }
}