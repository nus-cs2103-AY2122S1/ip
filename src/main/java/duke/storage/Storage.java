package duke.storage;

import duke.task.TaskList;
import duke.ui.UserInterface;
import duke.parser.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final File file;
    private final String filePath;
    private final static UserInterface userInterface = new UserInterface();

    public Storage(String path) {
        filePath = path;
        file = new File(filePath);
    }

    public void printTaskFile()  {
        try {
            Scanner sc = new Scanner(file);
            boolean isEmpty = !sc.hasNext();
            int index = 1;

            while (sc.hasNext()) {
                System.out.println(index + ". " + sc.nextLine());
                index++;
            }

            if (isEmpty) userInterface.emptyListWarning();

        } catch (FileNotFoundException e) {
            userInterface.fileNotFoundWarning();
        }
    }

    public void saveTask(TaskList task) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, true);
        fileWriter.write(task.toString() + "\n");
        fileWriter.close();
    }

    public void overwriteList(ArrayList<TaskList> taskArrayList) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (TaskList t : taskArrayList) {
                fileWriter.write(t.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            userInterface.generalErrorWarning(e.getMessage());
        }
    }

    public void loadFile() {
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                Parser.parseFromFile(line);
            }
        } catch (FileNotFoundException e) {
            userInterface.fileNotFoundWarning();
        }
    }
}
