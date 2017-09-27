/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texttospeech;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kadek
 * This is a class for clear the OutputTemp folder after playing speech.
 */
public class OutputFolderCleaner {

    public OutputFolderCleaner() {
        cleaner();
    }

    private void cleaner() {
        System.gc();
        try {
            // Real format in CMD: del "<folder>*.*" /f /s /q
            String cmd = "cmd /c del " + "\"" + ConstantObjects.OUTPUTFOLDER + "*.*" + "\" " + "/f /s /q";
            Runtime.getRuntime().exec(cmd);
        } catch (IOException ex) {
            Logger.getLogger(WAVOverlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
