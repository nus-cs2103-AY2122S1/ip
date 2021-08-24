package duke;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import duke.tasks.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

// deals with loading tasks from the file and saving tasks in the file
public class Storage {
     private String FILE_PATH;
     private ArrayList<String> textArr = new ArrayList<String>(); // array of saved lines in the new txt file
     private File file;
     private ArrayList<Task> taskList = new ArrayList<Task>();
     private String linebreak = "~~~~~~~~~~";

    public Storage(String filePath) throws IOException {
        this.FILE_PATH = filePath;
        file = new File(FILE_PATH);
    }

    public ArrayList<Task> load() throws IOException {

        // If the file exists, print out the previous data
        // Two cases: Data exists and data does not exist (file is empty)
        if (file.exists()) {

            if (file.length() == 0) {
                System.out.println("Oops! Looks like you don't have anything saved :(");

            } else {
                System.out.println("Here's your progress:");
                Scanner file_sc = new Scanner(file);

                while (file_sc.hasNext()) {
                    String nextLine = file_sc.nextLine();
//                    textFileString.add(nextLine);

                    // Regex to handle each line of the file / each command
                    String[] txtFileCmd = nextLine.split("@");
                    String taskType = txtFileCmd[0];
                    boolean taskState = Integer.parseInt(txtFileCmd[1]) != 0;  // 0 for not done, 1 for done
                    String taskInfo = txtFileCmd[2];
                    textArr.add(nextLine);

                    switch (taskType) {
                    case "T": {
                        taskList.add(new Todo(taskInfo, taskState));
                        break;

                    }
                    case "D": {
                        String dateBy = txtFileCmd[3];
                        taskList.add(new Deadline(taskInfo, LocalDate.parse(dateBy), taskState));
                        break;

                    }
                    case "E": {
                        String eventDetails = txtFileCmd[3];
                        taskList.add(new Event(taskInfo, eventDetails, taskState));
                        break;
                    }

                    }
                }
                file_sc.close();

                for (int count = 0; count < taskList.size(); count++) {
                    System.out.println((count + 1) + ". " + taskList.get(count).toString());
                }
            }

            // If the file does not exist, create a new file
        } else {
            System.out.println("Welcome new user!");
            System.out.println("Let me create a save file for you :)");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
            }
            file.createNewFile();
        }
        System.out.println(linebreak);

        return taskList;
    }

    private void update() throws FileNotFoundException {
        String txt = "";
        for (String s : textArr) {
            txt += s + "\n";
        }
        PrintWriter pw = new PrintWriter(file);
        pw.append(txt);
        pw.flush();
    }

    public void updateDone(int ref, String s) throws IOException {
        textArr.set(ref, s);
        update();
    }

    public void addTask(String s) throws IOException {
        textArr.add(s);
        update();
    }

    public void removeTask(int ref) throws IOException{
        textArr.remove(ref);
        update();
    }


}
