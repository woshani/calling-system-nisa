/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texttospeech;

import static java.lang.Math.toIntExact;
import java.util.ArrayList;

/**
 *
 * @author Kadek
 * This is a class for handle number-only token.
 */
public class NumberProcessor {
    private String prevPart, digitsString, outputName;
    private int order;
    private String spokenDigits;
    
    private final String units[] = {
        "", "1", "2", "3", "4", "5", "6", "7", "8", "9",
        "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"
    };
    
    private ArrayList<String> filePaths = new ArrayList<String>();
    
    public NumberProcessor(String prevPart, String digitsString, int order) {
        this.prevPart = prevPart;
        this.digitsString = digitsString;
        this.order = order;
        join();
    }
    
    private String digitsToSpoken(long n) {
        if (n < 20l) // 0 - 19
            return units[toIntExact(n)] + " ";
        if (n < 100l) // 20 - 99
            return units[toIntExact(n / 10l)] + " puluh " + digitsToSpoken(n % 10l);
        if (n < 200l) // 100 - 199
            return "100 " + digitsToSpoken(n % 100l);
        if (n < 1000l) // 200 - 999
            return units[toIntExact(n / 100l)] + " ratus " + digitsToSpoken(n % 100l);
        if (n < 2000l) // 1.000 - 1.999
            return "1000 " + digitsToSpoken(n % 1000l);
        if (n < 1000000l) // 2.000 - 999.999
            return digitsToSpoken(n / 1000l) + "ribu " + digitsToSpoken(n % 1000l);
        if (n < 1000000000l) // 1.000.000 - 999.999.999
            return digitsToSpoken(n / 1000000l) + "juta " + digitsToSpoken(n % 1000000l);
        if (n < 1000000000000l) // 1.000.000.000 - 999.999.999.999
            return digitsToSpoken(n / 1000000000l) + "milion " + digitsToSpoken(n % 1000000000l);
        if (n < 1000000000000000l) // 1.000.000.000.000 - 999.999.999.999.999
            return digitsToSpoken(n / 1000000000000l) + "bilion " + digitsToSpoken(n % 1000000000000l);
        
        return "";
    }
    
    private void join() {
        long fileDigits = Long.parseLong(digitsString);
        String spokenDigits;
        
        if (fileDigits == 0l) {
            spokenDigits = "0";
        } else {
            spokenDigits = digitsToSpoken(fileDigits);
        }
        
        //System.out.println(spokenDigits);
        String fileNames[] = spokenDigits.split(" ");
        
        if (order > 0) {
            filePaths.add(ConstantObjects.OUTPUTFOLDER + prevPart + ConstantObjects.FILEFORMAT);
        }
        
        for (int i = 0; i < fileNames.length; i++) {
            filePaths.add(ConstantObjects.WAVESETSOURCE + fileNames[i] + ConstantObjects.FILEFORMAT);
            //filePaths.add(ConstantObjects.WAVESETSOURCE + ConstantObjects.DIPHONESSOURCE + ConstantObjects.SPACEFILE + ConstantObjects.FILEFORMAT);
        }
        //System.out.println(filePaths.toString());
        
        filePaths.add(ConstantObjects.WAVESETSOURCE + ConstantObjects.SPACEFILE + ConstantObjects.FILEFORMAT);
        
        outputName = new WAVConcatenator(filePaths).getOutputName();
    }
    
    public String getOutputName() {
        return outputName;
    }
}
