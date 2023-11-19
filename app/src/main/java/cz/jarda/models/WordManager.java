package cz.jarda.models;

import androidx.lifecycle.LiveData;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import cz.jarda.services.WordProvider;

public class WordManager
{
    private final WordProvider provider = new WordProvider();
    private Set<Character> noDuplicateGuessedChars = new HashSet<>();

    private String searchWord = "";
    private String wrongEstimatedChars = "";
    private String guessedChars = "";

    int[] allowedChars = new int[] {
            97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110,
            111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122,
            283, 353, 357, 269, 345, 382, 253, 225, 237, 233, 367, 250, 243};

    public String getGuessedChars() { return guessedChars; }
    public Set<Character> getNoDuplicateGuessedChars() { return noDuplicateGuessedChars; }
    public String getWrongEstimatedChars() { return wrongEstimatedChars; }
    public String getSearchWord() { return searchWord;}


    public void addWrongEstimatedCharsChar(char letter)
    {
        wrongEstimatedChars += letter;
    }

    public void setSearchWord(String searchWord)
    {
        this.searchWord = searchWord;
        this.wrongEstimatedChars = "";
        this.guessedChars = "";

        noDuplicateGuessedChars = searchWord.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet());
    }

    public void addGuessedChar(char letter)
    {
        guessedChars += letter;
    }

    public LiveData<String> generateNewWord(WordType type )
    {
        return provider.getRandomWordAsync(type);
    }

    public boolean isGuessedCharAllowed(char letter)
    {
        return Arrays.stream(allowedChars).anyMatch(x -> x == letter);
    }
}
