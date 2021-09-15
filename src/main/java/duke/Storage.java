package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Encapsulates a class which interacts with the output file to save data after.
 */
public class Storage {
    private File txt;

    /**
     * Loads file to save task list data.
     *
     * @throws Exception IO or Security Exception thrown by createNewFile.
     */
    public void loadFile() throws Exception {
        Path p = Paths.get("data");
        if (!Files.exists(p)) {
            Files.createDirectories(p);
        }
        File f = Paths.get("data", "duke.txt").toFile();
        f.createNewFile();
        txt = f;
    }

    /**
     * Reads the output file and loads any saved tasks.
     *
     * @param t the TaskList object to save the list of tasks.
     */
    public void readFromFile(TaskList t) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(txt));
            String curLine;
            while ((curLine = br.readLine()) != null) {
                String[] parts = curLine.split(" ", 3);
                char taskType = parts[0].charAt(1);
                boolean isCompleted = parts[1].charAt(1) == 'X';

                ArrayList<String> argumentParts;
                String description;
                LocalDateTime dateTime;
                ArrayList<String> tags = new ArrayList<>();
                switch(taskType) {
                case 'T':
                    if (parts[2].contains(" (Tags: ")) {
                        argumentParts = Parser.parseFileOutputArguments(parts[2], "Tags");
                        description = argumentParts.get(0);
                        tags = new ArrayList<>(Arrays.asList(argumentParts.get(1).split(" ")));
                        t.addToList(new Todo(description, isCompleted, tags));
                    } else {
                        t.addToList(new Todo(parts[2], isCompleted, tags));
                    }
                    break;
                case 'D':
                    if (parts[2].contains(" (Tags: ")) {
                        argumentParts = Parser.parseFileOutputArguments(parts[2], "by", "Tags");
                        description = argumentParts.get(0);
                        dateTime = LocalDateTime.parse(argumentParts.get(1));
                        tags = new ArrayList<>(Arrays.asList(argumentParts.get(2).split(" ")));
                    } else {
                        argumentParts = Parser.parseFileOutputArguments(parts[2], "by");
                        description = argumentParts.get(0);
                        dateTime = LocalDateTime.parse(argumentParts.get(1));
                    }
                    t.addToList(new Deadline(description, isCompleted, dateTime, tags));
                    break;
                case 'E':
                    if (parts[2].contains(" (Tags: ")) {
                        argumentParts = Parser.parseFileOutputArguments(parts[2], "at", "Tags");
                        description = argumentParts.get(0);
                        dateTime = LocalDateTime.parse(argumentParts.get(1));
                        tags = new ArrayList<>(Arrays.asList(argumentParts.get(2).split(" ")));
                    } else {
                        argumentParts = Parser.parseFileOutputArguments(parts[2], "at");
                        description = argumentParts.get(0);
                        dateTime = LocalDateTime.parse(argumentParts.get(1));
                    }
                    t.addToList(new Event(description, isCompleted, dateTime, tags));
                    break;
                default:
                }
            }
        } catch (Exception e) {
            System.out.println("Read error: " + e.getMessage());
        }
    }

    /**
     * Writes the task list stored to an output file to persist its data.
     *
     * @param t The TaskList object that stores the task list to be written to the file.
     * @throws Exception IO Exception from BufferedWriter.
     */
    public void writeToFile(TaskList t) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(txt));
        String curLine;
        for (int i = 0; i < t.size(); i++) {
            curLine = t.getTask(i).toFileString();
            bw.write(curLine + "\n");
        }
        bw.close();
    }

}
