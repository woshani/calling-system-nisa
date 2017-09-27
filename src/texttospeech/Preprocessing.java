/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texttospeech;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Kadek
 * This is a class for process every token in the text.
 * This is the main class.
 */
public class Preprocessing {
    private String originalText, normalizedText, outputName;
    private int order = 0;
    
    private ArrayList<String> words;
    
    public Preprocessing(String originalText) {
        this.originalText = originalText;
        // Remove every punctuation.
        this.normalizedText = originalText.replaceAll("(\\r|\\n|\\n\\r|\\r\\n)", " ").trim().replaceAll("\\s+", " ").replaceAll("\\p{P}", "").replace("\'", "").replace(".", " ").replace("-", " ").toLowerCase();
        //System.out.println(normalizedText);
        // Split text based on space.
        words = new ArrayList<String>(Arrays.asList(normalizedText.split(" ")));
        //System.out.println(words);
        iterator();
    }
    
    private void iterator() {
        // Iterate every token.
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            
            // If token is acronym, then replace it to the alias.
            int isAcronym = ConstantObjects.searchAcronyms(ConstantObjects.ACRONYMS, word);
            if (isAcronym > -1) {
                word = ConstantObjects.ALIAS.get(isAcronym);
            }
            //System.out.println(word);
            
            // If token already available in the WAV list.
            if (ConstantObjects.WAVLIST.contains(word)) {
                outputName = new AvailableWordProcessor(outputName, word, order).getOutputName();
            } else if (word.matches("\\d+")) { // If token is number-only.
                outputName = new NumberProcessor(outputName, word, order).getOutputName();
            } else if (word.matches(".*\\d+.*")) { // If token contains both letter and number.
                outputName = new AlphanumericProcessor(outputName, word, order).getOutputName();
            } else { // If token is letter-only.
                outputName = new WordOnlyProcessor(outputName, word, order).getOutputName();
            }
            
            //System.out.println(outputName);
            order++;
        }
    }
    
    public String getOutputName() {
        return outputName;
    }
}
