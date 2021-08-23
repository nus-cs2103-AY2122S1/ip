package bern.functionalities;

import bern.Command;
import bern.exception.BernException;
import bern.model.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public void writeIntoFile(ArrayList<Task> arListTask) {
        try {
            new Storage().reinitialiseFile();
            FileWriter fw = new FileWriter("savedList.txt");
            fw.write(new Ui().getReply(Command.LIST, "", arListTask));
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (BernException e) {
            System.out.println(e.getMessage());
        }
    }

    public void initialiseArListTask(ArrayList<Task> arListTask) {
        File file = new File("savedList.txt");
        if (!file.exists()) {
            return;
        } else {
            try {
                Scanner s = new Scanner(file);
                while (s.hasNext()) {
                    String taskStr = s.nextLine();
                    taskStr = taskStr.substring(taskStr.indexOf("["));
                    Task temp = new Parser().taskFromString(taskStr);
                    new TaskList().addTask(temp, arListTask);
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void reinitialiseFile() {
        try {
            File file = new File("savedList.txt");
            file.delete();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
