import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the robot which has corresponding reaction to the user inputs.
 *
 * @author QIN GUORUI
 */
public class Duke {
    /** The content in the data file. */
    private static String content = "";

    /** The recommended working directory path. */
    private static String dir = System.getProperty("user.dir");

    /** The data structure used to store the tasks. */
    private static final ArrayList<Task> TASK_LIST = new ArrayList<>(100);

    /** Number of tasks stored. */
    private static int count;

    /** User inputs. */
    private static String response;

    /** The length of user input. */
    private static int len;

    public static void main(String[] args) {
        try {
            getData();
        } catch (IOException e) {
            System.out.println("The whole thing crashed without a reason.");
        }
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Start Prompt.
        System.out.println(getPattern("Hello! I'm Duke\n   What can I do for you?"));

        //User input.
        Scanner sc = new Scanner(System.in);
<<<<<<< HEAD
        boolean isExit = true;

        //Continue the loop until user says bye.
        while (isExit) {
            response = sc.nextLine();
            len = response.length();
            isExit = echo();
        }
    }

    public static void getData() throws IOException {
        String dir = System.getProperty("user.dir");
        // inserts correct file path separator on *nix and Windows
        // works on *nix
        // works on Windows
        java.nio.file.Path path = java.nio.file.Paths.get(dir,"data");
        boolean directoryExists = java.nio.file.Files.exists(path);
        if (!directoryExists) {
            new File(dir + "/data");
        }
        File f = new File(path + "/duke.txt");
        try {
            Scanner s = new Scanner(f);
            StringBuilder sb = new StringBuilder();
            while (s.hasNextLine()) {
                String curr = s.nextLine();
                int lens = curr.length();
                // No content
                if (lens == 0) {
                    return;
                }
                String oneLine = curr + System.lineSeparator();
                sb.append(oneLine);

                // Judge data input to add tasks.
                char type = curr.charAt(0);
                char done = curr.charAt(4);
                if (type == 'T') {
                    Todo todo = new Todo(curr.substring(8, lens));
                    if (done == '1') {
                        todo.markAsDone();
                    }
                    TASK_LIST.add(todo);
                } else {
                    String[] parts = curr.substring(8, lens).split(" ~ ");
                    String content = parts[0];
                    String by = parts[1];
                    Task deadlineOrEvent;
                    if (type == 'D') {
                        deadlineOrEvent = new Deadline(content, by);
                    } else {
                        deadlineOrEvent = new Event(content, by);
                    }
                    if (done == '1') {
                        deadlineOrEvent.markAsDone();
                    }
                    TASK_LIST.add(deadlineOrEvent);
                }
                count++;
            }
            content = sb.toString();
        } catch (FileNotFoundException e) {
            boolean isCreated = f.createNewFile();
            if (isCreated) {
                System.out.println("New data file is created.");
            }
        }
    }

    /**
     * Writes the text to data file, which overwrites initial storage.
     *
     * @param filePath The path to data file.
     * @param textToAdd The overwritten text.
     * @throws IOException When write to file fails.
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Stores the task to data file.
     *
     * @param task The string format of task representation.
     */
    public static void store(String task) {
        String dataFile = dir + "/data/duke.txt";
        try {
            String data = transformToData(task);
            appendToFile(dataFile, data + System.lineSeparator());
            content = content + data + System.lineSeparator();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Appends the text to existing data file.
     *
     * @param filePath The path to data file.
     * @param textToAppend The text tend to add.
     * @throws IOException When the append operation fails.
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Deletes the task at certain place.
     *
     * @param place The index when the task is stored in task list.
     */
    public static void delete(int place) {
        try {
            String dataFile = dir + "/data/duke.txt";
            String[] parts = content.split(System.lineSeparator());
            int lens = parts.length;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < lens; i++) {
                if (i == place) {
                    continue;
                }
                String temp = parts[i] + System.lineSeparator();
                sb.append(temp);
            }
            content = sb.toString();
            writeToFile(dataFile, content);
        } catch (IOException e) {
            System.out.println("Delete failed.");
=======
        boolean isContinue = true;

        //Continue the loop until user says bye.
        while (isContinue) {
            response = sc.nextLine();
            len = response.length();
            isContinue = echo();
>>>>>>> branch-Level-8
        }
    }

    /**
     * Returns formatted string output.
     *
     * @param r The input string.
     * @return Formatted string.
     */
    public static String getPattern(String r) {
        StringBuilder result = new StringBuilder();
        StringBuilder curr = new StringBuilder();
        String empty = "   ";
        curr.append("*".repeat(80));
        String out = empty + curr + "\n" + empty + r + "\n" + empty + curr + "\n";
        result.append(out);
        return result.toString();
    }

    /**
     * Returns all strings stored with indexing.
     *
     * @return All user's stored strings.
     */
    public static String toListStrings() {
        StringBuilder curr = new StringBuilder();
        String end = "\n   ";
        String begin = "Here are the tasks in your list:\n   ";
        curr.append(begin);
        for (int i = 0; i < count; i++) {
            if (i == count - 1) {
                end = "";
            }
            String out = ((Integer) (i + 1)).toString() + "." + TASK_LIST.get(i).toString() + end;
            curr.append(out);
        }
        return curr.toString();
    }

    /**
     * Returns boolean value which checks whether
     * input string is digit or not.
     *
     * @param input String input.
     * @return Content of input is digit or not.
     */
    public static boolean chekDigit(String input) {
        boolean isDigit = true;
        int i = 0;
        if (input.charAt(0) == '-') {
            i = 1;
        }
        for (; i < input.length(); i++) {
            char curr = input.charAt(i);
            if (!(curr >= '0' && curr <= '9')) {
                isDigit = false;
                break;
            }
        }
        return isDigit;
    }

    /**
     * Returns the formatted string output.
     *
     * @param task The possible task string representations.
     * @return The desirable output string related to task.
     */
    public static String getOutputFrame(String task) {
        String title = "Got it. I've added this task:\n   ";
        String middle = "  " + task + "\n   ";
        String end = "Now you have " + count + " tasks in the list.";
        return title + middle + end;
    }

    /**
     * Returns a boolean checking whether the user input is
     * related to to-do operations.
     *
     * @return Whether the input is related to to-do or not.
     */
    public static boolean checkTodo() {
        //check with the special response "to-do X", where X is what to do.
        Todo todo = new Todo(response.substring(5, len));
        TASK_LIST.add(todo);
<<<<<<< HEAD
        String todoString = todo.toString();
        store(todoString);
        count++;
        System.out.println(getPattern(getOutputFrame(todoString)));
=======
        count++;
        System.out.println(getPattern(getOutputFrame(todo.toString())));
>>>>>>> branch-Level-8
        return true;
    }

    /**
     * Returns a boolean checking whether the user input is
     * related to deadline operations.
     *
     * @return Whether the input is related to deadline or not.
     */
    public static boolean checkDeadline(){
        //check with the special response "deadline X", where X is what to do and by what time.
        String[] parts = response.substring(9, len).split(" /by ");
        String content = parts[0];
        String time = parts[1];
        Deadline deadline = new Deadline(content, time);
        TASK_LIST.add(deadline);
<<<<<<< HEAD
        String deadlineString = deadline.toString();
        store(deadlineString);
        count++;
        System.out.println(getPattern(getOutputFrame(deadlineString)));
=======
        count++;
        System.out.println(getPattern(getOutputFrame(deadline.toString())));
>>>>>>> branch-Level-8
        return true;
    }

    /**
     * Returns a boolean checking whether the user input is
     * related to event operations.
     *
     * @return Whether the input is related to event or not.
     */
    public static boolean checkEvent(){
        //check with the special response "event X", where X includes what to do and time to do.
        String[] parts = response.substring(6, len).split(" /at ");
        String content = parts[0];
        String time = parts[1];
        Event event = new Event(content, time);
        TASK_LIST.add(event);
<<<<<<< HEAD
        String eventString = event.toString();
        store(eventString);
        count++;
        System.out.println(getPattern(getOutputFrame(eventString)));
=======
        count++;
        System.out.println(getPattern(getOutputFrame(event.toString())));
>>>>>>> branch-Level-8
        return true;
    }

    /**
     * Returns a boolean checking whether the user input is
     * related to delete operations.
     *
     * @return Whether the input is related to delete or not.
     */
    public static boolean checkDelete(){
        //check with the special response "delete X", where X is index of deleted item.
        try {
            int curr = Integer.parseInt(response.substring(7, len));
            Task shouldDelete;
            try {
                shouldDelete = TASK_LIST.get(curr - 1);
            } catch (IndexOutOfBoundsException e) {
                throw new OutOfRangeException("delete");
            }
            TASK_LIST.remove(curr - 1);
            delete(curr - 1);
            count--;
            String title = "Noted. I've removed this task: \n";
            String out = "     " + shouldDelete.toString() + "\n   ";
            String end = "Now you have " + count + " tasks in the list.";
            System.out.println(getPattern(title + out + end));
            return true;
        } catch (OutOfRangeException e) {
            System.out.println(getPattern(e.getMessage()));
            return true;
        }
    }

    public static String transformToTask(String data) {
        return "";
    }

    /**
     * Transforms the task representation to storage form in data file.
     *
     * @param task The task string representation.
     * @return The string format stored in the data file.
     */
    public static String transformToData(String task) {
        int lens = task.length();
        char type = task.charAt(1);
        char done = task.charAt(4);
        String split = "by: ";
        String prefix = "0";
        String dataForm = "";
        if (done == 'X') {
            prefix = "1";
        }
        if (type == 'T') {
            dataForm = "T | " + prefix + " | " + task.substring(8, lens);
        } else  {
            if (type == 'E') {
                split = "at: ";
            }
            String[] parts = task.substring(8, lens).split(split);
            String content = parts[0];
            int lensContent = content.length();
            content = content.substring(0, lensContent - 2);
            String time = parts[1];
            int lensBy = time.length();
            time = time.substring(0, lensBy - 1);
            dataForm = type + " | " + prefix + " | " + content + " ~ " + time;
        }
        return dataForm;
    }

    /**
     * Replaces the task at certain place in the task list.
     *
     * @param place The index where the task is stored in task list.
     */
    public static void replace(int place) {
        try {
            String dataFile = dir + "/data/duke.txt";
            String[] parts = content.split(System.lineSeparator());
            int lensParts = parts.length;
            String newData = transformToData(TASK_LIST.get(place).toString());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < lensParts; i++) {
                String temp =parts[i];
                if (i == place) {
                    temp = newData;
                }
                temp = temp + System.lineSeparator();
                sb.append(temp);
            }
            content = sb.toString();
            writeToFile(dataFile, content);
        } catch (IOException e) {
            System.out.println("Fail to replace.");
        }
    }

    /**
     * Returns a boolean checking whether user input is
     * related to mark done of tasks.
     *
     * @return Is done operation or not.
     */
    public static boolean checkDone() {
        //check with the special response "done X", where X is a number.
        try {
            int curr = Integer.parseInt(response.substring(5, len));
            Task shouldMark;
            try {
                shouldMark = TASK_LIST.get(curr - 1);
            } catch (IndexOutOfBoundsException e) {
                throw new OutOfRangeException("done");
            }
            shouldMark.markAsDone();
            String stringForm = shouldMark.toString();
            replace(curr - 1);
            String title = "Nice! I've marked this task as done: \n";
            String out = "     " + stringForm;
            System.out.println(getPattern(title + out));
            return true;
        } catch (OutOfRangeException e) {
            System.out.println(getPattern(e.getMessage()));
            return true;
        }
    }

    /**
     * Returns the correct enum operation according to response,
     * or it returns null to show exception occurred.
     *
     * @return Type of operation for the next judgement.
     */
    public static Operation checkResponse() {
        Operation op;
        try {
            if (response.startsWith("date ")
                    && Task.isDate(response.substring(5, len))) {
                op = Operation.DATE;
            } else if (response.startsWith("done ")
                    && chekDigit(response.substring(5, len))) {
                op = Operation.DONE;
            } else if (response.startsWith("todo ")) {
                op = Operation.TODO;
            } else if (response.startsWith("deadline ")
                    && response.substring(9, len).contains(" /by ")) {
                op = Operation.DEADLINE;
            } else if (response.startsWith("event ")
                    && response.substring(6, len).contains(" /at ")) {
                op = Operation.EVENT;
            } else if (response.startsWith("delete ")
                    && chekDigit(response.substring(7, len))) {
                op = Operation.DELETE;
            } else if (response.equals("delete") || response.equals("todo") || response.equals("deadline")
                    || response.equals("event") || response.equals("done") || response.equals("date")) {
                String curr = response;
                throw new EmptyInputException(curr);
            } else {
                //This means there's no match of operations.
                throw new NotRecognizeException();
            }
        }catch (DukeException e) {
            System.out.println(getPattern(e.getMessage()));
            return null;
        }
        return op;
    }

    public static boolean checkDate() {
        String preTime = response.substring(5, len);
        String actualTime = Task.dateAndTime(preTime);
        StringBuilder sb = new StringBuilder();
        String end = "\n   ";
        String begin = "Here are the tasks in your list:\n   ";
        sb.append(begin);
        for (int i = 0; i < count; i++) {
            Task curr = TASK_LIST.get(i);
            if (i == count - 1) {
                end = "";
            }
            if (!(curr instanceof Todo) && curr.getActualTime().equals(actualTime)) {
                String out = (i + 1) + "." + curr + end;
                sb.append(out);
            }
        }
        System.out.println(getPattern(sb.toString()));
        return true;
    }

    /**
     * Returns a boolean to react to a response, while
     * printing the required information.
     *
     * @return Whether the user continues to input or not.
     */
    public static boolean echo() {
        switch (response) {
        case "bye":
            System.out.println(getPattern("Bye, see you soon. ^-^"));
            return false;
        case "list":
            System.out.println(getPattern(toListStrings()));
            return true;
        case "content":
            System.out.println(getPattern(content));
            return true;
        default:
            Operation op = checkResponse();
            if (op == null) {
                return true;
            }
            switch (op) {
            case DEADLINE:
                return checkDeadline();
            case TODO:
                return checkTodo();
            case EVENT:
                return checkEvent();
            case DONE:
                return checkDone();
            case DELETE:
                return checkDelete();
            case DATE:
                return checkDate();
            default:
                return true;
            }
        }
    }
}
