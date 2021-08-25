import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private static TodoList todolist = new TodoList();

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        introduceDuke();
    }

    public static void formatMessages(String message) {
        String output = "     --------------------------------------\n"
                + "      " + message + "\n"
                + "\n     --------------------------------------";
        System.out.println(output);
    }

    public static void introduceDuke() {
        Scanner sc = new Scanner(System.in);
        try {
            loadPastTasks();
        } catch (IOException e) {
            System.out.println("Error writing to tasks.txt file");
        }
        String introduction = "Hello, I am Ah Seng, the foodcourt uncle. Come chitchat with me.";
        formatMessages(introduction);
        respondTo(sc);
    }

    public static void terminateProgramme() {
        String endingMessage = "Ah ok bye. Next time treat uncle kopi ok?";
        formatMessages(endingMessage);
    }

    public static void respondTo(Scanner sc) {
        String input = sc.nextLine();
        if(input.equals("bye")) {
            terminateProgramme();
        } else if (input.equals("list")) {
            printTasks();
            respondTo(sc);
        } else if (containsDone(input)) {
            completeTask(input);
            respondTo(sc);
        } else if (containsTask(input)) {
            insertTask(input);
            respondTo(sc);
        } else if (containsDelete(input)) {
            deleteTask(input);
            respondTo(sc);
        } else {
            try {
                handleOtherResponses(input);
            } catch (NonsenseException e) {
                formatMessages(e.getMessage());
                respondTo(sc);
            }
        }
    }

    public static void handleOtherResponses(String input) throws NonsenseException {
        throw new NonsenseException(input + " ? What talk you...");
    }

    public static void insertTask(String input) {
        try {
            formatMessages(todolist.insertTask(input));
        } catch (NoDescriptionException e) {
            formatMessages(e.getMessage());
        }
    }

    public static void printTasks() {
            formatMessages(todolist.getList());
    }

    public static void completeTask(String input) {
        try {
            String first = input.split(" ")[0];
            String second = input.split(" ")[1];
            int value = Integer.parseInt(second);
            formatMessages(todolist.completeTask(value));
        } catch (IndexNotInListException e) {
            formatMessages(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            formatMessages("Aiyo, you say done a task but you never tell me which one leh.");
        }
    }

    public static void deleteTask(String input) {
        try {
            String first = input.split(" ")[0];
            String second = input.split(" ")[1];
            int value = Integer.parseInt(second);
            formatMessages(todolist.deleteTask(value));
        } catch (IndexNotInListException e) {
            formatMessages(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            formatMessages("Eh, I don't have this task leh");
        }
    }

    // These Methods return boolean, to check if they are 1) Done ... 2) Is a task 3) Delete...

    public static boolean containsDone(String input) {
        String first = input.split(" ")[0];
        return first.equalsIgnoreCase("done");
    }

    public static boolean containsTask(String input) {
        String first = input.split(" ")[0];
        return first.equalsIgnoreCase("todo")
                || first.equalsIgnoreCase("event") || first.equalsIgnoreCase("deadline");
    }

    public static boolean containsDelete(String input) {
        String first = input.split(" ")[0];
        return first.equalsIgnoreCase("delete");
    }

    public static void loadPastTasks() throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("./tasks.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.equals("Tasks:")) {
                    Task task = convertToTask(line);
                    todolist.insertTask(task);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            File file = new File("./tasks.txt");
            file.createNewFile();
        }
    }

    public static Task convertToTask(String output) {
        char type = output.charAt(1);
        char done = output.charAt(4);
        String taskAndDate = output.substring(7);

        Task task;
        if (type == 'T') {
            task = new Todo(taskAndDate);
        } else if (type == 'D') {
            // Deadlines
            if (taskAndDate.contains("(by: ")) {
                String name = taskAndDate.split(" \\(by: ")[0];
                String date = taskAndDate.split(" \\(by: ")[1];

                // Chop of last ")"
                date = date.substring(0, date.length()-1);
                task = new Deadline(name, " " + date);
            } else {
                task = new Deadline(taskAndDate, "");
            }
        } else {
            // Events
            if (taskAndDate.contains("(at: ")) {
                String name = taskAndDate.split(" \\(at: ")[0];
                String date = taskAndDate.split(" \\(at: ")[1];

                // Chop of last ")"
                date = date.substring(0, date.length()-1);
                task = new Event(name, " " + date);
            } else {
                task = new Event(taskAndDate, "");
            }
        }
        task.type = type;
        task.done = done == 'X';
        return task;
    }
}
