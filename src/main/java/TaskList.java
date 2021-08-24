import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void initialiseTaskList() throws IOException {
        String path = new File("").getAbsolutePath() + "/data/bob.txt";
        File f = new File(path);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String curr = s.nextLine();
            Task newTask;
            boolean isComplete;
            if (curr.matches("\\[T](.*)")) {
                String[] splitCurr = curr.split(" \\Q[\\E.\\Q]\\E ", 2);
                newTask = new Todo(splitCurr[1]);
                isComplete = curr.contains("[X]");
            } else if (curr.matches("\\[D](.*)")) {
                String[] splitCurr = curr.split(" \\Q[\\E.\\Q]\\E ", 2);
                String[] splitRight = splitCurr[1].split(" \\(by: ", 2);
                String by = splitRight[1].substring(0, splitRight[1].length() - 1);
                newTask = new Deadline(splitRight[0], by);
                isComplete = curr.contains("[X]");
            } else {
                String[] splitCurr = curr.split(" \\Q[\\E.\\Q]\\E ", 2);
                String[] splitRight = splitCurr[1].split(" \\(at: ", 2);
                String at = splitRight[1].substring(0, splitRight[1].length() - 1);
                newTask = new Event(splitRight[0], at);
                isComplete = curr.contains("[X]");
            }
            this.taskList.add(newTask);
            if (isComplete) {
                newTask.markCompleted();
            }
        }
    }

    public String addTask(Task task) throws IOException {
        this.taskList.add(task);
        updateTextFile();
        return "Okay okay I've added the task:\n" + task.printTask() + "\n"
                + "Yay " + this.taskList.size() + " tasks!\n";
    }

    public String getList() {
        String result = "Here's your tasks! Wow I'm so helpful!\n";
        for (int index = 0; index < this.taskList.size(); index++) {
            result = result + (index + 1) + "." + this.taskList.get(index).printTask() + "\n";
        }
        return result;
    }

    public String markIndexCompleted(int index) throws IOException {
        Task selectedTask = this.taskList.get(index);
        selectedTask.markCompleted();
        updateTextFile();
        return "Wow you finally did something productive!\n" + selectedTask.printTask() + "\n";
    }

    public String deleteIndex(int index) throws IOException {
        Task selectedTask = this.taskList.get(index);
        this.taskList.remove(index);
        updateTextFile();
        return "Okay task yeeted away :D\n" + selectedTask.printTask() + "\n"
                + "Yay " + this.taskList.size() + " tasks!\n";
    }

    public String noOfTasks() {
        return Integer.toString(this.taskList.size());
    }

    private void updateTextFile() throws IOException {
        String path = new File("").getAbsolutePath() + "/data/bob.txt";
        FileWriter writer = new FileWriter(path);
        writer.write("");
        writer.close();
        for(int i = 0; i < taskList.size(); i++) {
            appendToFile(path, taskList.get(i).printTask());
        }
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend + "\n");
        fw.close();
    }
}
