package org.example;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class ReadMeLauncher {
    ReadMeLauncher(){
        String filePath = "src/main/resources/manual/index.html";

        if (!Desktop.isDesktopSupported()) {
            System.out.println("Desktop is not supported");
            return;
        }

        Desktop desktop = Desktop.getDesktop();
        File htmlFile = new File(filePath);

        try {
            if (htmlFile.exists()) {
                desktop.browse(htmlFile.toURI());
            } else {
                System.out.println("File does not exist");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}