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
    public String changeY(String word){
        String text = word;
        int x;
        StringBuilder theName;
        ArrayList<Integer> positionY = new ArrayList<>();
        positionY = Preprocessing.findPositions(word, 'y');
        
        return text;
    }

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
        
        /*String syl, sy;
        StringBuilder t;
        syl="struk";
        sy=Syllabilizer.getWordPattern(syl);
        t = new StringBuilder(syl);
        if(sy.equals("VCC") || sy.equals("CVV")){   //VCC ASY -> VC AS | CVV BOO -> CV BO
            t.deleteCharAt(2);
            System.out.println(t);
        }
        else if(sy.equals("CVCC") || sy.equals("CCVC")){    //CVCC HING	-> CVC HIN | CCVC SHIB -> CCV SHI
            t.deleteCharAt(3);
            System.out.println(t);
        }else if(sy.equals("CVVC")){ //CVVC NOOR -> CVC	NOR
            t.deleteCharAt(2);
            System.out.println(t);
        }else if(sy.equals("CCCV")){ //CCCV STRA -> CCV	TRA
            t.deleteCharAt(0);
            System.out.println(t);
        }else if(sy.equals("CCCVC")){ //CCCVC struk -> CVC RUK
            t.deleteCharAt(0);
            t.deleteCharAt(1);
            System.out.println(t);
        }*/
       
    }
    
}
