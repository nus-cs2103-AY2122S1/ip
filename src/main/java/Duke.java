import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Duke {
    private static final String LINE = "     ________________________________________\n"; // 5 spaces, 40 dashes
    private static final String INDENT = "     "; // 5 spaces
    private String USER_DIR = System.getProperty("user.dir");
    private List<Task> taskList;

    private enum taskKind {
        TODOS,
        DEADLINES,
        EVENTS
    }

    private boolean isRunning;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        try {
            Duke duke = new Duke();
            duke.loadDataFile(duke.taskList);
            duke.greet();
            duke.converse();
            duke.saveToDataFile(duke.taskList);
        } catch (IOException e) {
            System.out.println("Data file cannot be created or written to");
        }
    }

    public Duke() {
        taskList = new ArrayList<>(100);
        isRunning = true;
    }

    public void greet() {
        String greeting = LINE
            + INDENT + "Hello! I'm Duke\n"
            + INDENT + "What can I do for you?\n"
            + LINE;
        System.out.println(greeting);
    }

    public void loadDataFile(List<Task> taskList) throws IOException {
        File dataDir = new File(USER_DIR + "/data");
        File dataFile = new File(USER_DIR + "/data/dataFile.txt");
        if (!dataDir.exists()) {
            dataDir.mkdir();
            dataFile.createNewFile();// Throws IOException is something goes wrong
        } else {
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        }

        Scanner scanner = new Scanner(dataFile);
        while (scanner.hasNext()) {
            String currLine = scanner.nextLine();
            String[] currLineSplit = currLine.split("\\|", 4);
            Task currTask = null;

            switch (currLineSplit[0]) {
                case "T":
                    currTask = new ToDos(currLineSplit[2]);
                    break;
                case "D":
                    currTask = new Deadlines(currLineSplit[2], currLineSplit[3]);
                    break;
                case "E":
                    currTask = new Events(currLineSplit[2], currLineSplit[3]);
                    break;
            }

            assert currTask != null;
            if (currLineSplit[1].equals("1")) {
                currTask.markAsDone();
            }
            taskList.add(currTask);
        }
    }

    public void saveToDataFile(List<Task> taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(USER_DIR + "/data/dataFile.txt"); // Throws IOException is something goes wrong
        StringBuilder fileStrBuilder = new StringBuilder();
        for (Task task : taskList) {
            fileStrBuilder.append(task.toDataFileString()).append("\n");
        }
        fileWriter.write(fileStrBuilder.toString());
        fileWriter.close();
    }


    public void converse() {
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            String userInput = scanner.nextLine();
            String[] slashSplitInput = userInput.split("/", 2);
            String[] spaceSplitInput = slashSplitInput[0].split(" ", 2); // Everything before the slash, split again by space

            try {
                if (userInput.equals("bye")) { // Exit
                    System.out.println(LINE + INDENT + "Bye. Hope to see you again soon!\n" + LINE);
                    isRunning = false;
                } else if (userInput.equals("list")) {
                    listTask(taskList);
                } else if (spaceSplitInput[0].equals("done")) {
                    doneTask(spaceSplitInput, taskList);
                } else if (spaceSplitInput[0].equals("delete")) {
                    deleteTask(spaceSplitInput, taskList);
                } else if (spaceSplitInput[0].equals("todo")) {
                    addTask(spaceSplitInput, slashSplitInput, taskList, taskKind.TODOS);
                } else if (spaceSplitInput[0].equals("deadline")) {
                    addTask(spaceSplitInput, slashSplitInput, taskList, taskKind.DEADLINES);
                } else if (spaceSplitInput[0].equals("event")) {
                    addTask(spaceSplitInput, slashSplitInput, taskList, taskKind.EVENTS);
                } else {
                    // User inputs unrecognized commands
                    throw new DukeException(LINE + INDENT + "My intelligence has not evolved to understand this command :(\n" + LINE);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (NumberFormatException e) {
                System.out.println(LINE + INDENT + "Please enter a valid integer for task number!\n" + LINE);
                continue;
            }
        }
    }

    private void listTask(List<Task> taskList) {
        System.out.println(LINE + INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            System.out.println(INDENT + (i + 1) + "." + currTask.toString());
        }
        System.out.println(LINE);
    }

    private void doneTask(String[] spaceSplitInput, List<Task> taskList) throws DukeException, NumberFormatException {
        if (spaceSplitInput.length < 2) {
            // No task number entered
            throw new DukeException(LINE + INDENT + "Done Task number is missing!\n" + LINE);
        }
        int doneTaskNo = Integer.parseInt(spaceSplitInput[1]); // Throws NumberFormatException if string cannot be parsed into valid int
        if (doneTaskNo < 1 || doneTaskNo > taskList.size()) {
            // Task No entered out of range
            throw new DukeException(LINE + INDENT + "Task number entered out of range!\n" + LINE);
        }

        Task doneTask = taskList.get(doneTaskNo - 1);
        doneTask.markAsDone();
        System.out.println(LINE + INDENT + "Nice! I've marked this task as done:\n"
            + INDENT + INDENT + doneTask.toString() + "\n"
            + LINE);
    }

    private void deleteTask(String[] spaceSplitInput, List<Task> taskList) throws DukeException, NumberFormatException {
        if (spaceSplitInput.length < 2) {
            // No task number entered
            throw new DukeException(LINE + INDENT + "Delete task number is missing!\n" + LINE);
        }
        int deleteTaskNo = Integer.parseInt(spaceSplitInput[1]); // Throws NumberFormatException if string cannot be parsed into valid int
        if (deleteTaskNo < 1 || deleteTaskNo > taskList.size()) {
            // Task No entered out of range
            throw new DukeException(LINE + INDENT + "Task number entered out of range!\n" + LINE);
        }

        Task deleteTask = taskList.get(deleteTaskNo - 1);
        taskList.remove(deleteTaskNo - 1);
        System.out.println(LINE + INDENT + "Noted. I've removed this task:\n"
            + INDENT + INDENT + deleteTask.toString() + "\n"
            + INDENT + String.format("Now you have %d tasks in the list.\n", taskList.size())
            + LINE);
    }

    private void addTask(String[] spaceSplitInput, String[] slashSplitInput, List<Task> taskList, taskKind currTaskKind) throws DukeException {
        Task currTask = null;

        switch (currTaskKind) {
            case TODOS:
                if (spaceSplitInput.length < 2) {
                    // Tudo has no description. If has, spaceSplitInput has length 2.
                    throw new DukeException(LINE + INDENT + "Todo description cannot be empty!\n" + LINE);
                }
                // Only if no exception is thrown
                currTask = new ToDos(spaceSplitInput[1].trim()); // description trimmed of trailing white space behind
                break;
            case DEADLINES:
                if (spaceSplitInput.length < 2) {
                    // Deadline has no description
                    throw new DukeException(LINE + INDENT + "Deadline description cannot be empty!\n" + LINE);
                }
                if (slashSplitInput.length < 2) {
                    // Deadline has no slash (thus no date)
                    throw new DukeException(LINE + INDENT + "Deadline must has a date written after a slash!\n" + LINE);
                }
                if (slashSplitInput[1].split(" ", 2).length < 2) {
                    // If there're less than 2 words behind slash i.e. No "by" or nothing behind "by"
                    throw new DukeException(LINE + INDENT + "Deadline date must be in the format of 'by date'!" + "\n" + LINE);
                }
                // Only if no exceptions is thrown:
                String taskTimeDDL = slashSplitInput[1].split(" ", 2)[1]; // Extract taskTime by discarding the "by"
                currTask = new Deadlines(spaceSplitInput[1].trim(), taskTimeDDL);
                break;
            case EVENTS:
                if (spaceSplitInput.length < 2) {
                    // Event has no description
                    throw new DukeException(LINE + INDENT + "Event description cannot be empty!\n" + LINE);
                }
                if (slashSplitInput.length < 2) {
                    // Event has no slash (thus no date)
                    throw new DukeException(LINE + INDENT + "Event must has a date written after a slash!\n" + LINE);
                }
                if (slashSplitInput[1].split(" ", 2).length < 2) {
                    // If there're less than 2 words behind slash i.e. No "at" or nothing behind "at"
                    throw new DukeException(LINE + INDENT + "Event date must be in the format of 'at date'!" + "\n" + LINE);
                }
                // Only if no exception is thrown
                String taskTimeEvent = slashSplitInput[1].split(" ", 2)[1];
                currTask = new Events(spaceSplitInput[1].trim(), taskTimeEvent);
                break;
        }

        assert currTask != null;
        taskList.add(currTask);
        System.out.println(LINE + INDENT + "Got it. I've added this task:\n"
            + INDENT + INDENT + currTask.toString() + "\n"
            + INDENT + String.format("Now you have %d tasks in the list.\n", taskList.size())
            + LINE);
}
}
