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
 * @author Kadek & Anisa
 * This is a class for process every token in the text.
 * This is the main class.
 */
public class Preprocessing {
    private String originalText, normalizedText, outputName;
    private int order = 0;
    
    private ArrayList<String> words;
    
    public Preprocessing(String originalText) {
        String tempText;
        this.originalText = originalText;
        // Remove every punctuation.
        tempText = originalText.replace("\'", "").replace(".", " ").replace("-", " ");
        this.normalizedText = tempText.replaceAll("(\\r|\\n|\\n\\r|\\r\\n)", " ").trim().replaceAll("\\s+", " ").replaceAll("\\p{P}", "").replaceAll("ee", "i").replaceAll("eo", "u").replaceAll("oo", "u").toLowerCase();
        
        //System.out.println(normalizedText);
        // Split text based on space.
        words = new ArrayList<String>(Arrays.asList(normalizedText.split(" ")));
        //System.out.println(words);
        iterator();
    }
    
    private void iterator() {
        int x;
        String syll;
        int indexY, indexLastText;
        char lastChar, beforeLastChar, typeBeforeLastChar; //last char in the word, char before last in the word
        char syllBefore='V', syllAfter='V';
        StringBuilder theName; //temp 
        ArrayList<Integer> positionY = new ArrayList<Integer>();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            System.out.println(word);
            if(word.contains("y")){
                syll = Syllabilizer.getWordPattern(word);
                //System.out.println(syll);
                positionY = findPositions(word, 'y');


                for (x = 0; x < positionY.size(); x++) {
                    //System.out.println(positionY.get(x));
                    indexLastText = word.length()-1;
                    indexY = positionY.get(x);
                    beforeLastChar = word.charAt(indexLastText-1);
                    typeBeforeLastChar = changeChar(beforeLastChar);
                    if(indexY==indexLastText && typeBeforeLastChar!='V'){ //ban"dy" become ban"di"
                        theName = new StringBuilder(word);
                        theName.setCharAt(indexY, 'i');
                        word = theName.toString();
                    }
                    else{ 
                        if(indexY>0){
                            syllBefore = syll.charAt(indexY-1);
                        }
                        
                        if(indexY<indexLastText){
                            syllAfter = syll.charAt(indexY+1);
                        }
                        
                        System.out.println(syllBefore+" .. "+syllAfter);
                        if(syllBefore=='C' && syllAfter=='C'){ //emylia become emilia
                            theName = new StringBuilder(word);
                            theName.setCharAt(indexY, 'i');
                            word = theName.toString();
                        }
                        else{
                            System.out.println(word);
                            word = word;
                        }
                    }
                }
                
            }
                
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
    
    public static ArrayList<Integer> findPositions(String string, char character) {
        ArrayList<Integer> positions = new ArrayList<Integer>();
        for (int i = 0; i < string.length(); i++){
            if (string.charAt(i) == character) {
               positions.add(i);
            }
        }
        return positions;
    }
    public char changeChar(char c){
        char res='V';
        if(c=='a' || c=='i' || c=='u' || c=='e' || c=='o'){
            res = 'V';
        }
        else{
            res = c;
        }
        return res;
    }
}
