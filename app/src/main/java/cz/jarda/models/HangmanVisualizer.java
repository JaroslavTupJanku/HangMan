package cz.jarda.models;

import android.widget.ImageView;
import java.util.HashMap;
import java.util.Map;
import cz.jarda.hangman.R;

public class HangmanVisualizer
{
    private final ImageView hangmanView;
    private Map<Integer, Integer> imageResourceMap;

    public HangmanVisualizer(ImageView hangmanView)
    {
        this.hangmanView = hangmanView;
        initializeImageMap();
    }

    private void initializeImageMap()
    {
        imageResourceMap = new HashMap<>();
        imageResourceMap.put(0, R.drawable.hangman1);
        imageResourceMap.put(1, R.drawable.hangman2);
        imageResourceMap.put(2, R.drawable.hangman3);
        imageResourceMap.put(3, R.drawable.hangman4);
        imageResourceMap.put(4, R.drawable.hangman5);
        imageResourceMap.put(5, R.drawable.hangman6);
        imageResourceMap.put(6, R.drawable.hangman7);
        imageResourceMap.put(7, R.drawable.hangman8);
        imageResourceMap.put(8, R.drawable.hangman9);
    }

    public void updateHangmanImage(int wrongGuessCount)
    {
        Integer imageResId = imageResourceMap.get(wrongGuessCount);
        if (imageResId != null)
        {
            hangmanView.setImageResource(imageResId);
        }
    }
}
