import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Persistent storage refers to the database used to store all the information regarding tasks. Allows for handling of
 * .txt files and manages all of them
 */
public class PersistentStorageHandler {

    private File file;
    private String fileName;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private int numberOfLines = 0;
    private int maxNumberOfLines = 10;
    private boolean hasStorageTextFile = false;
    private ArrayList<String> all_lines = new ArrayList<>();

    public PersistentStorageHandler(String fileName) {
        //Name the file
        this.fileName = fileName;
        this.file = new File(fileName);

        // Create the writer and buffered writer
        try {
            FileWriter writer = new FileWriter(fileName,true);
            this.bufferedWriter = new BufferedWriter(writer);
            this.bufferedReader = new BufferedReader(new FileReader(fileName));
            this.hasStorageTextFile = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Check if file exist. If not create a new file
        if (!hasStorageTextFile) {
            System.out.println("Oooooooooooooo. Fresh meat. Welcome my dinn- I mean friend");
            //Create a storage text file with the same name
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Ahhhhhh. It seems you have an old scroll. Let me read the contents......");
            //Add all lines of the file to all_lines variable
            try {
                List<String> list = Files.readAllLines(new File(fileName).toPath(), Charset.defaultCharset());
                all_lines.addAll(list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void write(String text)  {
        //Count the number of lines in the string
        String[] tempStringArray = text.split("\r\n|\r|\n");
        int linesInAddedText = tempStringArray.length;

        // Writes the content to the file
        try {
            this.bufferedWriter.write(text + "\n");
            this.bufferedWriter.flush();
            //this.bufferedWriter.close();
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }

        //Add the number of lines
        numberOfLines += linesInAddedText;

        //Accumulate all lines
        all_lines.add(text);
    }

    public void printAllLines() {
        for (String line: all_lines) {
            System.out.println(line);
        }
    }

    /**
     * Clears the entire history of the log
     */
    //I see what youre doing yes. Why are you clearing history?
    public void clear_history() {
        //Code to clear history
        this.all_lines.clear();
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stopWriting() {
        try {
            this.bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<String> getAll_lines() {
        return this.all_lines;
    }
}
