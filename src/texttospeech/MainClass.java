/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texttospeech;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kadek
 * This is the main class of the application.
 * All of the methods below are for testing purpose only. We don't need them in real execution.
 * Just configure the main method.
 */
public class MainClass {
    
    public void wavOverlayerTest() {
        ArrayList<String> fileNames = new ArrayList<String>(Arrays.asList("ka", "ad"));
        String outputString = "kad";
        String wavClass = ConstantObjects.WAVESETSOURCE ;
        ArrayList<String> wavClasses = new ArrayList<String>(Arrays.asList(wavClass, wavClass, wavClass, wavClass));
        WAVOverlayer wo = new WAVOverlayer(fileNames, wavClasses, outputString);
        System.out.println(wo.getDurations());
    }
    
    public void wavConcatenatorTest() {
        String path = "C:\\Users\\Kadek\\Documents\\Pemrograman Berorientasi Objek\\Source Code\\FFmpeg\\unit_storage\\diphones\\";
        ArrayList<String> filePaths = new ArrayList<String>(Arrays.asList(path + "ko.wav", path + "so.wav", path + "ong.wav"));
        WAVConcatenator wc = new WAVConcatenator(filePaths);
        System.out.println(wc.getOutputName());
    }
    
    public void wavSpeedReducerTest() {
        String fileName = "joined_nombor_0.06";
        //String path = "C:\\Users\\Kadek\\Documents\\Pemrograman Berorientasi Objek\\Source Code\\FFmpeg\\";
        String path = "C:\\Users\\Kadek\\Documents\\Pemrograman Berorientasi Objek\\Source Code\\FFmpeg\\Output\\";
        float tempo = 0.4f;
        WAVSpeedReducer ws = new WAVSpeedReducer(fileName, path, tempo);
    }
    
    public void phonetizerTest() {
        String word = "ka";
        Phonetizer pz = new Phonetizer(word);
        ArrayList<String> phonemes = pz.getPhonemes();
        System.out.println(phonemes.toString());
    }
    
    public void availableWordProcessor() {
        String prevPart = "_", fileName = "effendi";
        int order = 0;
        AvailableWordProcessor aw = new AvailableWordProcessor(prevPart, fileName, order);
        System.out.println(aw.getOutputName());
    }
    
    public void numberProcessorTest() {
        String prevPart = "_";
        String fileName = "999999999999999";
        int order = 0;
        NumberProcessor np = new NumberProcessor(prevPart, fileName, order);
        System.out.println(np.getOutputName());
    }
    
    public void wordOnlyProcessorTest() {
        String prevPart = "", word = "hide";
        int order = 0;
        
        String wop = new WordOnlyProcessor(prevPart, word, order).getOutputName();
        System.out.println(wop);
    }
    
    public void syllabilizerTest() {
        System.out.println(Syllabilizer.SYLLABLES);
        System.out.println(Syllabilizer.WORDPATTERNS);
        String word = "ayam".toLowerCase();
        String pattern = Syllabilizer.getWordPattern(word);
        ArrayList<String> syllable = Syllabilizer.getSyllable(pattern);
        System.out.println(syllable);
        System.out.println(Syllabilizer.syllableToWords(syllable, word));
    }
    
    public void audioSpeedEqualizerTest() {
        String sourceFolder = ConstantObjects.WAVESETSOURCE;
        String outputFolder = "C:\\Users\\Kadek\\Documents\\Pemrograman Berorientasi Objek\\Source Code\\FFmpeg\\EqualizedWAV2\\";
        //ArrayList<String> fileNames = new ArrayList<String>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"));
        ArrayList<String> fileNames = ConstantObjects.FILTEREDLIST;
        new AudioSpeedEqualizer(sourceFolder, fileNames, outputFolder);
    }
    
    public void alphanumericProcessorTest() {
        String prevPart = "_";
        String word = "jkt48";
        int order = 0;
        
        String outputName = new AlphanumericProcessor(prevPart, word, order).getOutputName();
        System.out.println(outputName);
    }
    
    public void preprocessingTest() {
        String originalText = "Prof. Dr. Datuk Ariffin bin Syakib M. C. S.";
        Preprocessing pp = new Preprocessing(originalText);
        
    }
    
    public void test() {
        String name = "Kadek Dwi Budi Utama".toLowerCase();
        String words[] = name.split(" ");
        System.out.println(words[2]);
        System.out.println("123".matches("\\d+"));
        System.out.println("Kadek123 utama!@#56?/".replaceAll("\\p{P}", " "));
        System.out.println("Kadek123 utama!@#56?/".replaceAll("\\d", ""));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()));;
        System.out.println("Kadek123 D45wi".replaceAll("([^\\d-]?)(-?[\\d\\.]+)([^\\d]?)", "$1 $2 $3").replaceAll(" +", " "));
        System.out.println("Ka12dek".matches(".*\\d+.*"));
        System.out.println("Kadek".substring(0, 2));
        System.out.println((int) (Math.log10(100) + 1));
        System.out.println(0 / 10);
        String digits[] = {"", "", "puluh", "ratus", "ribu", "juta", "milion", "bilion"};
        System.out.println(digits[2]);
        System.out.println("kadek".replaceAll("(?![aiueo])[a-z]", "C").replaceAll("[aiueo]", "V"));
        ArrayList<String> coba = new ArrayList<String>();
        coba.add("a");
        coba.clear();
        System.out.println(coba);
        System.out.println("C".contains("CCCVC"));
    }
    
    
    //Dictionary based on table above
    /*private static final Map<String, String> myMap = createMap();
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
        
    }*/

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainClass mc = new MainClass();
        //mc.wavConcatenatorTest();
        //mc.wavOverlayerTest();
        //mc.wavSpeedReducerTest();
        //mc.phonetizerTest();
        //mc.test();
        //mc.numberProcessorTest();
        //mc.wordSyllablesProcessorTest();
        //mc.syllabilizerTest();
        //mc.audioSpeedEqualizerTest();
        //System.out.println(0.1645235624516152 / 2.0);
        //mc.availableWordProcessor();
        //mc.alphanumericProcessorTest();
        //mc.preprocessingTest();
        //mc.wordOnlyProcessorTest();
        //System.out.println(System.getProperty("user.dir"));
        new GUI().setVisible(true);
        //System.out.println(Syllabilizer.getWordPattern("sayang"));
        //String s="zap";
        //System.out.println(mc.changeSyll(s));
        //mc.searchInDatabase(s);
        
        //String text = "A'lix Al-Ghazali S. Kom";
        //System.out.println(text.replace("\'", "").replace(".", " ").replace("-", " "));
                
    }
    
}
