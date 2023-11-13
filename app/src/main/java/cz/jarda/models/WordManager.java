package cz.jarda.models;

import androidx.lifecycle.LiveData;
import cz.jarda.services.WordProvider;

public class WordManager
{
    private WordProvider provider = new WordProvider();
    private String searchWord = "";
    private String misguessedChars = "";
    private String guessedChars = "";

    private int score = 10000;
    private int multiplier = 0;
    private int guessCount = 0;
    private int occurrenceCount = 0;

    int[] allowedChars = new int[] {
            97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110,
            111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122,
            283, 353, 357, 269, 345, 382, 253, 225, 237, 233, 367, 250, 243};

    public LiveData<String> generateNewWord(WordType type )
    {
        return provider.getRandomWordAsync(type);
    }
}
