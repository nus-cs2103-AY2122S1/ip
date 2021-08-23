package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
        makeFile();
        load();
    }

    public void makeFile() {
        File textFile = new File("data/duke.txt");
        Path path = Paths.get("data");
        if (!Files.isDirectory(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                System.err.println("Failed to create directory!" + e.getMessage());
            }
        }
        if (!textFile.exists()) {
            try {
                textFile.createNewFile();
            } catch (IOException e) {
                System.err.println("Failed to create file!" + e.getMessage());
            }
        }
    }

    public String[] load() {
        File textFile = new File("data/duke.txt");
        Integer counter = 0;
        String[] temp = new String[50];
        try {
            Scanner s = new Scanner(textFile);
            while (s.hasNext()) {
                String command = s.nextLine();
                temp[counter] = command;
                counter++;
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist" + e.getMessage());
        }
        return temp;
    }

    public void appendToFile(String filePath, String textToAppend) throws IOException {
        File f = new File(filePath);
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        if (f.length() == 0) {
            fw.write(textToAppend);
        } else {
            fw.write(System.lineSeparator() + textToAppend);
        }
        fw.close();
    }

    public void editFileContentsForCompletion(int taskNum) {
        File temp = new File("data/temp.txt");
        if (!temp.exists()) {
            try {
                Files.copy(Paths.get("data/duke.txt"), Paths.get("data/temp.txt"), REPLACE_EXISTING);
                new FileWriter("data/duke.txt", false).close();
                Scanner s = new Scanner(new File("data/temp.txt"));
                int count = 1;
                while (s.hasNextLine()) {
                    String command = s.nextLine();
                    if (count == taskNum) {
                        String head = command.substring(0, 4);
                        String tail = command.substring(5);
                        appendToFile("data/duke.txt", head + "1" + tail);
                        count++;
                    } else {
                        appendToFile("data/duke.txt", command);
                        count++;
                    }
                }
                s.close();
                Files.delete(Paths.get("data/temp.txt"));
            } catch (IOException e) {
                System.err.println("Failed to create file!" + e.getMessage());
            }
        }
    }

    public void editFileContentsForDeletion(int taskNum) {
        File temp = new File("data/temp.txt");
        if (!temp.exists()) {
            try {
                Files.copy(Paths.get("data/duke.txt"), Paths.get("data/temp.txt"), REPLACE_EXISTING);
                new FileWriter("data/duke.txt", false).close();
                Scanner s = new Scanner(new File("data/temp.txt"));
                int count = 1;
                while (s.hasNextLine()) {
                    String command = s.nextLine();
                    if (count == taskNum) {
                        count++;
                    } else {
                        appendToFile("data/duke.txt", command);
                        count++;
                    }
                }
                s.close();
                Files.delete(Paths.get("data/temp.txt"));
            } catch (IOException e) {
                System.err.println("Failed to create file!" + e.getMessage());
            }
        }
    }
}
