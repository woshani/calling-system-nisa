/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texttospeech;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * @author Kadek
 * This class used as container of every constant object that called in most of other classes.
 */
public class ConstantObjects {
    /**
     * WAVESETSOURCE --> Path of WAV Collection folder.
     * OUTPUTFOLDER --> Path of Temporary Output folder
     * FFMPEGFOLDER --> Path of FFMPEG
     * System.getProperty("user.dir") will provides current working directory.
     * 
     * WAVLIST is a list contains file names of all the existing WAV files without the file extension.
     * 
     * LENGTH and FILTERED lists are only used when we need to call AudioSpeedEqualizer.
     * LENGTH estimates file name length (ka.wav --> 2).
     * Please swap FILTEREDLIST when you need to use it.
     */
    public static final String WAVESETSOURCE = System.getProperty("user.dir") + "\\WAVCollection\\";
    public static final String OUTPUTFOLDER = System.getProperty("user.dir") + "\\OutputTemp\\";
    public static final String FFMPEGFOLDER = System.getProperty("user.dir") + "\\FFMPEG\\";
    
    public static final String FILEFORMAT = ".wav", SPACEFILE = "_", MINISPACEFILE = "_05";
    
    public static final HashSet<String> WAVLIST = new HashSet<String>(removeExtension(new ArrayList<String>(Arrays.asList(new File(WAVESETSOURCE).list()))));
    
    public static final int LENGTH = 3;
    //public static final ArrayList<String> FILTEREDLIST = filterByLength(removeExtension(new ArrayList<String>(Arrays.asList(new File(WAVESETSOURCE).list()))), LENGTH);
    public static final ArrayList<String> FILTEREDLIST = new ArrayList<String>();
    
    public static final ArrayList<ArrayList<String>> ACRONYMS = new ArrayList<ArrayList<String>>(Arrays.asList(
                new ArrayList<String>(Arrays.asList("a", "ab", "abd")),
                new ArrayList<String>(Arrays.asList("assoc")),
                new ArrayList<String>(Arrays.asList("b", "bn")),
                new ArrayList<String>(Arrays.asList("bt", "bti")),
                new ArrayList<String>(Arrays.asList("dr")),
                new ArrayList<String>(Arrays.asList("h")),
                new ArrayList<String>(Arrays.asList("hj", "hjh")),
                new ArrayList<String>(Arrays.asList("ir")),
                new ArrayList<String>(Arrays.asList("m", "md", "moh", "mohd", "muh", "muhd")),
                new ArrayList<String>(Arrays.asList("prof")),
                new ArrayList<String>(Arrays.asList("r")),
                new ArrayList<String>(Arrays.asList("s")),
                new ArrayList<String>(Arrays.asList("sh")),
                new ArrayList<String>(Arrays.asList("sy")),
                new ArrayList<String>(Arrays.asList("w"))
        ));
    public static final ArrayList<String> ALIAS = new ArrayList<String>(Arrays.asList(
                                                    "abdul",
                                                    "associate",
                                                    "bin",
                                                    "binti",
                                                    "dokter",
                                                    "haji",
                                                    "hajah",
                                                    "jurutera",
                                                    "muhammad",
                                                    "professor",
                                                    "raden",
                                                    "syeik",
                                                    "sharifa",
                                                    "syed",
                                                    "wan"
    ));
    
    /**
     * searchAcronyms is a method for search the index of the acronym in the list.
     * searchAcronyms will return -1 when the specified word is not in the list.
     */
    public static int searchAcronyms(ArrayList<ArrayList<String>> lol, String word) {
        for (int i = 0; i < 10; i++) {
            if (lol.get(i).contains(word))
                return i;
        }
        
        return -1;
    }
    
    /**
     * removeExtension is a method for remove WAV file extension.
     * For example: "a.wav" --> "a".
     */
    public static ArrayList<String> removeExtension(ArrayList<String> inputList) {
        ArrayList<String> outputList = (ArrayList<String>) inputList.clone();
        for (int i = 0; i < outputList.size(); i++) {
            outputList.set(i, outputList.get(i).replace(FILEFORMAT, ""));
        }
        return outputList;
    }
    
    /**
     * filterByLength is a method for filter the WAV file list, so it will contains only specified length characters.
     */
    public static ArrayList<String> filterByLength(ArrayList<String> inputList, int length) {
        ArrayList<String> list = new ArrayList<String>();
        
        for (int i = 0; i < inputList.size(); i++) {
            if (inputList.get(i).length() == length)
                list.add(inputList.get(i));
        }
        
        return list;
    }
}
