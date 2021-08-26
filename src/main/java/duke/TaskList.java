package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    private final ArrayList<Task> TASKLIST = new ArrayList<>();

    public TaskList(Path path) throws FileNotFoundException {
        File f = new File(String.valueOf(path));
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            // Read and add the task into the list
            String input = s.nextLine();
            String[] arrOfInputs = input.split("\\|");
            updateList(arrOfInputs);
        }
    }

    public TaskList() {

    }

    private void updateList(String[] arrOfInputs) {
        //Check for T, D, E and update in the list
        if (arrOfInputs[0].equals("T")) {
            String t = arrOfInputs[2];
            ToDo td = new ToDo(t);
            this.TASKLIST.add(td);
        } else if (arrOfInputs[0].equals("D")) {
            String t = arrOfInputs[2] + " /by " + arrOfInputs[3];
            Deadline d = new Deadline(t);
            this.TASKLIST.add(d);
        } else if (arrOfInputs[0].equals("E")) {
            String t = arrOfInputs[2] + " /at " + arrOfInputs[3];
            Event e = new Event(t);
            this.TASKLIST.add(e);
        }

        int currListLength = this.TASKLIST.size();
        //Check if its completed or not (0,1) and mark accordingly
        if (arrOfInputs[1].equals("1")) {
            this.TASKLIST.get(currListLength - 1).markAsDone();
        }
    }

    public void addNewTask(Task t) {
        this.TASKLIST.add(t);
    }

    public int getSize() {
        return TASKLIST.size();
    }

    public ArrayList<Task> getTaskList() {
        return TASKLIST;
    }
    public Task getTask(int i) {
        return TASKLIST.get(i);
    }

    public void deleteGivenTask(int i) {
        TASKLIST.remove(i-1);
    }

    public static void updateMemory(String filePath, TaskList taskList) throws DukeException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Task t: taskList.getTaskList()) {
                System.out.println(t.getTaskInfo());
                bufferedWriter.write(t.getTaskInfo() + System.lineSeparator());
            }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new DukeException("Could not write to file!");
        }
    }
}