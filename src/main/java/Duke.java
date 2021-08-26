import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;


public class Duke {


    private static final String line = "____________________________________________________________";
    private static ArrayList<Task> tasks = new ArrayList<>();

    private static void Greet() {
        System.out.println("Hello I'm Duke\n" +
                            line +
                            "\nWhat can I do for you?\n" +
                            line);
    }

    private static void printTasks() {
        int index = 0;

        System.out.println("Here are tasks in your list:");
        for(Task t: tasks) {
            index++;
            System.out.println(index + "." + t.toString());
        }
    }

    private static void makeTaskDone (int index) throws DukeException {
        if (index <= 0 || index > tasks.size()) {
            throw new DukeException ("I'm sorry the index is invalid :-(");
        } else {
            System.out.println("Nice! I've marked this task as done:");
            tasks.get(index - 1).markDone();
            System.out.println(tasks.get(index - 1).toString());
        }
    }

    private static void delete(int index) throws DukeException{
        if (index <= 0 || index > tasks.size()) {
            throw new DukeException("I'm sorry the index is invalid :-(");
        } else {
            System.out.println("Noted. I've removed this task:");
            System.out.println(tasks.get(index - 1).toString());
            tasks.remove(index - 1);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }

    private static void DukeOperation(String input) throws DukeException {
        // operation is always the first word of user input.
        String[] allWords = input.split(" ");
        String operation = allWords[0];

        switch (operation) {
            case "bye": {
                System.out.println(" Bye. Hope to see you again soon!");
                break;
            }
            case "list": {
                printTasks();
                break;
            }
            case "done": {
                try {
                    int index = Integer.valueOf(allWords[1]);
                    makeTaskDone(index);
                } catch (DukeException e) {
                    System.out.println(e.toString());
                }
                break;
            }
            case "todo": {
                if (allWords.length == 1) {
                  throw new DukeException("The description of todo cannot be empty");
                } else {
                    String description = input.substring(input.indexOf(allWords[1]));
                    Todo todo = new Todo(description);
                    System.out.println("added: " + todo.toString());
                    tasks.add(todo);
                    System.out.println("Now you have " + tasks.size() + " tasks in your list");
                }
                break;
            }
            case "event": {
                if (allWords.length == 1) {
                    throw new DukeException("The description of event cannot be empty");
                } else {
                    if (input.contains("/at")) {
                        String description = input.substring(input.indexOf(allWords[1]), input.indexOf("/at") - 1);
                        String at = input.substring(input.indexOf("/at") + 4);
                        Event event = new Event(description, at);
                        System.out.println("added: " + event.toString());
                        tasks.add(event);
                        System.out.println("Now you have " + tasks.size() + " tasks in your list");
                    } else {
                        throw new DukeException("The format of event is wrong");
                    }
                }
                break;
            }
            case "deadline": {
                if (allWords.length == 1) {
                    throw new DukeException("The description of deadline cannot be empty");
                } else {
                    if (input.contains("/by")) {
                        String description = input.substring(input.indexOf(allWords[1]), input.indexOf("/by") - 1);
                        String at = input.substring(input.indexOf("/by") + 4);
                        Deadline deadline = new Deadline(description, at);
                        System.out.println("added: " + deadline.toString());
                        tasks.add(deadline);
                        System.out.println("Now you have " + tasks.size() + " tasks in your list");
                    } else {
                        throw new DukeException("The format of deadline is wrong");
                    }
                }
                break;
            }
            case "delete": {
                try {
                    int index = Integer.valueOf(allWords[1]);
                    delete(index);
                } catch (DukeException e) {
                    System.out.println(e.toString());
                }
                break;
            }
            default: {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    private static void ProcessFileData(String Data) {
        char taskType = Data.charAt(0);
        char status = Data.charAt(4);
        Task task;
        String description;

        if (taskType == 'T') {
            description = Data.substring(8);
            task = new Todo(description);
        } else {
            String time = Data.substring(8, Data.lastIndexOf("|") - 1);
            description = Data.substring(Data.lastIndexOf("|") + 2);
            if (taskType == 'E') {
                task = new Event(description, time);
            } else {
                task = new Deadline(description, time);
            }
        }

        if (status == '1') {
            task.markDone();
        }
        tasks.add(task);
    }

    private static void updateData(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);

            for (Task task: tasks) {
                fw.write( task.formatForFile()+ "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void readData(String filePath) {
        try {
            File f = new File(filePath);
            Scanner fileInput = new Scanner(f);
            while (fileInput.hasNext()) {
                String Data = fileInput.nextLine();
                ProcessFileData(Data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error!! No File found in the given root.");
        }

    }

    private static void userInput() {
        String input = "";
        Scanner keyboardInput = new Scanner(System.in);

        Greet();
        do {
            try {
                input = keyboardInput.nextLine();

                System.out.println(line);
                DukeOperation(input);
                System.out.println(line);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        } while (!input.equals("bye"));
    }

    public static void main(String[] args) {
        String location = "data/duke.txt";

        readData(location);
        userInput();
        updateData(location);

    }

}
