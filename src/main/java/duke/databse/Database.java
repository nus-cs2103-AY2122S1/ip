package duke.databse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.core.UI;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.RecurringTask;
import duke.task.Task;
import duke.task.Todo;


/**
 * A class to CRUD file that stores task
 */
public class Database {

    private File file;
    private UI ui;
    private String filepath;

    /**
     * Constructor for database
     * @param filePath
     */
    public Database(String filePath) {
        try {
            this.filepath = filePath;

            file = new File(filePath);
            ui = new UI();

            if (!file.getParentFile().exists()) {
                boolean hasDir = file.getParentFile().mkdir();

            }

            if (!file.exists()) {
                file.createNewFile();
            }

        } catch (IOException e) {
            System.out.println("An error occurred in creating or opening file.");
        }
    }


    /**
     * read data from file
     * @return
     */
    public ArrayList<Task> getData() {
        ArrayList<Task> objectsList = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                objectsList.add(parseData(data));
            }
        } catch (IOException e) {
            System.out.println(ui.wrongMessage);
        }
        return objectsList;
    }

    /**
     * parse event task
     * @param data
     * @return
     */
    public Task parseEvent(String data) {
        String[] s = data.split(" ");

        boolean isDone;
        if (s[s.length - 1].equals("false")) {
            isDone = false;
        } else {
            isDone = true;
        }
        String taskNameEvent = "";
        String taskTimeEvent = "";
        boolean timePartEvent = false;
        for (int i = 1; i < s.length - 1; i++) {
            if (s[i].startsWith("/")) {
                timePartEvent = true;
                taskTimeEvent = s[i].substring(1);
            } else if (timePartEvent) {
                taskTimeEvent += " " + s[i];
            } else {
                if (s[i + 1].startsWith("/")) {
                    taskNameEvent += s[i];
                } else {
                    taskNameEvent += s[i] + " ";
                }

            }
        }
        return new Event(taskNameEvent, isDone, taskTimeEvent);
    }

    /**
     * parse deadline task
     * @param data
     * @return
     */
    public Task parseDeadline(String data) {
        String[] s = data.split(" ");

        boolean isDone;
        if (s[s.length - 1].equals("false")) {
            isDone = false;
        } else {
            isDone = true;
        }
        String taskNameDdl = "";
        String taskTimeDdl = "";
        boolean timePartDdl = false;
        for (int i = 1; i < s.length - 1; i++) {
            if (s[i].startsWith("/")) {
                timePartDdl = true;
                taskTimeDdl = s[i].substring(1);
            } else if (timePartDdl) {
                taskTimeDdl += " " + s[i];
            } else {
                if (s[i + 1].startsWith("/")) {
                    taskNameDdl += s[i];
                } else {
                    taskNameDdl += s[i] + " ";
                }
            }
        }
        return new Deadline(taskNameDdl, isDone, taskTimeDdl);
    }

    /**
     * parse recurring task
     * @param data
     * @return
     */
    public Task parseRecur(String data) {
        String[] s = data.split(" ");

        boolean isDone;
        if (s[s.length - 1].equals("false")) {
            isDone = false;
        } else {
            isDone = true;
        }
        String taskNameRecur = "";
        String taskTimeRecur = "";
        int counter = 0;
        boolean timePartRecur = false;
        for (int i = 1; i < s.length - 1; i++) {
            if (s[i].startsWith("/") && !timePartRecur) {
                timePartRecur = true;
                taskTimeRecur = s[i].substring(1);
            } else if (s[i].startsWith("/") && timePartRecur) {
                counter = Integer.parseInt(s[i].substring(1));
            } else if (timePartRecur) {
                taskTimeRecur += " " + s[i];
            } else {
                if (s[i + 1].startsWith("/")) {
                    taskNameRecur += s[i];
                } else {
                    taskNameRecur += s[i] + " ";
                }
            }
        }
        return new RecurringTask(taskNameRecur, isDone, taskTimeRecur, counter);
    }

    /**
     * parse data from string to task
     * @param data a string that contains a task information
     * @return
     */
    public Task parseData(String data) {
        String[] s = data.split(" ");

        boolean isDone;
        if (s[s.length - 1].equals("false")) {
            isDone = false;
        } else {
            isDone = true;
        }
        switch (s[0]) {
        case "T":
            Todo todo = new Todo(s[1], isDone);
            assert s[0].equals("T") : "should be a todo event but it is not";
            return todo;
        case "E":
            return parseEvent(data);
        case "D":
            return parseDeadline(data);
        case "R":
            return parseRecur(data);
        default:
            break;
        }
        return null;
    }

    /**
     * write data to file
     * @param todo
     */
    public void writeToDatabase(Task todo) {
        try {
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(filepath, true));
            out.write(todo.toString());
            out.close();
        } catch (IOException e) {
            System.out.println("exception occurred" + e);
        }
    }

    /**
     * update data to file
     * @param task
     * @param index
     */
    public void updateData(Task task, int index) {
        String data = "";
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                if (index == 1) {
                    myReader.nextLine();
                    data += task.toString();
                } else {
                    data += myReader.nextLine() + "\n";
                }
                index--;
            }
            myReader.close();

        } catch (IOException e) {
            System.out.println(ui.wrongMessage);
        }

        try {
            FileWriter fw = new FileWriter(file);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            System.out.println(ui.wrongMessage);
        }
    }

    /**
     * delete data from file
     * @param index
     */
    public void deleteData(int index) {
        String data = "";
        try {
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
                if (index == 1) {
                    myReader.nextLine();

                } else {
                    data += myReader.nextLine() + "\n";
                }
                index--;
            }
            myReader.close();

        } catch (IOException e) {
            System.out.println(ui.wrongMessage);
        }
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            System.out.println(ui.wrongMessage);
        }
    }

    /**
     * run database
     * @param args
     */
    public static void main(String[] args) {
        Database d = new Database("todoList.txt");
        d.getData();
    }
}
