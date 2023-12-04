package cz.jarda.hangman;
import cz.jarda.services.HangmanVisualizer;
import cz.jarda.models.ScoreCounter;
import cz.jarda.models.WordManager;
import cz.jarda.models.WordType;
import cz.jarda.services.TextViewFactory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainGameActivity extends AppCompatActivity
{
    private ImageView imageTitleAnimation;
    private TableRow myTableRow;
    private EditText etInputChar;
    private TextView labelIncorrectChars;

    private HangmanVisualizer hangmanVisualizer;
    private final int MAX_ALLOWED_GUESSES = 9;
    private final WordManager manager = new WordManager();
    private final ScoreCounter score = new ScoreCounter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        etInputChar = findViewById(R.id.etInput);
        labelIncorrectChars = findViewById(R.id.labelIncorrectEntries);
        ImageView imgHangman = findViewById(R.id.myImageView);
        hangmanVisualizer = new HangmanVisualizer(imgHangman);

        WordType wordType = (WordType) getIntent().getSerializableExtra("WordType");
        fillTableRow(manager, wordType);
    }

    private void fillTableRow(WordManager manager, WordType type)
    {
        TextViewFactory factory = new TextViewFactory(this);
        myTableRow = findViewById(R.id.myTableRow);

        //Lambda expression word is a callback that is executed when the word is retrieved asynchronously by the generateNewWord method.
        //When a word is available, this lambda expression receives that word as an argument (word) and executes the code inside;
        //UI (Observer) is notified about change.
        manager.generateNewWord(type).observe(this, word ->
        {
            if (word != null) {
                manager.setSearchWord(word);
                for (int i = 0; i < word.length(); i++)
                {
                    myTableRow.addView(factory.createTextView(i));
                }
            }
            else {
                Toast.makeText(this, "Error fetching word", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addChar(View view)
    {
        String input = etInputChar.getText().toString().toLowerCase();
        etInputChar.setText("");

        if (TextUtils.isEmpty(input) || !manager.isGuessedCharAllowed(input.charAt(0))) {
            showToast(R.string.input_illegal_char);
            return;
        }

        char insertedChar = input.charAt(0);
        String insertedCharStr = String.valueOf(insertedChar);
        if (manager.getGuessedChars().contains(insertedCharStr)) {
            showToast(R.string.input_guessed_char);
            return;
        }

        if (manager.getWrongEstimatedChars().contains(insertedCharStr)) {
            showToast(R.string.input_unsuccessful_guessed);
            return;
        }

        boolean isSuccessfullyFound = false;
        for (int i = 0; i < manager.getSearchWord().length(); i++)
        {
            if (manager.getSearchWord().charAt(i) == insertedChar) {
                displayGuessedChar(insertedChar, i);
                isSuccessfullyFound = true;
                score.scorePlus();
            }
        }

        if (!isSuccessfullyFound) {
            manager.addWrongEstimatedCharsChar(insertedChar);
            score.scoreMinus();
            updateHangman();
            updateMisGuessedLabels();
        }
        else {
            manager.addGuessedChar(insertedChar);
        }

        if (manager.getNoDuplicateGuessedChars().toArray().length == manager.getGuessedChars().length()) {
            endGame(true);
        }
    }

    private void updateHangman()
    {
        int wrongGuesses = manager.getWrongEstimatedChars().length();
        hangmanVisualizer.updateHangmanImage(wrongGuesses);

        if (wrongGuesses >= MAX_ALLOWED_GUESSES)
        {
            endGame(false);
        }
    }

    private void showToast(int messageId)
    {
        Toast.makeText(getApplicationContext(), getResources().getString(messageId), Toast.LENGTH_LONG).show();
    }

    private void displayGuessedChar(char insertedChar, int index)
    {
        TextView textView = (TextView) myTableRow.getVirtualChildAt(index);
        textView.setText(String.valueOf(insertedChar));
    }

    private void updateMisGuessedLabels()
    {
        labelIncorrectChars.setText(manager.getWrongEstimatedChars());
    }

    private void endGame(boolean win)
    {
        Intent endGame = new Intent(this, EndGameActivity.class); //To pass variables to another activity
        endGame.putExtra("isWin", win);
        endGame.putExtra("score", score.getScore());
        endGame.putExtra("word", manager.getSearchWord());
        startActivity(endGame);
        finish();
    }
}