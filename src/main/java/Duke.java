import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    private static StorageList sl = new StorageList();
    private static File file;

    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am\n" + logo);


        try{
            file = new File("data/duketest.txt");
            if (!file.exists()) {
                throw new FileNotFoundException();
            } else {
                System.out.println("I have received your file! Added tasks!\n");
                Scanner sc = new Scanner(file);
                readFile(sc);
                result();
            }

        } catch (FileNotFoundException e) {
            System.out.println("I am unable to find your file. " +
                    "Check that your 'duketest' file exists," +
                    " or that your 'data' folder exists.");
        }

    }

    private static void readFile(Scanner sc) {
        while (sc.hasNext()) {
            String input = sc.nextLine();

            char type = input.charAt(0);
            int doneState = Integer.parseInt(input.substring(4, 5));
            if (type == 'T') {
                String taskDesc = input.substring(8);
                ToDos todo = new ToDos(taskDesc);
                if (doneState == 1) {
                    todo.markAsDone();
                }
                sl.add(todo);
            } else {
                int thirdBarIdx = input.indexOf('|', 7);
                String taskDesc = input.substring(8, thirdBarIdx-1);
                String taskTime = input.substring(thirdBarIdx + 2);
                if(type == 'D'){
                    Deadlines dl = new Deadlines(taskDesc, taskTime);
                    if(doneState == 1){
                        dl.markAsDone();
                    }
                    sl.add(dl);
                } else if (type == 'E') {
                    Events event = new Events(taskDesc, taskTime);
                    if(doneState == 1){
                        event.markAsDone();
                    }
                    sl.add(event);
                }
            }
        }
    }


    private static void result() throws DukeException {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();

            try {
                if (input.length() >= 4 && input.substring(0, 4).equals("done")) {
                    marking(input);
                } else if (input.length() >= 4 && input.substring(0, 4).equals("todo")) {
                    if(input.length() == 4) {
                        throw new DukeException("   ☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    ToDos todo = new ToDos(input.substring(5));
                    sl.add(todo);
                    linesToPrint(todo.toString(), sl.size());
                } else if (input.length() >= 8 && input.substring(0, 8).equals("deadline")) {
                    if (input.length() == 8) {
                        throw new DukeException("   ☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String by = input.substring(input.indexOf("/") + 4);
                    Deadlines dl = new Deadlines(input.substring(9, input.indexOf("/")), by);
                    sl.add(dl);
                    linesToPrint(dl.toString(), sl.size());
                } else if (input.length() >= 5 && input.substring(0, 5).equals("event")) {
                    if (input.length() == 5) {
                        throw new DukeException("   ☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    String at = input.substring(input.indexOf("/") + 4);
                    Events event = new Events(input.substring(6,  input.indexOf("/")), at);
                    sl.add(event);
                    linesToPrint(event.toString(), sl.size());
                } else if (input.length() >= 6 && input.substring(0, 6).equals("delete")) {
                    if (input.length() == 6) {
                        throw new DukeException("   ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    int idx = Integer.parseInt(input.substring(7)) - 1;
                    delete(idx);
                } else {
                    Task task = new Task(input);
                    String desc = task.getDescription();
                    switch (desc) {
                    case "bye":
                        System.out.println("    Bye. Hope to see you again soon!");
                        return;
                    case "list":
                        sl.display();
                        break;
                    default:
                        throw new DukeException("   ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
                save();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Something went wrong!");
            }
        }

    }

    private static void save() throws IOException {
        FileWriter fw = new FileWriter("data/duketest.txt");
        String textToAdd = "";
        for (int i = 0; i < sl.size(); i++){
            Task task = sl.get(i);
            String taskType = "";
            String status = task.isDone() ? "1 " : "0 ";
            String taskDesc = task.getDescription();
            String taskTime = "";
            if (task instanceof ToDos) {
                taskType = "T ";
                textToAdd += taskType + "| " + status + "| " + taskDesc + "\n";
            } else {
                if (task instanceof Deadlines) {
                    taskType = "D ";
                    taskTime = ((Deadlines) task).getBy();
                } else if (task instanceof Events) {
                    taskType = "E ";
                    taskTime = ((Events) task).getAt();
                }
                textToAdd += taskType + "| " + status + "| " + taskDesc
                        + " | " + taskTime + "\n";
            }

        }

        fw.write(textToAdd);
        fw.close();
    }

    private static void delete(int idx) {
        String desc = sl.get(idx).getDescription();
        sl.delete(idx);
        System.out.println("    Noted. I've removed this task:");
        System.out.println("        " + desc);
        System.out.println("    Now you have " + sl.size() + " tasks in the list.");
    }

    public static void linesToPrint(String task, int size) {
        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + task);
        System.out.println("    Now you have " + size + " tasks in the list.");
    }

    public static void marking(String input) throws DukeException {
        if (input.length() >= 6) {
            if (input.substring(5).matches("[0-9]+")) {
                int taskNum = Integer.parseInt(input.substring(5)) - 1;
                if (taskNum < sl.size() && taskNum >= 0) {
                    sl.get(taskNum).markAsDone();
                    System.out.println("    Nice! I've marked this task as done:");
                    sl.get(taskNum).displayTask();
                } else {
                    throw new DukeException("   ☹ OOPS!!! index out of bounds");
                }
            } else {
                throw new DukeException("   ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } else {
            throw new DukeException("   ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

        }
    }
}
