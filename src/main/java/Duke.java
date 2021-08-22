
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String LINE = "----------------------------------------------------";
    private static final String COMMANDS = LINE + "-----------------------------\n" +
            "|\tPlease enter one of the following commands:                                  |\n" +
            "|\t1. todo <description> (eg. todo paint)                                       |\n" +
            "|\t2. deadline <description> /by <date> (e.g deadline submit hw /by 2020-01-01) |\n" +
            "|\t3. event <description> /at <date> (e.g event party /at 2020-01-01)           |\n" +
            "|\t4. list - see list of tasks added                                            |\n" +
            "|\t5. delete <task number> (e.g delete 1) - delete a task from list             |\n" +
            "|\t6. done <task number> (e.g done 1) - mark a task in list as done             |\n" +
            "|\t7. bye - exit duke                                                           |\n" +
            LINE + "-----------------------------\n";
    private ArrayList<Task> taskList;
    private Scanner scanner = new Scanner(System.in);
    private String filePath;

    public Duke(String filePath) {
        this.filePath = filePath;
        this.taskList = loadTaskList(filePath);
    }

    public ArrayList<Task> loadTaskList(String filePath) {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return taskList;
            }
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();
            while (line != null) {
                String[] input = line.split("\\|");
                String taskType = input[0];
                String description = input[2];
                boolean isTaskDone = input[1].equals("1");
                switch (taskType) {
                    case "T":
                        loadTodoToList(taskList, description, isTaskDone);
                        break;
                    case "E":
                        String at = input[3];
                        loadEventToList(taskList, description, at, isTaskDone);
                        break;
                    case "D":
                        String by = input[3];
                        loadDeadlineToList(taskList, description, by, isTaskDone);
                        break;
                }
                line = br.readLine();
            }
        } catch (DukeException | IOException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }

    public void loadTodoToList(ArrayList<Task> taskList, String description,
                               boolean isTaskDone) throws DukeException {
        Todo todo = new Todo(description);
        if (isTaskDone) {
            todo.setDone();
        }
        taskList.add(todo);
    }

    public void loadEventToList(ArrayList<Task> taskList, String description, String at,
                                boolean isTaskDone) throws DukeException {
        Event event = new Event(description, at);
        if (isTaskDone) {
            event.setDone();
        }
        taskList.add(event);
    }

    public void loadDeadlineToList(ArrayList<Task> taskList, String description, String by,
                                   boolean isTaskDone) throws DukeException {
        Deadline deadline = new Deadline(description, by);
        if (isTaskDone) {
            deadline.setDone();
        }
        taskList.add(deadline);
    }

    public void saveTaskList() {
        File file = new File(filePath);
        try {
            FileWriter writer = new FileWriter(file);
            for (Task tasks : taskList) {
                writer.write(tasks.saveTaskFormat() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addTaskToList(Task task) {
        taskList.add(task);
        System.out.printf("added: " + task.toString()
                + "\nNow you have %s tasks in your list\n" , taskList.size());
    }

    public void printTasksInList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("\t%s."+ taskList.get(i).toString() + "%n", i + 1);
        }
    }

    public String[] getDescriptionAndTime(String str,
                                          String taskType) throws DukeException {
        String[] next = scanner.nextLine().split(str);
        if (next.length < 2) {
            if (next[0].trim().length() == 0) {
                throw new NoDescriptionAndTimeException(taskType);
            } else {
                throw new NoTimeException(taskType);
            }
        }
        return next;
    }

    public void addDeadlineToList() throws DukeException {
        List<String> formatStrings = Arrays.asList("MM-dd-yyyy", "MMM d yyyy",
                "MM/dd/yyyy", "yyyy/");
        String[] taskTimeD = getDescriptionAndTime("/by","deadline");
        String description = taskTimeD[0].trim();
        String by = taskTimeD[1].trim();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
            LocalDate date = LocalDate.parse(by);
            by = date.format(formatter);
            Deadline deadline = new Deadline(description, by);
            addTaskToList(deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    public void addEventToList() throws DukeException {
        String[] taskTimeE = getDescriptionAndTime("/at", "event");
        String description = taskTimeE[0].trim();
        String at = taskTimeE[1].trim();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
            LocalDate date = LocalDate.parse(at);
            at = date.format(formatter);
            Event event = new Event(description, at);
            addTaskToList(event);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    public void addTodoToList() throws DukeException {
        Todo todo = new Todo(scanner.nextLine().trim());
        addTaskToList(todo);
    }

    public void deleteFromList() throws DukeException {
        int deleteNumber = Integer.parseInt(scanner.next()) - 1;
        if (deleteNumber < 0 || deleteNumber > taskList.size()-1) {
            throw new InvalidTaskDeletionException();
        }
        Task task = taskList.get(deleteNumber);
        taskList.remove(deleteNumber);
        System.out.printf("Noted. I've removed this task:\n" + task.toString()
                + "\nNow you have %s tasks in your list\n" , taskList.size());
    }

    public void setTaskAsDone() throws DukeException {
        int doneNumber = Integer.parseInt(scanner.next()) - 1;
        if (doneNumber < 0 || doneNumber > taskList.size()-1) {
            throw new InvalidTaskDoneException();
        }
        Task task = taskList.get(doneNumber);
        task.setDone();
        System.out.println("Nice! I've marked this task as done:\n"
                + task.toString());
    }

    public void dukeAction() {
        String inp = scanner.next();
        while(!inp.equals("bye")) {
            try {
                System.out.println(LINE);
                switch (inp) {
                    case "list":
                        printTasksInList();
                        break;
                    case "done":
                        setTaskAsDone();
                        break;
                    case "todo":
                        addTodoToList();
                        break;
                    case "deadline":
                        addDeadlineToList();
                        break;
                    case "event":
                        addEventToList();
                        break;
                    case "delete":
                        deleteFromList();
                        break;
                    default:
                        scanner.nextLine();
                        throw new NoCommandException();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println(LINE);
                inp = scanner.next();
            }
        }
        saveTaskList();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n\n"
                + COMMANDS);
        new Duke("duke.txt").dukeAction();
    }
}