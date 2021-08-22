import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<>(100);
    private static int listIndex = 0;
    private static final String FILE_PATH = "data/mango.txt";

    public static void greet() {
        System.out.println("Hello! I'm Mango\nWhat can I do for you?");
    }

    public static void loadData() throws IOException {
        File f = new File(FILE_PATH);
        if (!f.exists()) {
            File directory = new File(f.getParent());
            if (!directory.exists()) {
                directory.mkdirs();
            }
            f.createNewFile();
        } else {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String[] task = s.nextLine().split("-", 4);
                Task toAdd;
                switch (task[0]) {
                case "D":
                    toAdd = new Deadline(task[2], task[3], task[1]);
                    break;
                case "E":
                    toAdd = new Event(task[2], task[3], task[1]);
                    break;
                case "T":
                    toAdd = new Todo(task[2], task[1]);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + task[0]);
                }

                list.add(toAdd);
                listIndex++;
            }
        }
    }

    public static void saveData() throws IOException {
        String tempFilePath = "temp/mango.txt";
        File f = new File(tempFilePath);
        File directory = new File(f.getParent());
        if (!directory.exists()) {
            directory.mkdirs();
        }
        f.createNewFile();

        FileWriter fw = new FileWriter(tempFilePath);
        for (Task t : list) {
            fw.write(t.save());
        }
        fw.close();

        Files.copy(Paths.get(tempFilePath), Paths.get(FILE_PATH), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(Paths.get(tempFilePath));
    }

    public static void echo(String str) {
        System.out.println(str);
    }

    public static void add(String str) throws DukeException {
        Task newTask = null;
        String[] arr1 = str.split(" ", 2);
        String type = arr1[0];
        String desc;
        String date;
        String[] arr2;

        switch (type) {
            case "todo":
                if (Todo.isValid(arr1)) {
                    desc = arr1[1];
                    newTask = new Todo(desc);
                }
                break;
            case "deadline":
                if (Deadline.isValid(arr1)) {
                    arr2 = arr1[1].split(" /", 2);
                    desc = arr2[0];
                    date = arr2[1].substring(3);
                    newTask = new Deadline(desc, date);
                }
                break;
            case "event":
                if (Event.isValid(arr1)) {
                    arr2 = arr1[1].split(" /", 2);
                    desc = arr2[0];
                    date = arr2[1].substring(3);
                    newTask = new Event(desc, date);
                }
                break;
            default:
                throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        list.add(listIndex, newTask);
        String word;
        if (listIndex == 0) {
            word = "task";
        } else {
            word = "tasks";
        }

        System.out.println("Got it. I've added this task:");
        System.out.printf("   [%s][%s] %s\n", newTask.getType(), newTask.getStatusIcon(), newTask.getDescription());
        System.out.printf("Now you have %d %s in the list.%n", listIndex + 1, word);

        listIndex++;
    }

    public static void complete(int completedTask) {
        Task currentTask = list.get(completedTask - 1);
        if (!currentTask.isDone) {
            currentTask.markDone();
        }

        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("[%s][X] %s\n", currentTask.getType(), currentTask.getDescription());
    }

    public static void delete(int deleteTask) {
        Task delTask = list.remove(deleteTask - 1);
        listIndex--;
        System.out.println("Noted. I've removed this task:");
        System.out.printf("[%s][%s] %s\n", delTask.getType(), delTask.getStatusIcon(), delTask.getDescription());

        String word;
        if (listIndex == 1) {
            word = "task";
        } else {
            word = "tasks";
        }

        System.out.printf("Now you have %d %s in the list.%n", listIndex, word);
    }

    public static void getList() {
        int i = 0;
        System.out.println("Here are the tasks in your list:");
        while (i < listIndex) {
            int num = i+1;
            Task curr = list.get(i);
            System.out.printf("%d. [%s][%s] %s\n", num, curr.getType(), curr.getStatusIcon(), curr.getDescription());
            i++;
        }
    }

    public static void exit() {
        try {
            Duke.saveData();
        } catch (IOException e) {
            System.out.println("Error encountered when saving data: " + e.getMessage());
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        String logo = " __  __    ___    _  _     ___     ___\n"
                + "|  \\/  |  /   \\  | \\| |   / __|   / _ \\\n"
                + "| |\\/| |  | - |  | .` |  | (_ |  | (_) |\n"
                + "|_|__|_|  |_|_|  |_|\\_|   \\___|   \\___/\n"
                + "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|\n"
                + "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\n";


        System.out.println("Hello from\n" + logo);

        Duke.greet();
        try {
            Duke.loadData();
        } catch (IOException e) {
            System.out.println("Error encountered when loading data: " + e.getMessage());
        }

        Scanner sc = new Scanner(System.in);
        String str;

        while (!(str = sc.nextLine()).equals("bye")) {
            try {
                if (str.equals("list")) {
                    Duke.getList();
                } else if (str.contains("done")) {
                    Duke.complete(Character.getNumericValue(str.charAt(5)));
                } else if (str.contains("delete")) {
                    Duke.delete(Character.getNumericValue(str.charAt(7)));
                } else {
                        Duke.add(str);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        Duke.exit();

    }
}
