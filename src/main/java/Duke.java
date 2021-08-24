import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void greet(){
        print("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public static void print(String message) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }

    public static String buildMessage(String message) {
        String line = "____________________________________________________________";
        return line + "\n" + message + "\n" + line;
    }

    public static void print(ArrayList<Task> ls) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < ls.size() ; i++) {
            System.out.println((i + 1) + "." + ls.get(i));
        }
        System.out.println(line);
    }

    public static boolean isDone(String input, int listLength) {
//        String[] splited = phrase.split(" ");
////        System.out.println(splited);
//        if (splited.length != 2) {
//            return false;
//        } else {
//            return splited[0].equals("done") && splited[1].matches("\\d+") && Integer.valueOf(splited[1]) <= listLength;
//        }
        return input.startsWith("done ");
    }

    public static void printAdd(Task toAdd, int size) {
        String message = "Got it. I've added this task:\n" + "  " + toAdd + "\nNow you have " + size + " tasks in the list.";
        print(message);
    }

    public static String[] splitInput(String input, String type) throws DukeException{
        if (type.equals("deadline") || type.equals("event")) {
            String[] str = input.split("/");
            if (str.length == 1) {
                if (type.equals("deadline")) {
                    throw new IncompleteDeadlineException();
                } else {
                    throw new IncompleteEventException();
                }
            } else {
                String[] first = str[0].split(" ");
                String[] second = str[1].split(" ");
                String description = "";
                String deadline = "";
                for (int i = 1; i < first.length; i++) {
                    description += first[i];
                    if (i != first.length - 1) {
                        description += " ";
                    }
                }
                for (int i = 1; i < second.length; i++) {
                    deadline += second[i];
                    if (i != second.length - 1) {
                        deadline += " ";
                    }
                }
                return new String[]{description, deadline};
            }
        } else {
            String[] str = input.split(" ");
            if (str.length == 1) {
                throw new IncompleteToDoException();
            } else {
                String description = "";
                for (int i = 1; i < str.length; i++) {
                    description += str[i];
                    if (i != str.length - 1) {
                        description += " ";
                    }
                }
                return new String[]{description};
            }
        }
    }

    public static boolean isRemove(String input){
        return input.startsWith("remove ");
    }

    public static void saveFile(ArrayList<Task> arr) {
        try {
            FileWriter fw = new FileWriter("history.txt");
            String textToAdd = "";
            for (int i = 0; i < arr.size(); i++) {
                if (i == 0) {
                    textToAdd += arr.get(i).convertToString();
                } else {
                    textToAdd += "\n" + arr.get(i).convertToString();
                }
            }
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }
    }
//    public static void saveFile(ArrayList<Task> arr) {
//        File readFile = new File("history.txt");
//        try {
//            if (readFile.exists()) {
//                FileWriter fw = new FileWriter("history.txt");
//                String textToAdd = "";
//                for (int i = 0; i < arr.size(); i++) {
//                    if (i == 0) {
//                        textToAdd += arr.get(i).convertToString();
//                    } else {
//                        textToAdd += "\n" + arr.get(i).convertToString();
//                    }
//                }
//                fw.write(textToAdd);
//                fw.close();
//            } else {
//                FileWriter fw = new FileWriter("history.txt");
//                String textToAdd = "";
//                for (int i = 0; i < arr.size(); i++) {
//                    if (i == 0) {
//                        textToAdd += arr.get(i).convertToString();
//                    } else {
//                        textToAdd += "\n" + arr.get(i).convertToString();
//                    }
//                }
//                fw.write(textToAdd);
//                fw.close();
//            }
//        } catch (IOException e) {
//            System.out.println("Something went wrong " + e.getMessage());
//        }
//    }

    public static ArrayList<Task> readFile() {
        File file = new File("history.txt");
        try {
            Scanner s = new Scanner(file);
            ArrayList<Task> output = new ArrayList<>();
            while (s.hasNext()) {;
                String task = s.nextLine();
                String[] splitTask = task.split(" ");
                if (splitTask.length == 3) {
                    // it is todotask
                    Task toAdd = new ToDo(splitTask[2]);
                    if (splitTask[1].equals("1")) {
                        toAdd.markAsDone();
                    }
                    output.add(toAdd);
                } else {
                    // can be event or deadline
                    if (splitTask[0].equals("E")) {
                        // event
                        Task toAdd = new Event(splitTask[2], splitTask[3]);
                        if (splitTask[1].equals("1")) {
                            toAdd.markAsDone();
                        }
                        output.add(toAdd);
                    } else {
                        // deadline
                        Task toAdd = new Deadline(splitTask[2], splitTask[3]);
                        if (splitTask[1].equals("1")) {
                            toAdd.markAsDone();
                        }
                        output.add(toAdd);
                    }
                }
            }
            return output;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        ArrayList<Task> tasks = readFile();


        // Read file here and insert into task list
        // ...
        Scanner sc = new Scanner(System.in);
        greet();
        String next = sc.nextLine();
        while (!next.equals("bye")) {
            try {
                if (next.equals("list")) {
                    print(tasks);
                } else if (isDone(next, tasks.size())) {
                    String[] splited = next.split(" ");
                    if (splited.length < 2 || !splited[1].matches("\\d+") || Integer.valueOf(splited[1]) > tasks.size()) {
                        throw new DukeException(buildMessage("Please key in valid number to mark as done."));
                    } else {
                        int index = Integer.valueOf(splited[1]) - 1;
                        tasks.get(index).markAsDone();
                        String message = "Nice! I've marked this task as done:\n" + "  " + tasks.get(index);
                        print(message);
                    }
                } else if (isRemove(next)) {
                    String[] str = next.split(" ");
                    if (str.length < 2 || !str[1].matches("\\d+") || Integer.valueOf(str[1]) > tasks.size()) {
                        throw new DukeException(buildMessage("Please key in valid number to remove."));
                    } else {
                        Task toRemove = tasks.get(Integer.valueOf(str[1]) - 1);
                        tasks.remove(toRemove);
                        String message = "Noted. I've removed this task:\n" + toRemove + "\nNow you have "
                                + tasks.size() + " tasks in the list";
                        print(message);
                    }
                } else {
                    String[] splited = next.split(" ");
                    if (splited[0].equals("todo") || splited[0].equals("deadline") || splited[0].equals("event")) {
                        String[] str = splitInput(next, splited[0]);
                        if (splited[0].equals("todo")) {
                            ToDo add = new ToDo(str[0]);
                            tasks.add(add);
                            printAdd(add, tasks.size());
                        } else if (splited[0].equals("deadline")) {
                            Deadline add = new Deadline(str[0], str[1]);
                            tasks.add(add);
                            printAdd(add, tasks.size());
                        } else {
                            Event add = new Event(str[0], str[1]);
                            tasks.add(add);
                            printAdd(add, tasks.size());
                        }
                    } else {
                        throw new InvalidCommandException();
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                next = sc.nextLine();
            }
        }

        print("Bye. Hope to see you again soon!");
        // close file and save the contents of the task list into an array
        saveFile(tasks);
        sc.close();
    }
}
