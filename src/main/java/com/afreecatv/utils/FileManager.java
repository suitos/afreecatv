package main.java.com.afreecatv.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    public static String getDownloadFolderPath() {
        String tempfolder = System.getProperty("os.name").toLowerCase().contains("mac") ? "/test-temp/" : "\\test-temp\\";
        Path temppath = Paths.get(System.getProperty("user.dir"), tempfolder);

        if (!Files.exists(temppath)) {
            try {
                Files.createDirectories(temppath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return temppath.toString();
    }

    //확장자 미포함
    public String getDownloadFilePath(String filename) {
        String downloadFolderPath = getDownloadFolderPath();
        Path tempfilepath = Paths.get(downloadFolderPath, filename);
        return tempfilepath.toString();
    }

}
