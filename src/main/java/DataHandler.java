import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public interface DataHandler {
    /**
     * Reads the text content of a given file.
     * @param filename The name of the file to read from
     * @return An ArrayList of the lines read from a given file.
     */
    public static ArrayList<String> readLinesFromFile(String filename) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                lines.add(data);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("No save data found.");
        }
        return lines;
    }

    /**
     * Creates a file with the given filename.
     * If the file already exists, it will overwrite the existing file with a new file.
     * @param filename The filename to be created.
     */
    public static void overwriteNewFile(String filename) {
        try {
            FileWriter fileWriter = new FileWriter(filename, false);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Writes a given String to a given file, without overwriting existing content.
     * @param content The content to be written to the file
     * @param filename The file that is to be written to
     */
    public static void writeToFile(String content, String filename) {
        try {
            FileWriter fileWriter = new FileWriter(filename, true);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
