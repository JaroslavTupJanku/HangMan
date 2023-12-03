package cz.jarda.models;
import android.content.Context;
import android.media.MediaPlayer;

import cz.jarda.hangman.R;

public class ScoreCounter
{
    private int score = 10000;
    private int multiplier = 1;
    private MediaPlayer mp;
    private final Context context;

    public ScoreCounter(Context context)
    {
        this.context = context;
    }

    public int getScore()
    {
        return score;
    }

    public void scorePlus()
    {
        if (mp != null) {
            mp.release();
        }

        mp = MediaPlayer.create(context, R.raw.success);
        mp.start();
        multiplier++;
        score += (500 * multiplier);
    }

    public void scoreMinus() {

        if (mp != null) {
            mp.release();
        }

        mp = MediaPlayer.create(context, R.raw.failure);
        mp.start();
        score -= 1000;
        multiplier = 0;
    }
}
