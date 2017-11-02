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
 * @author Kadek & Anisa
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
        Map<String,String> myMap = new HashMap<String,String>(); //p, t, c, k, s,  
        /*myMap.put("p", "b");
        myMap.put("b", "p");
        myMap.put("t", "d");
        myMap.put("d", "t");
        myMap.put("c", "j");
        myMap.put("j", "c");
        myMap.put("k", "g");
        myMap.put("g", "k");
        myMap.put("f", "v");*/
        myMap.put("v", "f");
        myMap.put("w", "f");
        //myMap.put("s", "z");
        myMap.put("z", "s");
        myMap.put("y", "i");
        //myMap.put("sy", "s");
        //myMap.put("kh", "k");
        //myMap.put("ch", "c");
        //myMap.put("gh", "g");
        myMap.put("y", "i");
        myMap.put("x", "s");
        myMap.put("q", "k");
        return myMap;
    }
    
    //change syllable; 
    private String changeSyll(String input){
        String syll, res = input;
        char lastSyll, lastSyll_1, firstSyll, secondSyll, lastInput, firstInput, secondInput;
        int size;
        
        syll = Syllabilizer.getWordPattern(input);
        size = syll.length();
        lastSyll = syll.charAt(size-1);
        lastSyll_1 = syll.charAt(size-2);
        lastInput = input.charAt(size-1);
        
        
        //firstSyll = syll.charAt(0);
        //secondSyll = syll.charAt(1);

        if(myMap.containsKey(Character.toString(lastInput))){     
            System.out.println("1");
            //input = kaw, output = kaf; input = lav, output = laf         
            res = input.substring(0, size-1).concat(myMap.get(Character.toString(lastInput)));
      
        }
        secondInput = res.charAt(1);
        if(myMap.containsKey(Character.toString(secondInput))){     
            System.out.println("1.3");
            //input = dwi, output = dvi; input = dxa, output = dsa 
            res = res.charAt(0)+myMap.get(Character.toString(secondInput))+res.charAt(2);
            //res = input.charAt(0).concat(myMap.get(Character.toString(secondInput)))).concat(input.substring(1, size-1));

        }
        firstInput = res.charAt(0);
        if(myMap.containsKey(Character.toString(firstInput))){     
            System.out.println("1.2");
            //input = won, output = fon; input = qak, output = kak         
            res = (myMap.get(Character.toString(firstInput))).concat(res.substring(1, size));

        }
        //new one
        firstInput = res.charAt(0);
        secondInput = res.charAt(1);
        if(firstInput==secondInput){ //ggo -> go
            res = res.substring(1);
        }
        if(!isAvailable(res)){
            if(Syllabilizer.getWordPattern(res).equals("CCV")){ //kfi -> fi (example, there is no kfi in database)
                res = res.substring(1);
            }
            else{   //max -> ma (example, there is no max in database)
                res = res.substring(0,1);
            }
        }
        /*if(!isAvailable(res)){  
            //if 'fon' not available in databse output will become 'fa'
                res = removeChar(input, 1);
            }
        */
        /*//if(input.contains(res))
        //input = zai, ouput za; input = mao, output = ma; input = zee, outpue = ze
        else if((lastSyll=='V' && lastSyll_1=='V')){
            System.out.println("2");
            res = input.substring(0, size-1);
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
        }*/
        
        System.out.println(res);
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
                // Phonetize the word. Syllable concatenation
                ArrayList<String> phonemes = new Phonetizer(syl).getPhonemes();
                ArrayList<String> overlayList = new ArrayList<String>();
                ArrayList<String> overlayListPath = new ArrayList<String>();
                //System.out.println(phonemes);
                String sy;
                StringBuilder t;
                sy=Syllabilizer.getWordPattern(syl);
                t = new StringBuilder(syl);
                if(sy.equals("VCC") || sy.equals("CVV") || sy.equals("CVV")){   //VCC ASY -> VC AS | CVV BOO -> CV BO, ZAI -> ZA 
                    t.deleteCharAt(2);
                    //System.out.println(t);
                }else if(sy.equals("CCVV")){    //CCVV  KHAI 
                    t.deleteCharAt(1);          
                    if(isAvailable(t.toString())){  //KAI
                        String s=t.toString();
                    }
                    else{
                        t.deleteCharAt(2);          //KA
                        String s=t.toString();
                    }
                    
                }else if(sy.equals("CVCC") ){    //CVCC HING	-> CVC HIN | 
                    t.deleteCharAt(3);
                    //System.out.println(t);
                }else if(sy.equals("CCVC")){ //CCVC SHIB -> CVC SIB
                    t.deleteCharAt(1);
                    //System.out.println(t);
                }else if(sy.equals("CVVC")){ //CVVC NOOR -> CVC	NOR
                    t.deleteCharAt(2);
                    //System.out.println(t);
                }else if(sy.equals("CCCV")){ //CCCV STRA -> CCV	TRA
                    t.deleteCharAt(0);
                    //System.out.println(t);
                }else if(sy.equals("CCCVC")){ //CCCVC struk -> CVC RUK
                    t.deleteCharAt(0);
                    t.deleteCharAt(1);
                    //System.out.println(t);
                }
                String s=t.toString();
                if(isAvailable(s)){
                    System.out.println(s);
                    if(Syllabilizer.getWordPattern(s).equals("C")){
                        concatList.add(getPath(ConstantObjects.SPACEFILE, 0));
                    }
                    else{
                        concatList.add(getPath(s, 0));
                    }
                }
                else{
                    System.out.println("s = "+s);
                    s=changeSyll(syl);
                    System.out.println(s);
                    if(isAvailable(s)){
                        if(Syllabilizer.getWordPattern(s).equals("C")){  
                            concatList.add(getPath(ConstantObjects.SPACEFILE, 0));
                        }
                        else{
                            concatList.add(getPath(s, 0));
                        }
                        
                    }
                    else{
                        concatList.add(getPath(ConstantObjects.SPACEFILE, 0));
                    }
                }
                /*String s=syl;   //s syl already change
                s=changeSyll(syl);
                String sy;
                sy=Syllabilizer.getWordPattern(syl);
                System.out.println(s);
                if(isAvailable(s)){
                    concatList.add(getPath(s, 0));
                }
                else{
                    concatList.add(getPath(ConstantObjects.SPACEFILE, 0));
                }*/
                
                
                //comment code below is diphone cotcatenation, we not used it anymore
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
    
    public ArrayList<Integer> findPositions(String string, char character) {
        ArrayList<Integer> positions = new ArrayList<>();
        for (int i = 0; i < string.length(); i++){
            if (string.charAt(i) == character) {
               positions.add(i);
            }
        }
        return positions;
    }
    public String removeChar(String str, Integer n) {
        String front = str.substring(0, n);
        String back = str.substring(n+1, str.length());
        return front + back;
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