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
 * 
 * This is a class for handle alphanumeric token.
 */
public class AlphanumericProcessor {
    private String prevPart, word, outputName;
    private int order;

    private ArrayList<String> filePaths = new ArrayList<String>();
    
    public AlphanumericProcessor(String prevPart, String word, int order) {
        this.prevPart = prevPart;
        this.word = word;
        this.order = order;
        join();
    }
    
    private void join() {
        // If word is not the first word in the text, then add previous part to the top of the list.
        if (order > 0) {
            filePaths.add(ConstantObjects.OUTPUTFOLDER + prevPart + ConstantObjects.FILEFORMAT);
        }
        
        // Alphanumeric token handled by iterating every character and add it to the list.
        // Minispace is also added after each character.
        for (int i = 0; i < word.length(); i++) {
            filePaths.add(ConstantObjects.WAVESETSOURCE + word.charAt(i) + ConstantObjects.FILEFORMAT);
            filePaths.add(ConstantObjects.WAVESETSOURCE + ConstantObjects.MINISPACEFILE + ConstantObjects.FILEFORMAT);
        }
        
        // Add long space as a differentiator of every word in the text.
        filePaths.add(ConstantObjects.WAVESETSOURCE + ConstantObjects.SPACEFILE + ConstantObjects.FILEFORMAT);

        outputName = new WAVConcatenator(filePaths).getOutputName();
    }

    public String getOutputName() {
        return outputName;
    }
}
