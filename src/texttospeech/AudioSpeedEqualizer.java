/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texttospeech;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Kadek
 * This is a class for equalize the duration of every WAV file.
 * The process is done by configuring the tempo.
 */
public class AudioSpeedEqualizer {
    private String sourceFolder, outputFolder;
    
    private ArrayList<String> fileNames;
    
    private final String FFMPEG = "\"" + ConstantObjects.FFMPEGFOLDER + "ffmpeg\" ", YES = "-y ", PREFIX = "-i ", FILTER = "-filter:a ";
    private final String SPEEDPREFIX = "\"", SPEEDINFIX = "atempo=", SPEEDINFIX2 = ", ", SPEEDPOSTFIX = "\" -vn ";
    
    public AudioSpeedEqualizer(String sourceFolder, ArrayList<String> fileNames, String outputFolder) {
        this.fileNames = fileNames;
        this.sourceFolder = sourceFolder;
        this.outputFolder = outputFolder;
        equalizer();
    }
    
    /**
     * tempo = WAV duration / desired duration
     * Desired duration is obtained either by average duration or maximal duration.
     */
    private void equalizer() {
        double averageLength = getAverageLength();
        //double averageLength = 0.0822617812258076 * ConstantObjects.LENGTH;
        //double averageLength = getMaxLength();
        System.out.println(averageLength);
        
        for (int i = 0; i < fileNames.size(); i++) {
            //double tempo = averageLength / getDuration(sourceFolder + fileNames.get(i) + ConstantObjects.FILEFORMAT);
            double tempo = getDuration(sourceFolder + fileNames.get(i) + ConstantObjects.FILEFORMAT) / averageLength;
            speedChanger(fileNames.get(i), tempo);
        }
    }
    
    /**
     * Format: ffmpeg -y -i <source> -filter:a "atempo=<tempo>" -vn <output>
     * FFMPEG tempo range: 0.5 - 2.0
     * If we need to adjust the tempo out of range, we should call atempo twice.
     * If tempo < 0.5 --> ffmpeg -y -i <source> -filter:a "atempo=<tempo1>, atempo=<tempo2>" -vn <output>
     * tempo1 = 0.5, tempo2 = 1 - (0.5 - tempo)
     * If tempo > 2.0 --> tempo1 = 2.0, tempo2 = 1.0 + (tempo - 2.0)
     * Example (tempo = 0.4): ffmpeg -y -i ka.wav -filter:a "atempo=0.5, atempo=0.9" -vn ka2.wav
     */
    private void speedChanger(String fileName, double tempo) {
        String cmd = FFMPEG + YES + PREFIX + "\"" + sourceFolder + fileName + ConstantObjects.FILEFORMAT + "\" " + FILTER + SPEEDPREFIX;
        
        if (tempo >= 0.5 && tempo <= 2.0) {
            cmd += SPEEDINFIX + tempo + SPEEDPOSTFIX;
        } else if (tempo < 0.5) {
            cmd += SPEEDINFIX + 0.5 + SPEEDINFIX2 + SPEEDINFIX + (1.0 - (0.5 - tempo)) + SPEEDPOSTFIX;
        } else {
            cmd += SPEEDINFIX + 2.0 + SPEEDINFIX2 + SPEEDINFIX + (1.0 + (tempo - 2.0)) + SPEEDPOSTFIX;
        }
        
        String outputName;
        if (sourceFolder.equals(ConstantObjects.OUTPUTFOLDER)) {
            outputName = fileName + "1234567890";
            cmd += "\"" + ConstantObjects.OUTPUTFOLDER + outputName + ConstantObjects.FILEFORMAT + "\"";
        } else {
            outputName = fileName;
            cmd += "\"" + outputFolder + fileName + ConstantObjects.FILEFORMAT + "\"";
        }
        
        Runtime rt = Runtime.getRuntime();
        Process proc = null;
        
        try {
            proc = rt.exec(cmd);
        } catch (IOException ex) {
            Logger.getLogger(WAVSpeedReducer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            proc.waitFor();
        } catch (InterruptedException ex) {
            Logger.getLogger(WAVSpeedReducer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        proc.destroyForcibly();
        
        if (!outputName.equals(fileName)) {
            try {
                java.nio.file.Files.delete(Paths.get(ConstantObjects.OUTPUTFOLDER + fileName + ConstantObjects.FILEFORMAT));
                java.nio.file.Files.move(Paths.get(ConstantObjects.OUTPUTFOLDER + outputName + ConstantObjects.FILEFORMAT), Paths.get(ConstantObjects.OUTPUTFOLDER + fileName + ConstantObjects.FILEFORMAT), StandardCopyOption.ATOMIC_MOVE);
            } catch (IOException ex) {
                Logger.getLogger(WAVSpeedReducer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(cmd);
    }
    
    private double getAverageLength() {
        double sum = 0.0;
        
        for (int i = 0; i < fileNames.size(); i++) {
            sum += getDuration(sourceFolder + fileNames.get(i) + ConstantObjects.FILEFORMAT);
        }
        
        return sum / fileNames.size();
    }
    
    private double getMaxLength() {
        double max = getDuration(sourceFolder + fileNames.get(0) + ConstantObjects.FILEFORMAT);
        
        for (int i = 1; i < fileNames.size(); i++) {
            double duration = getDuration(sourceFolder + fileNames.get(i) + ConstantObjects.FILEFORMAT);
            if (duration > max)
                max = duration;
        }
        
        return max;
    }
    
    private double getDuration(String wavPath) {
        double duration = 0.0;
        try {
            File file;
            double length;
            AudioInputStream stream;
            AudioFormat format;
            long frameSize;
            double frameRate;

            file = new File(wavPath);
            length = file.length();
            stream = AudioSystem.getAudioInputStream(file);
            format = stream.getFormat();
            frameSize = format.getFrameSize();
            frameRate = format.getFrameRate();
            //duration = Math.round((length / (frameSize * frameRate)) * 100.0) / 1000.0;
            duration = (length / (frameSize * frameRate));
            stream.close();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(WAVOverlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WAVOverlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //System.gc();
        return duration;
    }
}
