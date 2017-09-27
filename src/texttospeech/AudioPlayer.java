/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texttospeech;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Kadek
 * This is a class for play the final speech WAV.
 */
public class AudioPlayer {
    private String filePath;
    
    public AudioPlayer(String filePath) {
        this.filePath = filePath;
        play();
    }
    
    private void play() {
        // Duration is used as a paramter in FFPLAY, so it will automatically closed after specified duration.
        // 0.5 is added as safety limit.
        double duration = getDuration(filePath) + 0.5;
        
        // Build a process to run the FFPLAY.
        // Format: ffplay -nodisp -autoexit -t <time> <file path>
        // Example: ffplay -nodisp -autoexit -t 0.5 a.wav
        ProcessBuilder pb = new ProcessBuilder(ConstantObjects.FFMPEGFOLDER + "ffplay", "-nodisp", "-autoexit", "-t", "" + duration, filePath);
        pb.redirectErrorStream(true);
        Process proc = null;
        
        try {
            proc = pb.start();
        } catch (IOException ex) {
            Logger.getLogger(WAVConcatenator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // In Java, we need to print every stream in a process.
        // If not, buffer stack overflow will come and the program will hang forever.
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
    
    /**
     * getDuration is a method for count the duration of specified WAV file.
     * duration = file length / (frame size * frame rate)
     */
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
