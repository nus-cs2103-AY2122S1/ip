import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a Duke chatbot that can add tasks
 * to users' to-do list.
 *
 * @author Ne Zhijian, Didymus A0218159Y
 */
public class Duke {
    private boolean isOpen;
    private TaskList listOfTasks;
    private final String FILE_PATH = "./data/duke.txt";

    private Duke() { // constructor for Duke chat bot object
        this.listOfTasks = TaskList.makeNewTaskList();
    }

    /**
     * Represents the action command keyed in by the user.
     */
    public enum Action {
        LIST("list"),
        BYE("bye"),
        DONE("done"),
        TASK("task"),
        DELETE("delete"),
        ERRORS("error");

        private final String actionCommand;

        private Action(String actionCommmand) {
            this.actionCommand = actionCommmand;
        }

        /**
         * Returns the Action type ENUM for each string
         * @param s action word typed in by user
         * @return Action that corresponds to the user's entry
         */
        public static Action getAction(String s) {
            for (Action a : values()) {
                if (a.actionCommand.equals(s)) {
                    return a;
                }
            }
            return ERRORS;
        }
    }

    private void openDukeChatBot() {
        this.isOpen = true;
        Ui.printOpeningMessage();
    }

    private void closeDukeChatBot() {
        this.isOpen = false;
        Ui.printClosingMessage();
    }

    private Task addTaskToList(String item) {
        Task task = Task.createTask(item);
        this.listOfTasks.addTaskToList(task);
        return task;
    }

    private String getFirstWord(String s) {
        String[] arrString = s.split(" ", 2);
        return arrString[0];
    }

    private int getSecondNum(String s) throws DukeIncorrectInputs {
        String[] arrString = s.split(" ", 2);
        try {
            String second = arrString[1];
            Integer.parseInt(second);
        } catch (IllegalArgumentException e) {
            throw new DukeDoneIncorrectArgument();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeDoneIncorrectArgument();
        }
        return Integer.parseInt(arrString[1]);
    }

    private Task deleteTask(int i) throws DukeNoSuchTask {
        return this.listOfTasks.deleteTask(i);
    }

    private void saveTasks() throws IOException {
        StringBuffer tasksToWrite = new StringBuffer();
        int len = this.listOfTasks.getTotalNumber();

        for (int i = 0; i < len; i++) {
            tasksToWrite.append(this.listOfTasks.getTask(i).saveToFile() + "\n");
        }

        FileWriter fileWriter = new FileWriter(this.FILE_PATH);
        fileWriter.write(tasksToWrite.toString());
        fileWriter.close();
    }

    private void loadSavedTasks() throws IOException, DukeUnableLoadTask {
        File f = new File(this.FILE_PATH);
        Scanner sc = new Scanner(f); // Scanner with file as source

        int i = 1; // index counter

        while (sc.hasNextLine()) {
            String entry = sc.nextLine();
            String[] pastEntry = entry.split(" \\| ");

            String taskType = pastEntry[0];
            String isDone = pastEntry[1];

            switch (taskType) {
            case "T":
                this.addTaskToList("todo " + pastEntry[2]);
                if (isDone.equals("1")) {
                    this.listOfTasks.setTaskAsDone(i);
                }
                break;
            case "D":
                this.addTaskToList("deadline " + pastEntry[2] + " /by " + pastEntry[3]);
                if (isDone.equals("1")) {
                    this.listOfTasks.setTaskAsDone(i);
                }
                break;
            case "E":
                this.addTaskToList("event " + pastEntry[2] + " /at " + pastEntry[3]);
                if (isDone.equals("1")) {
                    this.listOfTasks.setTaskAsDone(i);
                }
                break;
            default:
                throw new DukeUnableLoadTask();
            }
            i++;
        }


    }



    /**
     * Function that runs to execute the chatbot Duke
     * @param args user inputs
     */
    public static void main(String[] args) {
        Duke d = new Duke();
        d.openDukeChatBot();
        Scanner sc = new Scanner(System.in);
        File output = new File(d.FILE_PATH);
        if (!output.isFile()) {
            output.getParentFile().mkdirs(); // if user does not have existing file path
            try {
                output.createNewFile();
            } catch (IOException e) {
                Ui.printErrorMessage(e);
            }
        }

        try {
            d.loadSavedTasks();
        } catch (IOException | DukeUnableLoadTask e) {
            Ui.printErrorMessage(e);
        }

        while (d.isOpen) {
            try {
                String userInput = sc.nextLine().strip();
                String firstWord = d.getFirstWord(userInput);
                firstWord = firstWord.equals("todo") || firstWord.equals("event") || firstWord.equals("deadline")
                            ? "task" : firstWord;
                Action actionCommand = Action.getAction(firstWord);

                switch (actionCommand) {
                case BYE:
                    d.closeDukeChatBot();
                    sc.close();
                    break;
                case LIST:
                    Ui.printReply(d.listOfTasks.toString());
                    break;
                case DONE:
                    d.listOfTasks.setTaskAsDone(d.getSecondNum(userInput));
                    Ui.printReply("\tNice! I've marked this task as done:\n" +
                            "\t\t" + d.listOfTasks.getTask(d.getSecondNum(userInput) - 1) + "\n");
                    d.saveTasks();
                    break;
                case DELETE:
                    Task taskRemoved = d.deleteTask(d.getSecondNum(userInput));
                    Ui.printReply("\tNoted. I've removed this task:\n" +
                            "\t\t" + taskRemoved + "\n" +
                            "\tNow you have " + d.listOfTasks.getTotal() + " in your list.\n");
                    d.saveTasks();
                    break;
                case TASK:
                    Ui.printReply("\tGot it. I've added this task:\n" +
                            "\t\t" + d.addTaskToList(userInput) + "\n" +
                            "\tNow you have " + d.listOfTasks.getTotal() + " in your list.\n");
                    d.saveTasks();
                    break;
                case ERRORS:
                    throw new DukeIncorrectCommandWord(new IllegalArgumentException());
                }
            } catch (DukeException | IOException e) {
                Ui.printErrorMessage(e);
                // System.err.println(Ui.formatDukeMessage(e.getMessage() + "\n"));
            }
        }
    }
}

