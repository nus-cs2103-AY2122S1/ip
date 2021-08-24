import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHelper {

    /** Starts up the file reader, creates file if it does not exist. */
    public static ArrayList<String> startUpFile(String filePath) throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            Files.createFile(Paths.get(filePath));
        }

        printFileContents(filePath);
        ArrayList<String> toReturn = new ArrayList<>();
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String unformattedTask = s.nextLine();
            toReturn.add(unformattedTask);
        }
        return toReturn;
    }

    /** Read the file contents. Print out. */
    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    /** Write to file. Rewrite everytime there is a done / delete command */
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /** Append to file. Append when adding a new item*/
    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(System.lineSeparator() + textToAppend);
        fw.close();
    }

    /** Test */
    public static void run() {
        String filePath = "data/list.txt";
        File f = new File(filePath);
        System.out.println("full path:" + f.getAbsolutePath());
        System.out.println("file exists?:" + f.exists());
        System.out.println("is directory?:" + f.isDirectory());

        try {
            FileHelper.startUpFile(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

    public static void main(String[] args) {
    }
}
