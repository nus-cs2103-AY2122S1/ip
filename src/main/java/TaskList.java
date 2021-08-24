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

    public void initialiseTaskList() {
        try {
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
                    String deadline = splitRight[1].substring(0, splitRight[1].length() - 1);
                    newTask = new Deadline(splitRight[0], formatDate(deadline));
                    isComplete = curr.contains("[X]");
                } else {
                    String[] splitCurr = curr.split(" \\Q[\\E.\\Q]\\E ", 2);
                    String[] splitRight = splitCurr[1].split(" \\(at: ", 2);
                    String timing = splitRight[1].substring(0, splitRight[1].length() - 1);
                    newTask = new Event(splitRight[0], formatDate(timing));
                    isComplete = curr.contains("[X]");
                }
                this.taskList.add(newTask);
                if (isComplete) {
                    newTask.markCompleted();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String formatDate(String date) {
        String day;
        String month;
        String year;
        String[] splitDate = date.split(" ", 3);
        day = splitDate[1];
        switch (splitDate[0]) {
            case "Jan":
                month = "01";
            case "Feb":
                month = "02";
            case "Mar":
                month = "03";
            case "Apr":
                month = "04";
            case "May":
                month = "05";
            case "Jun":
                month = "06";
            case "Jul":
                month = "07";
            case "Aug":
                month = "08";
            case "Sep":
                month = "09";
            case "Oct":
                month = "10";
            case "Nov":
                month = "11";
            default:
                month = "12";
        }
        year = splitDate[2];
        return year + "-" + month + "-" + day;
    }

    public String addTask(Task task) {
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

    public String markIndexCompleted(int index) {
        Task selectedTask = this.taskList.get(index);
        selectedTask.markCompleted();
        updateTextFile();
        return "Wow you finally did something productive!\n" + selectedTask.printTask() + "\n";
    }

    public String deleteIndex(int index) {
        Task selectedTask = this.taskList.get(index);
        this.taskList.remove(index);
        updateTextFile();
        return "Okay task yeeted away :D\n" + selectedTask.printTask() + "\n"
                + "Yay " + this.taskList.size() + " tasks!\n";
    }

    public String noOfTasks() {
        return Integer.toString(this.taskList.size());
    }

    private void updateTextFile() {
        try {
            String path = new File("").getAbsolutePath() + "/data/bob.txt";
            FileWriter writer = new FileWriter(path);
            writer.write("");
            writer.close();
            for (int i = 0; i < taskList.size(); i++) {
                appendToFile(path, taskList.get(i).printTask());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend + "\n");
        fw.close();
    }
}
