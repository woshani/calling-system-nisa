/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texttospeech;

import java.util.ArrayList;

/**
 *
 * @author Kadek
 * This is a class for handle the available word in the WAV list.
 */
public class AvailableWordProcessor {

    private String prevPart, fileName, outputName;
    private int order;

    private ArrayList<String> filePaths = new ArrayList<String>();

    public AvailableWordProcessor(String prevPart, String fileName, int order) {
        this.prevPart = prevPart;
        this.fileName = fileName;
        this.order = order;
        join();
    }

    private void join() {
        if (order > 0) {
            filePaths.add(ConstantObjects.OUTPUTFOLDER + prevPart + ConstantObjects.FILEFORMAT);
        }

        filePaths.add(ConstantObjects.WAVESETSOURCE + fileName + ConstantObjects.FILEFORMAT);
        filePaths.add(ConstantObjects.WAVESETSOURCE + ConstantObjects.SPACEFILE + ConstantObjects.FILEFORMAT);

        outputName = new WAVConcatenator(filePaths).getOutputName();
    }

    public String getOutputName() {
        return outputName;
    }
}
