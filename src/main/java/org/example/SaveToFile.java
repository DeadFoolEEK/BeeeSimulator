package org.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SaveToFile {
    private String fileNameTemplate = "WynikiSymulacji.txt";
    public SaveToFile(){

    }
    public void saveResultsToFile(ArrayList<String> dataToSave){
        try {
            PrintWriter writer = new PrintWriter(fileNameTemplate);
            for (String datum : dataToSave) {
                writer.write(datum);
                writer.write('\n');
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setFileName(String fileName){
        this.fileNameTemplate = fileName;
    }
    public String getFileName(){
        return this.fileNameTemplate;
    }
    private boolean checkIfFileExists(String fileName){
        File file = new File(fileName);
        return file.exists();
    }
}