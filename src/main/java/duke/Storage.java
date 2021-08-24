package duke;

import duke.task.Task;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class Storage {
    private Scanner s;
    private final String FILEPATH;
    private File savefile;

    public Storage (String filepath) {
        this.FILEPATH = filepath;
        this.initialiseSaveFile();
    }

    public void initialiseSaveFile() {
        this.savefile = new File(this.FILEPATH);

        try {
            if (savefile.getParentFile().mkdir()) {
                System.out.println("File path created.");
                if (savefile.createNewFile()) {
                    System.out.println("File created.");
                }
            }
        } catch (IOException e) {
            throw new DukeException("File could not be found!");
        }
    }

    public ArrayList<String> produceReadableString() {
        try {
            s = new Scanner(savefile);
            ArrayList<String> output = new ArrayList<>();

            while (s.hasNextLine()) {
                String thisLine = s.nextLine();
                String[] segments = thisLine.split(" \\| | ");
                for (int i = 0; i < segments.length; i++) {
                    output.add(segments[i]);
                }
            }
            s.close();

            return output;
        } catch (IOException e) {
            throw new DukeException("File could not be found!");
        }

    }

    public void updateSavefile(TaskList tl) {
        try {
            FileWriter myWriter = new FileWriter("saves/saves.txt");
            System.out.println("Updating Save File");

            for (int i = 0; i < tl.numberOfTasks(); i++) {
                Task thisTask = tl.getTask(i);
                myWriter.write(thisTask.getReadableString());
            }

            myWriter.close();

        } catch (Exception e) {
            throw new DukeException("File could not be found!");
        }
    }
}
