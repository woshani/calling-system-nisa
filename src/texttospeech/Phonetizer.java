/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texttospeech;

import java.util.ArrayList;

/**
 *
 * @author Kadek
 * This is a class for get the phonemes of a word.
 */
public class Phonetizer {
    private String word;
    private ArrayList<String> phonemes = new ArrayList<String>();
    
    public Phonetizer(String word) {
        this.word = word;
        phonetizer();
    }
    
    private void phonetizer() {
        int length = word.length();
        // If length > 2, then get 2 char substring.
        if (length > 2) {
            for (int i = 0; i < word.length() - 1; i++) {
                phonemes.add(word.substring(i, i + 2));
            }
        } else if (length == 2) {// If length = 2, then split the word into two characters.
            phonemes.add(String.valueOf(word.charAt(0)));
            phonemes.add(String.valueOf(word.charAt(1)));
        } else // Word contains 1 character only.
            phonemes.add(word);
    }
    
    public ArrayList<String> getPhonemes() {
        return phonemes;
    }
}
