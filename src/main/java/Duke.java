import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;


public class Duke {

    private ArrayList<Task> userInput;
    private boolean isBye;
    private Scanner scanner;
    private File taskFile;

    private final static String DIVIDER = "  ---------------------------------------------";
    private final static String INDENT_1 = "    ";
    private final static String INDENT_2 = "      ";

    public enum Commands {
        LIST, BYE, DELETE, DONE,
        TODO, EVENT, DEADLINE;
    }


    public Duke(File taskFile) {
        scanner = new Scanner(System.in);
        userInput = new ArrayList<>();
        isBye = false;
        this.taskFile = taskFile;
    }

    public void setUserInput() {
        try {
            Scanner s = new Scanner(taskFile);
            while (s.hasNextLine()) {
                String taskString = s.nextLine();
                System.out.println(taskString);
                String[] splitString = taskString.split("  ");

                switch(splitString[0]) {

                    case "T" :
                        userInput.add(new Todo(splitString[2]));
                        break;
                    case "D":
                        LocalDate date = LocalDate.parse(splitString[3]);
                        userInput.add(new Deadline(splitString[2], date));
                        break;
                    case "E":
                        LocalDateTime dateTime = LocalDateTime.parse(splitString[3]);
                        userInput.add(new Event(splitString[2], dateTime));
                        break;
                }

                if(splitString.length > 2 && splitString[1].equals("Y")) {
                    userInput.get(userInput.size()-1).markAsDone();
                }


            }
        } catch (FileNotFoundException e) {
            
        }
    }


    public void getInput() {
        while (!isBye && scanner.hasNext()) {
            String input = scanner.nextLine().trim();
            String[] splitInput = input.split(" +");
            try {
                Commands word = Commands.valueOf(splitInput[0].toUpperCase());

                switch (word) {

                    case BYE:
                        printMessage(INDENT_1 + "Bye! Hope to see you again soon :)");
                        isBye = true;
                        scanner.close();
                        break;

                    case LIST:
                        System.out.println(DIVIDER);
                        System.out.println(INDENT_1 + "Here are the tasks in your list:");
                        int point = 0;
                        while (point < userInput.size()) {
                            Task temp = userInput.get(point);
                            System.out.println(INDENT_2 + (point + 1) + ". " + temp.toString());
                            point++;
                        }
                        printNumberOfTasks();
                        break;

                    case DONE:
                        inputDone(input, splitInput);
                        break;

                    case DELETE:
                        deleteTask(input, splitInput);
                        break;

                    case TODO:

                    case DEADLINE:

                    case EVENT:
                        checkTask(input);
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(DIVIDER);
                System.out.println(INDENT_1 + "☹ OH NO I'm sorry, but I don't " +
                        "know what that means :-(");
                System.out.println(DIVIDER);
            } catch (DukeException e) {
                printMessage(e.getMessage());
            }
        }
    }

    public void printMessage(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }

    public void deleteTask(String input, String[] splitInput) throws DukeException {
        if (splitInput.length == 2) {
            int taskNum = Integer.valueOf(splitInput[1]);
            if (taskNum <= 0 || taskNum > userInput.size()) {
                throw new DukeException(INDENT_1 + "☹ OOPS!!! There is no " +
                        "corresponding task to be deleted.");
            } else {
                System.out.println(DIVIDER);
                System.out.println(INDENT_1 + "Sure! I've removed this task:\n" + INDENT_2 +
                        userInput.get(taskNum - 1).toString());
                userInput.remove(taskNum - 1);
                printNumberOfTasks();
                editFileAll();
            }
        } else {
            throw new DukeException(INDENT_1 + "☹ OOPS!!! The task to be deleted" +
                    "is not indicated!!");
        }
    }

    public void inputDone(String input, String[] splitInput) throws DukeException {
        if (splitInput.length == 2) {
            int taskNum = Integer.valueOf(splitInput[1]);
            if (taskNum > userInput.size() || taskNum <= 0) {
                throw new DukeException(INDENT_1 + "☹ OOPS!!! There is no corresponding task to be " +
                        "marked done.");
            } else {
                Task temp = userInput.get(Integer.valueOf(splitInput[1]) - 1);
                temp.markAsDone();
                System.out.println(DIVIDER);
                System.out.println(INDENT_1 + "YAY good job for completing the task :)\n" +
                        INDENT_1 + "I've marked it as done:\n" + INDENT_2 +
                        temp.toString());
                System.out.println(DIVIDER);
                editFileAll();
            }
        } else if (splitInput.length == 1) {
            throw new DukeException(INDENT_1 + "☹ OOPS!!! The task to be marked done is not indicated!!");
        }
    }

    public void addTask(Task task) {
        userInput.add(task);
        System.out.println(DIVIDER);
        System.out.println(INDENT_1 + "Sure! I've added this task:");
        System.out.println(INDENT_2 + task.toString());
        printNumberOfTasks();
        appendToFile("data/Tasks.txt", fileString(task));
    }


    public void printNumberOfTasks() {
        String numberOfTasks = INDENT_1 + "Now you have " + userInput.size() +
                (userInput.size() == 1 ? " task" : " tasks") + " in the list.\n" +
                DIVIDER;
        System.out.println(numberOfTasks);
    }

    public String fileString(Task task) {
        String toAdd = task.taskIndicator() + "  " + (task.getStatusIcon().equals("X")
                    ? "Y" : "N") + "  " + task.description.trim();

        if(task.taskIndicator().equals("D")) {
            Deadline temp = (Deadline) task;
            toAdd += "  " + temp.getBy().trim();
        }  else if(task.taskIndicator().equals("E")) {
            Event temp = (Event) task;
            toAdd += "  " + temp.getAt().trim();
        }
        return toAdd;
    }

    public void editFileAll() {
        for (int i = 0; i < userInput.size(); i++) {
            Task tempFile = userInput.get(i);
            String toAdd = fileString(tempFile);
            if (i == 0) {
                editFile("data/Tasks.txt", toAdd);
            } else {
                appendToFile("data/Tasks.txt", toAdd);
            }
        }
    }

    public void editFile(String fileName, String content) {
        try {
            FileWriter fw = new FileWriter(fileName);
            fw.write(System.lineSeparator() + content);
            fw.close();
        } catch (IOException e) {
            System.out.println("OH NO :( There seems to be something wrong with the file.");
        }
    }

    public void appendToFile(String fileName, String content) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            fw.append(System.lineSeparator() + content);
            fw.close();
        } catch (IOException e) {
            System.out.println("OH NO :( There seems to be something wrong with the file.");
        }
    }

    public void checkTask(String input) throws DukeException {
        String[] splitInput2 = input.split(" +", 2);
        String taskType = splitInput2[0];
        if (splitInput2.length == 2) {
            if (taskType.equals("todo")) {
                Todo todo = new Todo(splitInput2[1]);
                addTask(todo);
            } else {
                String[] splitTask2 = splitInput2[1].split(" +");
                String[] splitTask = taskType.equals("deadline")
                        ? splitInput2[1].split("/by") :
                        splitInput2[1].split("/at");
                if (splitTask.length == 2 && !splitTask[0].isBlank()) {
                    Task toAdd;
                    switch (taskType) {
                        case "deadline":
                            try {
                                LocalDate date = LocalDate.parse(splitTask[1].trim());
                                toAdd = new Deadline(splitTask[0], date);
                                addTask(toAdd);
                            } catch (DateTimeParseException e) {
                                System.out.println(INDENT_1 + "OH NO :( I can't seem to understand the date you have entered.\n"
                                        + INDENT_1 + "I can only understand if it is in  the yyyy-mm-dd format..");
                            }
                            break;
                        case "event":
                            try {
                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                                LocalDateTime dateTime = LocalDateTime.parse(splitTask[1].trim(), dtf);
                                toAdd = new Event(splitTask[0], dateTime);
                                addTask(toAdd);
                            } catch (DateTimeParseException e) {
                                System.out.println(INDENT_1 + "OH NO :( I can't seem " +
                                        "to understand the date and time you have entered.\n"
                                        + INDENT_1 + "I can only understand if it is in yyyy-MM-dd HH:mm format..");
                            }
                            break;
                    }
                } else {
                    if (taskType.equals("deadline") && !splitInput2[1].contains("/by")
                            || taskType.equals("event") && !splitInput2[1].contains("/at")) {
                        throw new DukeException(INDENT_1 + "☹ OOPS!!! The " +
                                (taskType.equals("event") ? "period which the event occurs" :
                                        "deadline") + " is not inputted correctly. Use " +
                                (taskType.equals("event") ? "/at" : "/by") +
                                " to indicate ;)");
                    } else {
                        noDescription(taskType);
                    }

                }

            }
        } else {
            noDescription(taskType);
        }
    }

    public static void noDescription(String taskType) throws DukeException {
        throw new DukeException(INDENT_1 + "☹ OOPS!!! The description of " +
                (taskType.equals("event") ? "an " : "a ")
                + taskType + " cannot be empty.");
    }


    public static void main(String[] args) {

        System.out.println("  ---------------------------------------------");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("  ---------------------------------------------");

        File taskFile = new File("data/Tasks.txt");
        if (!taskFile.exists()) {
            File dir = new File("data");
            if (!(dir.exists() && dir.isDirectory())) {
                dir.mkdir();
            }
            try {
               taskFile.createNewFile();
            } catch (IOException e) {

            } catch (SecurityException e) {

            }
        }
        Duke duke = new Duke(taskFile);
        duke.setUserInput();
        duke.getInput();

    }
}