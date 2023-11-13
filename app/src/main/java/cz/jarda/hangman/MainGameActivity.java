package cz.jarda.hangman;
import cz.jarda.models.WordManager;
import cz.jarda.models.WordType;
import cz.jarda.services.TextViewFactory;

import androidx.appcompat.app.AppCompatActivity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainGameActivity extends AppCompatActivity
{
    ImageView imageTitleAnimation;
    TableRow myTableRow;
    ImageView imgHangman;
    EditText etInputChar;
    TextView labelIncorrectChars;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        WordType wordType = (WordType)getIntent().getSerializableExtra("WordType");
        fillTableRow(new WordManager(), wordType);
    }

    private void fillTableRow(WordManager manager, WordType type)
    {
        TextViewFactory factory = new TextViewFactory(this);
        myTableRow = findViewById(R.id.myTableRow);

        //Lambda expression word is a callback that is executed when the word is retrieved asynchronously by the generateNewWord method.
        //When a word is available, this lambda expression receives that word as an argument (word) and executes the code inside;
        manager.generateNewWord(type).observe(this, word ->
        {
            if (word != null)
            {
                for (int i = 0; i < word.length(); i++)
                {
                    myTableRow.addView(factory.createTextView(i));
                }
            }
            else
            {
                Toast.makeText(this, "Error fetching word", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addChar (View view)
    {

    }
}