package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SaveToFile {
    public SaveToFile(){

    }
    public void saveResultsToFile(ArrayList<String> dataToSave){
        try {
            String fileNameTemplate = "WynikiSymulacji.txt";
            PrintWriter writer = new PrintWriter(fileNameTemplate);
            for (String data : dataToSave) {
                writer.write(data);
                writer.write('\n');
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public void setFileName(String fileName){
//        this.fileNameTemplate = fileName;
//    }
//    public String getFileName(){
//        return this.fileNameTemplate;
//    }

//    private boolean checkIfFileExists(String fileName){
//        File file = new File(fileName);
//        return file.exists();
//    }
}