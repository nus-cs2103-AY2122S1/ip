import java.io.*;
import java.util.stream.Collectors;

public class DataStorageHandler {


    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private int numberOfLines = 0;
    private int maxNumberOfLines = 10;
    private boolean hasStorageTextFile = false;

    public DataStorageHandler(String fileName) {
        //Check if file exist. If not create a new file
        if (!hasStorageTextFile) {
            //Create a storage text file with the same name
            File file = new File(fileName);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Create the writer and buffered writer
        try {
            FileWriter writer = new FileWriter(fileName,true);
            this.bufferedWriter = new BufferedWriter(writer);
            this.bufferedReader = new BufferedReader(new FileReader(fileName));
            this.hasStorageTextFile = true;
        } catch (IOException e) {
            e.printStackTrace();
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
    }

    public void printAllLines() {
        String all_lines =  bufferedReader.lines().collect(Collectors.joining());
        System.out.println(all_lines);
    }

    /**
     * Clears the entire history of the log
     */
    //I see what youre doing yes. Why are you clearing history?
    public static void clear_history() {
        //Code to clear history
    }

    public void stopWriting() {
        try {
            this.bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
