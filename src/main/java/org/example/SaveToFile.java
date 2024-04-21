package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Save to file class is used to save results of simulation to file (.txt)
 */
public class SaveToFile {
    /**
     * Save results to file. Creates new file (if file already exists - overwrites) and save results of the simulation. Results are Strings in the ArrayList
     */
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

}