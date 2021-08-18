package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The is the FileUtils class to do file operations.
 *
 * @author  HU JIAJUN
 * @version %I%, %G%
 * @since   1.0
 */

public class FileUtils {

    /**
     * Load list of contents from file.
     *
     * @return list of contents
     */
    public static List<String> loadFile(String dirName, String fileName) {
        List<String> contents = new ArrayList<>();
        Path rootPath = Paths.get("");
        Path dirPath = Paths.get(rootPath.toAbsolutePath().toString(), dirName);
        if (!Files.exists(dirPath)) {
            return contents;
        }
        Path filePath = Paths.get(dirPath.toAbsolutePath().toString(), fileName);
        File file = new File(String.valueOf(filePath));
        if(!file.exists() || file.isDirectory()) {
            return contents;
        }
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String row = sc.nextLine();
                contents.add(row);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return contents;
    }

    /**
     * Save list of contents to file.
     *
     * @return whether the contents are saved to file successfully
     */
    public static boolean saveFile(String dirName, String fileName, List<String> contents) {
        Path rootPath = Paths.get("");
        Path dirPath = Paths.get(rootPath.toAbsolutePath().toString(), dirName);
        if (!Files.exists(dirPath)) {
            File dirFile = new File(String.valueOf(dirPath));
            dirFile.mkdir();
        }
        Path filePath = Paths.get(dirPath.toAbsolutePath().toString(), fileName);
        File file = new File(String.valueOf(filePath));
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Create file error: " + e.getMessage());
            return false;
        }
        try {
            FileWriter writer = new FileWriter(filePath.toAbsolutePath().toString());
            for (String content : contents) {
                writer.write(content + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Write file error: " + e.getMessage());
            return false;
        }
        return true;
    }
}
