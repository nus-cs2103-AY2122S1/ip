import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Task;
import tasks.ToDoList;

public class Bubbles {
    private static ToDoList toDoList;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String introduction = "You should do what you want to do!\n"
                + "Hello I'm Bubbles from the Powerpuff Girls, what are you up to?";

        System.out.println("Hello from\n" + logo);
        formatting(introduction);

        String filePath = "data/bubbles.txt";

        File bubbles = loadFile(filePath);

        echoTask(toDoList, bubbles);
    }

    private static File loadFile(String filePath) {
        File bubbles = new File(filePath);

        try {
            toDoList = readFile(bubbles);
        } catch (FileNotFoundException e) {
            createFile(filePath);
            toDoList = new ToDoList();
        }

        return bubbles;
    }

    private static void createFile(String filePath) {
        String[] arr = filePath.split("/");

        File file = new File(filePath);
        File dir = new File(arr[0]);
        dir.mkdir();
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("There is an error while creating the database!");
        }
    }

    public static ToDoList readFile(File bubbles) throws FileNotFoundException {
        Scanner sc = new Scanner(bubbles);
        ToDoList toDoList = new ToDoList();

        while (sc.hasNext()) {
            String task = sc.nextLine();
            Object[] inputs = formatTask(task);

            toDoList.addTask((String) inputs[0], (String) inputs[1], (Boolean) inputs[2]);
        }

        return toDoList;
    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                String farewell = "Bye-bye! Hope to see you again soon!";

                int n = formatting(farewell);
                break;
            } else {
                int n = formatting(input);
            }
        }

        sc.close();
    }

    public static void echoTask(ToDoList toDoList, File bubbles) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                ArrayList<Task> tasks = toDoList.getTasks();

                writeTasks(tasks, bubbles);

                String farewell = "Bye-bye! Hope to see you again soon!";

                formatting(farewell);
                break;
            } else {
                toDoList.taskListener(input);
            }
        }
    }

    public static int formatting(String str) {
        String separator = "-----------------------------------------------------------------";

        System.out.println(separator + "\n"
                + str + "\n"
                + separator);

       return 0;
    }

    public static void writeTasks(ArrayList<Task> tasks, File bubbles) {
        try {
            FileWriter fw = new FileWriter(bubbles, true);

            for (Task t : tasks) {
                String task = t.format();

                fw.write(task);
                fw.write(System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static Object[] formatTask(String task) {
        String input = "";
        String[] arr = task.split(" \\| ");
        int n = arr.length;

        String taskType = arr[0];

        String completed = arr[1];
        boolean isDone = false;

        if (completed.equals("0")) {
            isDone = true;
        }

        input += arr[2];

        if (n > 2) {
            String[] date = arr[2].split(" ", 2);

            input = input + "/" + date[0] + " " + date[1];
        }

        Object[] inputs = {taskType, input, isDone};
        return inputs;
    }
}
