package cz.jarda.hangman;

import androidx.appcompat.app.AppCompatActivity;
import cz.jarda.models.WordType;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.HashMap;
import java.util.Map;

public class SelectCategoryActivity extends AppCompatActivity
{
    private Map<Integer, WordType> idToWordType = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);

        idToWordType.put(R.id.rbVerb, WordType.verb);
        idToWordType.put(R.id.rbAdjective, WordType.adjective);
        idToWordType.put(R.id.rbAdverb, WordType.adverb);
    }

    public void startGame(View view)
    {
        RadioGroup rg = findViewById(R.id.rgCategory);
        RadioButton button = findViewById(rg.getCheckedRadioButtonId());
        WordType wt = getWordType(button);

        Intent intent = new Intent(this, MainGameActivity.class);
        intent.putExtra("WordType", wt);
        startActivity(intent);
    }

    private WordType getWordType(RadioButton button)
    {
        return idToWordType.getOrDefault(button.getId(), WordType.noun);
    }
}