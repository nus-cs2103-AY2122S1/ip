import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Duke {
    private final static String DATA_FILEPATH = "./data/data.txt";
    public static Task createTask(String input) throws DukeException {
        try {
            String[] inputList = input.split(" ");
            if (inputList[0].equals("todo")){
                String todoDescription = input.replaceFirst(Pattern.quote("todo"),"").trim();
                if (todoDescription.equals("")) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                return new Todo(todoDescription);
            } else if (inputList[0].equals("deadline")) {
                String[] withoutAction = input.replaceFirst(Pattern.quote("deadline"),"").split("/by", 2);
                return new Deadline(withoutAction[0].trim(), withoutAction[1].trim());
            } else {
                String[] withoutAction = input.replaceFirst(Pattern.quote("event"),"").split("/at", 2);
                return new Event(withoutAction[0].trim(), withoutAction[1].trim());
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("date should be in YYYY-MM-DD format");
        }
    }

    public static ArrayList<Task> loadTasksFromDataFile() throws IOException, DukeException, DateTimeParseException {
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File f = new File(DATA_FILEPATH);
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            String l = s.nextLine();
            System.out.println(l);
            String[] TaskEntry = l.split("\\|");
            System.out.println(TaskEntry);
            System.out.println(TaskEntry[2]);
            switch(TaskEntry[0]) {
                case "T":
                    tasks.add(new Todo(TaskEntry[2]));
                    break;
                case "D":
                    tasks.add(new Deadline(TaskEntry[2], TaskEntry[3]));
                    break;
                case "E":
                    tasks.add(new Event(TaskEntry[2], TaskEntry[3]));
                    break;
                default:
                    throw new DukeException("Invalid Task Type stored in Data File");
            }
            if (TaskEntry[1].equals("X")) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        }
        return tasks;
    }

    public static void updateDataFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(DATA_FILEPATH);
        for (Task currTask: tasks) {
            fw.write(currTask.toStringData() + "\n");
        }
        fw.close();
    }
    public static void main(String[] args) {
        ArrayList<Task> tasks;
        try {
            tasks = loadTasksFromDataFile();
        } catch (IOException | DukeException | DateTimeParseException e) {
            System.out.println("Error loading data file:" + e.getMessage());
            return;
        }

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            String[] inputList = input.split(" ");
            String action = inputList[0];
            try {
                if (action.equals("bye")) {
                    break;
                } else if (action.equals("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i).toString());
                    }
                } else if (action.equals("delete") || action.equals("done")) {
                    if (inputList.length != 2) {
                        throw new DukeException("Please provide the target task index!");
                    }
                    int taskIndex = Integer.parseInt(inputList[1]);
                    if (tasks.size() < taskIndex || 0 >= taskIndex) {
                        throw new DukeException("Invalid task index provided!");
                    }
                    if (action.equals("done")) {
                        Task currTask = tasks.get(taskIndex - 1);
                        currTask.markAsDone();
                        System.out.println("Nice! I've marked this task as done:\n" + currTask);
                    } else {
                        Task removedTask = tasks.remove(taskIndex - 1);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(removedTask);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    }
                    try {
                        updateDataFile(tasks);
                    } catch (IOException e) {
                        System.out.println("Error writing data file:" + e.getMessage());
                        return;
                    }
                } else if (action.equals("todo") || action.equals("deadline") || action.equals("event")) {
                    Task newTask = createTask(input);
                    tasks.add(newTask);
                    try {
                        updateDataFile(tasks);
                    } catch (IOException e) {
                        System.out.println("Error writing data file:" + e.getMessage());
                        return;
                    }
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (action.equals("occurring")) {
                    LocalDate queryDate = LocalDate.parse(inputList[1]);
                    System.out.println("Tasks occurring on "
                            + queryDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
                    int count = 1;
                    for (Task task: tasks) {
                        if ((task instanceof Deadline && ((Deadline) task).by.equals(queryDate))
                                || (task instanceof Event  && ((Event) task).at.equals(queryDate))) {
                            System.out.println(count + ". " + task);
                            count++;
                        }
                    }
                    if (count == 1) {
                        System.out.println("No tasks.");
                    }
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println("☹ OOPS!!! " + e.getMessage());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
