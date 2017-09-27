/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texttospeech;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kadek
 * This is a class for append a WAV file to another WAV file.
 */
public class WAVConcatenator {

    private ArrayList<String> filePath;
    private String outputFile;

    private final String FFMPEG = ConstantObjects.FFMPEGFOLDER + "ffmpeg", YES = "-y", PREFIX = "-i", FILTER = "-filter_complex";
    private final String CONCATPREFIX = "\"", CONCATINFIX = " concat=n=", CONCATPOSTFIX = ":v=0:a=1[out]\"", CONCATPOSTFIX2 = "-map", CONCATPOSTFIX3 = "[out]";
    
    public WAVConcatenator(ArrayList<String> filePath) {
        this.filePath = filePath;
        concat();
    }

    private void concat() {
        /**
         * Format: ffmpeg -y <-i file> -filter_complex "<[idx:0]> concat=n=<n>:v=0:a=1[out]" -map "[out]" <output>
         * Example: ffmpeg -y -i ka.wav -i ad.wav -i de.wav -i ek.wav -filter_complex "[0:0][1:0][2:0][3:0] concat=n=4:v=0:a=1[out]" -map "[out]" kadek.wav
         */
        ArrayList<String> cmd = new ArrayList<String>();
        cmd.add(FFMPEG);
        cmd.add(YES);
        
        String filterParam = "";
        
        // Iterate every file.
        for (int i = 0; i < filePath.size(); i++) {
            cmd.add(PREFIX);
            cmd.add(filePath.get(i));
            filterParam += "[" + i + ":0]";
        }
        
        cmd.add(FILTER);
        cmd.add(CONCATPREFIX + filterParam + CONCATINFIX + filePath.size() + CONCATPOSTFIX);
        cmd.add(CONCATPOSTFIX2);
        cmd.add(CONCATPOSTFIX3);
        
        // Output name is a sequence of time: years_months_days-hours_minutes_seconds_milliseconds
        String outputName = new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss_SSS").format(new Date());
        outputFile = outputName;
        //outputName = "\"" + ConstantObjects.OUTPUTFOLDER + outputName + ConstantObjects.FILEFORMAT + "\"";
        outputName = ConstantObjects.OUTPUTFOLDER + outputName + ConstantObjects.FILEFORMAT;
        
        cmd.add(outputName);
        //System.out.println(cmd);
        
        ProcessBuilder pb = new ProcessBuilder(cmd);
        pb.redirectErrorStream(true);
        Process proc = null;
        
        try {
            proc = pb.start();
        } catch (IOException ex) {
            Logger.getLogger(WAVConcatenator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                
            }
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(WAVConcatenator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            proc.waitFor();
        } catch (InterruptedException ex) {
            Logger.getLogger(WAVConcatenator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        proc.destroyForcibly();
    }

    public String getOutputName() {
        return outputFile;
    }
}
