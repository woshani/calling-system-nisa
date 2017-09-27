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
 * This is a class for overlay two or more WAV file.
 */
public class WAVOverlayer {

    private String outputString;
    private ArrayList<String> fileNames, wavClass;
    private ArrayList<Double> durations = new ArrayList<Double>();
    
    // Constant number for FFMPEG parameter.
    private final double MSCONSTANT = 0.075; // 0.055 | 0.075 | 0.095
    private final String FFMPEG = ConstantObjects.FFMPEGFOLDER + "ffmpeg", YES = "-y", PREFIX = "-i", FILTER = "-filter_complex";
    private final String SILENTPREFIX = "\"anullsrc,atrim=0:", SILENTPOSTFIX = "[s];[s]concat=2:v=0:a=1\"";
    private final String OVERLAYFILTER = "\"[0]asetnsamples=8192[a];[1]asetnsamples=8192[b];[a][b]amix=inputs=2:duration=longest:dropout_transition=0, volume=2\"";

    public WAVOverlayer(ArrayList<String> fileNames, ArrayList<String> wavClass, String outputString) {
        this.fileNames = (ArrayList<String>) fileNames.clone();
        this.wavClass = wavClass;
        this.outputString = outputString;

        overlayer();
        //new WAVSpeedReducer(outputString, ConstantObjects.OUTPUTFOLDER, 0.59f);
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
    
    // This method is only count every audio duration.
    private void setDurations() {
        File file;
        double length;
        AudioInputStream stream;
        AudioFormat format;
        long frameSize;
        double frameRate;
        double duration;

        for (int i = 0; i < fileNames.size(); i++) {
            duration = getDuration(wavClass.get(i) + fileNames.get(i) + ConstantObjects.FILEFORMAT);
            durations.add(duration);
        }
    }

    public ArrayList<Double> getDurations() {
        setDurations();
        return durations;
    }
    
    /**
     * Add silent silent sound in a particular time to a WAV file.
     */
    private void addSilentSound(String inputWav, String outputWav) {
        double duration = getDuration(inputWav);
        double param = (duration / 20.0) + MSCONSTANT;
        
        // Format: ffmpeg -y -i <source> -filter_complex "anullsrc,atrim=0:<param>[s];[s]concat=2:v=0:a=1" <output>
        // Example: ffmpeg -y -i ka.wav -filter_complex "anullsrc,atrim=0:0.098[s];[s]concat=2:v=0:a=1" ka2.wav
        //String cmd[] = {FFMPEG, YES, PREFIX, inputWav, FILTER, SILENTPREFIX + Math.round(param * 1000.0) / 1000.0 + SILENTPOSTFIX, outputWav};
        String cmd[] = {FFMPEG, YES, PREFIX, inputWav, FILTER, SILENTPREFIX + param + SILENTPOSTFIX, outputWav};
        //System.out.println(cmd[5]);
        
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
        //System.gc();
    }
    
    /**
     * Overlay 2 WAV files.
     */
    private void wavCombine(String leftWav, String rightWav, String outputWav) {
        // Format: ffmpeg -y -i <right> -i <left> -filter_complex "[0]asetnsamples=8192[a];[1]asetnsamples=8192[b];[a][b]amix=inputs=2:duration=longest:dropout_transition=0, volume=2" <output>
        // Example: ffmpeg -y -i ek.wav -i kade.wav -filter_complex "[0]asetnsamples=8192[a];[1]asetnsamples=8192[b];[a][b]amix=inputs=2:duration=longest:dropout_transition=0, volume=2" kadek.wav
        String cmd[] = {FFMPEG, YES, PREFIX, rightWav, PREFIX, leftWav, FILTER, OVERLAYFILTER, outputWav};
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
        //System.gc();
    }
    
    /**
     * Iterator for addSilentSound and wavCombine.
     */
    private void overlayer() {
        int fileLength = fileNames.size();
        /**
         * Kadek --> "ka" + "ad" + "de" + "ek"
         * 1.1. Add silent sound to "ek". --> NOT COUNTED.
         * 1.2. Overlay "de" and "ek". --> NOT COUNTED.
         * 2.1. Add silent sound to "dek".
         * 2.2. Overlay "ad" and "dek".
         * 3.1. Add silent sound to "adek".
         * 3.2. Overlay "ka" and "adek".
         * 
         * Iteration = Number of phonemes - 2 - 1
         * Why -2? It is because the iteration goes from N until 0.
         */
        int iteration = fileLength - 2 - 1;
        int fileIdx = 0, fileListIdx = 0;
        ArrayList<String> tempFileList = new ArrayList<String>();

        String outputFilePath = ConstantObjects.OUTPUTFOLDER;
        String outputFile = outputFilePath + outputString + ConstantObjects.FILEFORMAT;
        
        //1.1.
        String realFile = wavClass.get(fileLength - 1) + fileNames.get(fileLength - 1) + ConstantObjects.FILEFORMAT;
        String tempFile = outputFilePath + fileIdx + ConstantObjects.FILEFORMAT;
        tempFileList.add(tempFile);
        addSilentSound(realFile, tempFile);
        fileIdx++;
        
        //1.2.
        realFile = wavClass.get(fileLength - 2) + fileNames.get(fileLength - 2) + ConstantObjects.FILEFORMAT;
        tempFile = outputFilePath + fileIdx + ConstantObjects.FILEFORMAT;
        tempFileList.add(tempFile);
        wavCombine(realFile, tempFileList.get(fileListIdx), tempFile);
        fileListIdx++;
        fileIdx++;

        if (fileLength > 2) {

            for (int i = iteration; i >= 0; i--) {
                tempFile = outputFilePath + fileIdx + ConstantObjects.FILEFORMAT;
                tempFileList.add(tempFile);
                addSilentSound(tempFileList.get(fileListIdx), tempFile);
                fileListIdx++;
                fileIdx++;
                realFile = wavClass.get(i) + fileNames.get(i) + ConstantObjects.FILEFORMAT;
                tempFile = outputFilePath + fileIdx + ConstantObjects.FILEFORMAT;
                tempFileList.add(tempFile);
                wavCombine(realFile, tempFileList.get(fileListIdx), tempFile);
                fileListIdx++;
                fileIdx++;
            }
        }
        
        // Change filename from numbers to desired output name.
        try {
            //new File(tempFileList.get(fileListIdx)).renameTo(new File(outputFile));
            java.nio.file.Files.move(Paths.get(tempFileList.get(fileListIdx)), Paths.get(outputFile), StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException ex) {
            Logger.getLogger(WAVOverlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        removeTemp(tempFileList);
    }
    
    // Remove all existing temporary files in the list.
    private void removeTemp(ArrayList<String> tempFileList) {
        System.gc();
        //System.out.println(tempFileList);
        int size = tempFileList.size();
        for (int i = 0; i < size - 1; i++) {
            try {
                // Format: del "<file>" /f /s /q
                String cmd = "cmd /c del " + "\"" + tempFileList.get(i) + "\" " + "/f /s /q";
                Runtime.getRuntime().exec(cmd);
            } catch (IOException ex) {
                Logger.getLogger(WAVOverlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
