/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texttospeech;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kadek
 * This is a class for handle letter-only token.
 */
public class WordOnlyProcessor {

    private String word, outputName, prevPart;
    private int order;

    private ArrayList<String> concatList = new ArrayList<String>();
    private ArrayList<String> syllable;
    private ArrayList<String> wordParts;
    
    private static final Map<String, String> myMap = createMap();
    private static Map<String, String> createMap()
    {
        Map<String,String> myMap = new HashMap<String,String>();
        myMap.put("p", "b");
        myMap.put("b", "p");
        myMap.put("t", "d");
        myMap.put("d", "t");
        myMap.put("c", "j");
        myMap.put("j", "c");
        myMap.put("k", "g");
        myMap.put("g", "k");
        myMap.put("f", "v");
        myMap.put("v", "f");
        myMap.put("s", "z");
        myMap.put("z", "s");
        myMap.put("sy", "s");
        myMap.put("kh", "k");
        myMap.put("ch", "c");
        myMap.put("gh", "g");
        myMap.put("y", "i");
        return myMap;
    }
    
    //change syllable; 
    private String changeSyll(String input){
        String syll, res = input;
        char lastSyll, lastSyll_1, firstSyll, secondSyll, lastInput;
        int size;
        
        syll = Syllabilizer.getWordPattern(input);
        size = syll.length();
        lastSyll = syll.charAt(size-1);
        lastSyll_1 = syll.charAt(size-2);
        lastInput = input.charAt(size-1);
        firstSyll = syll.charAt(0);
        secondSyll = syll.charAt(1);

        if(myMap.containsKey(Character.toString(lastInput))){     
            System.out.println("1");
            //input = zab, output = zap; input = my, output = mi         
            res = input.substring(0, size-1).concat(myMap.get(Character.toString(lastInput)));
            if(!isAvailable(res)){  
            //if 'zap' not available in databse output will become 'za'
                res = input.substring(0, size-1);
            }        
        }
        //input = zai, ouput za; input = mao, output = ma; input = zee, outpue = ze
        else if((lastSyll=='V' && lastSyll_1=='V')){
            System.out.println("2");
            res = res = input.substring(0, size-1);
        }
        //input = khai, ouput = kai
        else if(firstSyll=='C' && secondSyll=='C'){
            System.out.println("3");
            res = input.charAt(0)+input.substring(2);
        }
        //input = zam, output = za
        else{
            System.out.println("4");
            res = res = input.substring(0, size-1);
        }
        
        
        return res;
        
    }
    
    public WordOnlyProcessor(String prevPart, String word, int order) {
        this.word = word;
        this.prevPart = prevPart;
        this.order = order;
        //wordPattern = Syllabilizer.getWordPattern(word);
        //syllable = Syllabilizer.getSyllable(wordPattern);
        syllable = Syllabilizer.getSyllable(word);
        wordParts = Syllabilizer.syllableToWords(syllable, word);
        //System.out.println(wordParts);
        makeWord();
    }
    
    private void makeWord() {
        if (order > 0) {
            concatList.add(getPath(prevPart, 1));
        }
        
        // Iterate all of the word pattern (words version of syllable).
        for (String syl : wordParts) {
            System.out.println(syl);
            // If it is already available in the WAV source.
            if (isAvailable(syl)) {
                //System.out.println(syl);
                concatList.add(getPath(syl, 0));
            } 
            else { // If not, make a new one.
                // Phonetize the word.
                ArrayList<String> phonemes = new Phonetizer(syl).getPhonemes();
                ArrayList<String> overlayList = new ArrayList<String>();
                ArrayList<String> overlayListPath = new ArrayList<String>();
                //System.out.println(phonemes);
                String s=syl;
                s=changeSyll(syl);
                System.out.println(s);
                //if(!isAvailable(s)){
                  //  new WAVOverlayer(overlayList, overlayListPath, syl);
                    concatList.add(getPath(s, 0));
                //}
                /*else{
                    // Iterate every phoneme.
                    for (String phoneme : phonemes) {
                        // If it's already available
                        if (isAvailable(phoneme)) {
                            overlayList.add(phoneme);
                            overlayListPath.add(ConstantObjects.WAVESETSOURCE);
                        } else { // If not, make a new phoneme.

                            ArrayList<String> concatList2 = new ArrayList<String>();
                            concatList2.add(getPath(String.valueOf(phoneme.charAt(0) + "_"), 0));
                            concatList2.add(getPath(String.valueOf(phoneme.charAt(1) + "_"), 0));

                            // Join the two characters.
                            String concatList2Output = new WAVConcatenator(concatList2).getOutputName();
                            overlayList.add(concatList2Output);
                            overlayListPath.add(ConstantObjects.OUTPUTFOLDER);
                        }
                    }
                    //System.out.println("XX" + overlayList);
                    // Overlay all of the phonemes.
                    new WAVOverlayer(overlayList, overlayListPath, syl);
                    concatList.add(getPath(syl, 1));
                }*/
                
            }

            //concatList.add(getPath(ConstantObjects.MINISPACEFILE, 0));
        }
        
        // Add a long space.
        concatList.add(getPath(ConstantObjects.SPACEFILE, 0));
        
        // Join all of the parts.
        outputName = new WAVConcatenator(concatList).getOutputName();
        //System.out.println(concatList);
    }

    private boolean isAvailable(String word) {
        return ConstantObjects.WAVLIST.contains(word);
    }

    private String getPath(String word, int folder) {
        if (folder == 0) {
            return ConstantObjects.WAVESETSOURCE + word + ConstantObjects.FILEFORMAT;
        } else {
            return ConstantObjects.OUTPUTFOLDER + word + ConstantObjects.FILEFORMAT;
        }
    }

    public String getOutputName() {
        return outputName;
    }
    

    
}
//where the myMab came from
/*
        Bahsa Melayu place of articulation
        *plosive
        labial          = p    b
        alveolar        = t    d
        post-alveolar   = c    j
        velar           = k    g    (kalah)
        glottal         = k (bapak, rakyat)
        
        *fricative
        labial          = f     v
        alveolar        = s     z (saya     zaman)
        post-alveolar   = sy
        velar           = x
        glottal         = h
        dental          = s     z (selasa   izin)
        
        *nasal
        labial          = m
        alveolar        = n
        post-alveolar   = ny
        velar           = ng

        *trill
        alveolar        = r
        
        **approximant
        palatal         = j
        labial-velar    = w
        
        **lateral
        alveolar        = l
   
    */