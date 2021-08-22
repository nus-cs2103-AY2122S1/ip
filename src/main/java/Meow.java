import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a chat bot Meow who will be performing different tasks
 * according to user input.
 */
public class Meow {
    private boolean isExit = false;
    private List<Task> tasksList = new ArrayList<>();

    enum Command {
        BYE,
        LIST,
        DONE,
        DELETE,
        TODO,
        EVENT,
        DEADLINE
    }

    /**
     * A public constructor to initialize a Meow object.
     */
    public Meow() {

    }

    /**
     * Prints the greeting message from the chat bot cat Meow.
     *
     * @return A boolean indicating whether the user wants to exit to
     * chat bot or nor.
     */
    public boolean getIsExit() {
        return this.isExit;
    }

    /**
     * Prints the greeting message from the chat bot cat Meow.
     */
    public void greet() {

        System.out.println(
                "------------------------------------------------------------------------------\n" +
                        "Meow: Hi human, I'm your cat Meow~ What can I do for you?\n" +
                        "------------------------------------------------------------------------------"
        );
    }


    /**
     * Prints the goodbye message from the chat bot cat Meow.
     */
    private void exit() {

        System.out.println(
                "------------------------------------------------------------------------------\n" +
                        "Meow: Bye human, see you soon! Your cat Meow is going to sleep now~\n" +
                        "------------------------------------------------------------------------------"
        );
    }


    private void displayList() throws NoItemInTheListException {
        int len = tasksList.size();
        if (len > 0) {
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < len; i++) {
                Task task = tasksList.get(i);
                System.out.println(i + 1 + ". " + task.toString());
            }
            System.out.println("------------------------------------------------------------------------------");
        } else {
            throw new NoItemInTheListException();
        }
    }

    private void writeToFile(String filePath, String textToAdd) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void createFile(String filePath) {
        try {
            File file = new File(filePath);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFolder(String filePath) {
        File file = new File(filePath);
        file.mkdir();
    }

    /**
     * Reads the local file and converts it the array, if the folder or file
     * does not exists, create an empty one.
     */
    public void convertFileToArray() {
        File folder = new File("data");
        if (folder.isDirectory()) {
            try {
                addFileContentToArray("data/meow.txt");
            } catch (FileNotFoundException e) {
                // Create a file
                createFile("data/meow.txt");
            }
        } else {
            // Create a folder
            createFolder("data");
        }
    }

    private void addFileContentToArray(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String task = s.nextLine();
            addTaskToArray(task);
        }
    }

    private void addTaskToArray(String input) {
        String[] values = input.split(" \\| ");
        String typeOfTask = values[0].trim();
        String completeStatus = values[1].trim();
        String task = values[2].trim();
        switch (typeOfTask) {
        case "T": {
            Todo newTodo = new Todo(task);
            if (completeStatus.equals("1")) {
                newTodo.markAsDone();
            }
            tasksList.add(newTodo);
            break;
        }
        case "D": {
            String date = values[3].trim();
            Deadline newDeadline = new Deadline(task, date);
            if (completeStatus.equals("1")) {
                newDeadline.markAsDone();
            }
            tasksList.add(newDeadline);
            break;
        }
        case "E": {
            String date = values[3].trim();
            Event newEvent = new Event(task, date);
            if (completeStatus.equals("1")) {
                newEvent.markAsDone();
            }
            tasksList.add(newEvent);
            break;
        }
        default: {
            break;
        }
        }
    }

    /**
     * Checks whether the task is able to be marked as done, 0 indicating that
     * this is an invalid task, Integer.MAX_VALUE indicating that this task is
     * not in the task list, any number other than 0 or Integer.MAX_VALUE indicating
     * the task number to be marked as done.
     *
     * @param index The task number that the user wants to mark as done.
     * @return An integer indicating which task to be marked as done.
     */
    private void completeTask(String index) throws MeowException {
        try {
            int taskNumber = Integer.parseInt(index);
            if (taskNumber <= tasksList.size() && taskNumber > 0) {
                Task completedTask = tasksList.get(taskNumber - 1);
                completedTask.markAsDone();
                addArrayTaskToFile();
                System.out.println("------------------------------------------------------------------------------");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(completedTask.toString());
                System.out.println("------------------------------------------------------------------------------");
            } else {
                throw new InvalidTaskIndex();
            }
        } catch (NumberFormatException e) {
            // String cannot be parsed to integer
            throw new NotSuchTaskFoundException();
        }
    }

    private void printTaskList(Task taskAdded) {
        int taskListLength = tasksList.size();
        String task = taskListLength <= 1 ? " task " : " tasks ";
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println(taskAdded.toString());
        System.out.println("Now you have " + taskListLength + task + "in the list.");
        System.out.println("------------------------------------------------------------------------------");
    }

    private void addArrayTaskToFile() {
        String addedContent = "";
        for (int i = 0; i < tasksList.size(); i++) {
            addedContent = addedContent + tasksList.get(i).toString() + System.lineSeparator();
        }
        writeToFile("data/meow.txt", addedContent);
    }

    private void addTodo(String todo) {
        Todo newTodo = new Todo(todo);
        tasksList.add(newTodo);
        addArrayTaskToFile();
        printTaskList(newTodo);
    }

    private void addDeadline(String deadline, String by) {
        Deadline newDeadline = new Deadline(deadline, by);
        tasksList.add(newDeadline);
        addArrayTaskToFile();
        printTaskList(newDeadline);
    }

    private void addEvent(String event, String at) {
        Event newEvent = new Event(event, at);
        tasksList.add(newEvent);
        addArrayTaskToFile();
        printTaskList(newEvent);
    }

    private String getTask(String input, Command typeOfTask) throws MeowException {
        try {
            return input.trim().substring(typeOfTask.toString().length() + 1);
        } catch (StringIndexOutOfBoundsException exception) {
            if (typeOfTask.equals(Command.TODO)) {
                throw new EmptyTodoDescriptionException();
            } else if (typeOfTask.equals(Command.DEADLINE)) {
                throw new EmptyDeadlineDescriptionException();
            } else {
                throw new EmptyEventDescriptionException();
            }

        }

    }

    private String[] getTaskAndDate(String task, Command typeOfTask) {
        String[] split;
        if (typeOfTask.equals(Command.DEADLINE)) {
            split = task.split(" /by ");
        } else {
            split = task.split(" /at ");
        }
        return split;
    }

    private void deleteTask(String index) throws MeowException {
        try {
            int taskNumber = Integer.parseInt(index);
            if (taskNumber <= tasksList.size() && taskNumber > 0) {
                Task removedTask = tasksList.remove(taskNumber - 1);
                addArrayTaskToFile();
                int taskListLength = tasksList.size();
                String task = taskListLength <= 1 ? " task " : " tasks ";
                System.out.println("------------------------------------------------------------------------------");
                System.out.println("Noted. I've removed this task:");
                System.out.println(removedTask.toString());
                System.out.println("Now you have " + taskListLength + task + "in the list.");
                System.out.println("------------------------------------------------------------------------------");
            } else {
                throw new InvalidTaskIndex();
            }
        } catch (NumberFormatException e) {
            // String cannot be parsed to integer
            throw new NotSuchTaskFoundException();
        }
    }

    /**
     * Performs different tasks according to
     * the command that the user has entered.
     *
     * @param input The input command from the user.
     * @throws MeowException If the user input is invalid.
     */
    public void checkCommand(String input) throws MeowException {
        try {
            String[] commandWord = input.split(" ");
            Command userCommand = Command.valueOf(commandWord[0].trim().toUpperCase());
            switch (userCommand) {
            case BYE: {
                exit();
                isExit = true;
                break;
            }
            case LIST: {
                displayList();
                break;
            }
            case DONE: {
                completeTask(commandWord[1].trim());
                break;
            }
            case TODO: {
                String task = getTask(input, userCommand);
                addTodo(task);
                break;
            }
            case EVENT: {
                String task = getTask(input, userCommand);
                String[] taskAndDate = getTaskAndDate(task, userCommand);
                try {
                    addEvent(taskAndDate[0], taskAndDate[1]);
                    break;
                } catch (ArrayIndexOutOfBoundsException exception) {
                    throw new EmptyEventTimeException();
                }
            }
            case DEADLINE: {
                String task = getTask(input, userCommand);
                String[] taskAndDate = getTaskAndDate(task, userCommand);
                try {
                    addDeadline(taskAndDate[0], taskAndDate[1]);
                    break;
                } catch (ArrayIndexOutOfBoundsException exception) {
                    throw new EmptyDeadlineTimeException();
                }
            }
            case DELETE: {
                deleteTask(commandWord[1].trim());
                break;
            }
            default: {
                throw new InvalidInputException();
            }
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException();
        }

    }


}
