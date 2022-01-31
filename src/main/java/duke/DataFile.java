package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class DataFile {

    private File dataFile;

    public DataFile(String filePath) {
        try {
            this.dataFile = new File(filePath);
            this.dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating/loading data file");
            this.dataFile = null;
        }
    }

    public ArrayList<Task> parseTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(dataFile);
            String fileLine;
            while (sc.hasNext()) {
                fileLine = sc.nextLine();
                tasks.add(Parser.parseFileLine(fileLine));
            }
        } catch (IOException e) {
            System.out.println("Cannot parse file");
        }

        return tasks;
    }

    public void saveToDisk(String data) {
        try {
            FileWriter writer = new FileWriter(this.dataFile);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public String toString() {
        try {
            Scanner reader = new Scanner(this.dataFile);
            String contents = "";
            while (reader.hasNext()) {
                contents += reader.nextLine();
            }
            return contents;
        } catch (IOException e) {
            return "";
        }
    }

}
