/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texttospeech;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kadek
 * This is a class for reduce the speed of a WAV file.
 * This class isn't used anymore.
 * AudioSpeedEqualizer has more advanced features.
 */
public class WAVSpeedReducer {
    private String fileName, filePath;
    private float tempo;
    
    private final String FFMPEG = "\"" + ConstantObjects.FFMPEGFOLDER + "ffmpeg\" ", YES = "-y ", PREFIX = "-i ", FILTER = "-filter:a ";
    private final String SPEEDPREFIX = "\"", SPEEDINFIX = "atempo=", SPEEDINFIX2 = ", ", SPEEDPOSTFIX = "\" -vn ";
    
    private Runtime rt;
    private Process proc;
    
    public WAVSpeedReducer(String fileName, String filePath, float tempo) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.tempo = tempo;
        speedReduce();
    }
    
    private void speedReduce() {
        String cmd = FFMPEG + YES + PREFIX + "\"" + filePath + fileName + ConstantObjects.FILEFORMAT + "\" " + FILTER + SPEEDPREFIX;
        
        if (tempo < 0.5f) {
            cmd += SPEEDINFIX + 0.5f + SPEEDINFIX2 + SPEEDINFIX + (1.0f - (0.5f - tempo)) + SPEEDPOSTFIX;
        } else {
            cmd += SPEEDINFIX + 0.5f + SPEEDPOSTFIX;
        }
        
        String outputName;
        if (filePath.equals(ConstantObjects.OUTPUTFOLDER)) {
            outputName = fileName + "1234567890";
            cmd += "\"" + ConstantObjects.OUTPUTFOLDER + outputName + ConstantObjects.FILEFORMAT + "\"";
        } else {
            outputName = fileName;
            cmd += "\"" + ConstantObjects.OUTPUTFOLDER + fileName + ConstantObjects.FILEFORMAT + "\"";
        }
        
        rt = Runtime.getRuntime();
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
        //System.out.println(cmd);
    }
}
