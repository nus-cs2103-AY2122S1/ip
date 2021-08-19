import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Duke class used to run the Duke chat-bot.
 * Contains methods that
 * (i)    runs the chat-bot
 * (ii)   exits the chat-bot
 * (iii)  lists all tasks
 * (iv)   marks a given task as done
 * (v)    deletes a given task
 * (vi)   inputs a deadline task
 * (vii)  inputs an event task
 * (viii) inputs a todo task
 * (ix)   check user input for keywords
 * (x)    display separator line
 * (xi)   displays total number of tasks
 * (xii)  counts number of spaces in user input
 * (xiii) provide a user guide
 */
public class Duke {
    private final ArrayList<Task> taskList = new ArrayList<>();

    private enum Keywords {bye, list, done, todo, deadline, event, allCmd, delete}

    public static void main(String[] args) {
        Duke chatBot = new Duke();
        chatBot.run();
    }


    /**
     * The run method runs the Duke chat-bot.
     * By taking in user keyboard input, it checks the input for keywords
     * and responds accordingly. Invalid input is caught and the custom
     * DukeException is thrown to inform the user that the command is invalid
     * or not properly formatted.
     */
    private void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you today?");
        readFromFile();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String des = sc.nextLine();
            String command = checkForKeyword(des);
            try {
                this.printLine();
                if (command == null) {
                    throw new DukeException(des + " is not a recognised command\nPlease refer to the available commands using the \"allCmd\" command");
                } else {
                    if (command.equals("bye")) {
                        this.byeCommand();
                        this.printLine();
                        break;
                    }

                    if (command.equals("allCmd")) {
                        this.possibleCommands();
                    }

                    if (command.equals("list")) {
                        this.listCommand();
                    }

                    if (command.equals("done")) {
                        this.doneCommand(des);
                    }

                    if (command.equals("deadline")) {
                        this.deadlineCommand(des);
                    }

                    if (command.equals("event")) {
                        this.eventCommand(des);
                    }

                    if (command.equals("todo")) {
                        this.toDoCommand(des);
                    }

                    if (command.equals("delete")) {
                        this.deleteCommand(des);
                    }

                    this.printLine();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                this.printLine();
            }

        }
    }

    private void byeCommand() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * The listCommand() method lists all tasks.
     * Individual tasks are typecast into their respective classes in order for
     * the toString() methods to work as intended.
     */
    private void listCommand() {
        int count = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task t : taskList) {
            System.out.print(count + ". ");
            if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                System.out.println(d);
            } else if (t instanceof Event) {
                Event e = (Event) t;
                System.out.println(e);
            } else if (t instanceof ToDo) {
                ToDo td = (ToDo) t;
                System.out.println(td);
            } else {
                System.out.println(t.toString());
            }
            count++;
        }
    }

    /**
     * The doneCommand() method marks a given Task as completed.
     *
     * @param des the user input into the Duke chat-box.
     * @throws DukeException if done command is not formatted properly
     *                       or if task has already been completed.
     */
    private void doneCommand(String des) throws DukeException {
        String sNum = des.substring(des.lastIndexOf(' ') + 1);
        int num = Integer.parseInt(sNum);
        if (num <= 0 || num > taskList.size()) {
            throw new DukeException("The input number is not a valid task number \nPlease refer to the task list using the \"list\" command");
        } else if (this.countSpaces(des) > 1) {
            throw new DukeException("Too many arguments being provided to \"done\" \nPlease refer to proper usage of commands with \"allCmd\"");
        } else {
            Task atHand = taskList.get(num - 1);
            if (atHand.getIsDone()) {
                throw new DukeException("You have already completed this task.");
            } else {
                atHand.markAsDone();
                System.out.println("I see that you have completed a task. Keep up the good work!");
                System.out.println();
                System.out.println("This task has now been marked as completed");
                System.out.println(atHand.getStatusIcon() + " " + atHand.getDescription());
            }
        }
        createFile();
        writeToFile();
    }

    /**
     * The deleteCommand() method deletes a given command.
     *
     * @param des the user input into the Duke chat-box.
     * @throws DukeException if input number is not valid or if too many arguments
     *                       are provided to deleteCommand().
     */
    private void deleteCommand(String des) throws DukeException {
        String sNum = des.substring(des.lastIndexOf(' ') + 1);
        int num = Integer.parseInt(sNum);
        if (num <= 0 || num > taskList.size()) {
            throw new DukeException("The input number is not a valid task number \nPlease refer to the task list using the \"list\" command");
        } else if (this.countSpaces(des) > 1) {
            throw new DukeException("Too many arguments being provided to \"delete\" \nPlease refer to proper usage of commands with \"allCmd\"");
        } else {
            taskList.remove(num - 1);
            System.out.println("Successfully removed task " + num);
        }
        createFile();
        writeToFile();
    }

    /**
     * The deadlineCommand() method inputs a Deadline task into the Duke chat-bot.
     *
     * @param des the user input into the Duke chat-box.
     * @throws DukeException if input is not correctly formatted with task and due
     *                       date arguments.
     */
    private void deadlineCommand(String des) throws DukeException {
        if (des.equals("deadline")) {
            throw new DukeException("\"deadline\" command not correctly formatted \nPlease insert task and due date arguments");
        }
        if (!des.contains("/by")) {
            throw new DukeException("\"deadline\" command not correctly formatted \nPlease do not forget to include \"by\" and insert due date argument");
        }
        try {
            String description = des.substring(9, des.indexOf('/') - 1);
            String date = des.substring(des.indexOf('/') + 4); //+4 as we do not want to include the "/by " in our output
            Deadline atHand = new Deadline(description, date);
            taskList.add(atHand);
            System.out.println("Sure. The following task has been added: ");
            System.out.println(atHand);
            this.numberOfTasks();
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("\"deadline\" command not correctly formatted");
        }
        createFile();
        writeToFile();
    }

    /**
     * The eventCommand() method inputs an Event task into the Duke chat-bot.
     *
     * @param des the user input into the Duke chat-box.
     * @throws DukeException if input is not correctly formatted with task and
     *                       timeframe arguments.
     */
    private void eventCommand(String des) throws DukeException {
        if (des.equals("event")) {
            throw new DukeException("\"event\" command not correctly formatted \nPlease insert task and timeframe arguments");
        }
        if (!des.contains("/at")) {
            throw new DukeException("\"event\" command not correctly formatted \nPlease do not forget to include \"at\" and insert timeframe argument");
        }
        try {
            String description = des.substring(6, des.indexOf('/') - 1);
            String timeframe = des.substring(des.indexOf('/') + 4); //+4 as we do not want to include the "/by " in our output
            Event atHand = new Event(description, timeframe);
            taskList.add(atHand);
            System.out.println("Sure. The following task has been added: ");
            System.out.println(atHand);
            this.numberOfTasks();
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("\"event\" command not correctly formatted");
        }
        createFile();
        writeToFile();
    }

    /**
     * The toDoCommand() method inputs a ToDo task into the Duke chat-bot.
     *
     * @param des the user input into the Duke chat-box.
     * @throws DukeException if input is not correctly formatted with task argument.
     */
    private void toDoCommand(String des) throws DukeException {
        if (des.equals("todo")) {
            throw new DukeException("\"todo\" command not correctly formatted \nPlease insert task argument");
        }
        String description = des.substring(5);
        ToDo atHand = new ToDo(description);
        taskList.add(atHand);
        System.out.println("Sure. The following task has been added: ");
        System.out.println(atHand);
        this.numberOfTasks();

        createFile();
        writeToFile();
    }

    /**
     * The checkForKeyword() method checks user input for keywords and responds
     * accordingly by calling the respective commands.
     *
     * @param des the user input into the Duke chat-box.
     * @return String type object that informs the run() method which
     * command should be called.
     */
    private String checkForKeyword(String des) {
        if (des.equals("allCmd")) {
            return "allCmd";
        } else if (des.equals("bye")) {
            return "bye";
        } else if (des.equals("list")) {
            return "list";
        } else if (des.contains("done") && des.startsWith("done")) {
            try {
                String sNum = des.substring(des.indexOf(' ') + 1);
                Integer.parseInt(sNum);
                return "done";
            } catch (NumberFormatException e) {
                return null;
            }
        } else if (des.contains("delete") && des.startsWith("delete")) {
            try {
                String sNum = des.substring(des.indexOf(' ') + 1);
                Integer.parseInt(sNum);
                return "delete";
            } catch (NumberFormatException e) {
                return null;
            }
        } else if (des.contains("deadline") && des.startsWith("deadline")) {
            return "deadline";
        } else if (des.contains("event") && des.startsWith("event")) {
            return "event";
        } else if (des.contains("todo") && des.startsWith("todo")) {
            return "todo";
        }
        return null;
    }

    private void printLine() {
        System.out.println("_____________________________________________________________________________________________________________");
    }

    /**
     * The numberOfTasks() method informs the user of the total number of tasks.
     */
    private void numberOfTasks() {
        if (taskList.size() == 1) {
            System.out.println("You now have " + taskList.size() + " task in the list");
        } else {
            System.out.println("You now have " + taskList.size() + " tasks in the list");
        }
    }

    /**
     * The countSpaces method counts the number of blank spaces in a given String.
     *
     * @param des the user input into the Duke chat-box.
     * @return Integer type object that represents the number of blank spaces in
     * the user input.
     */
    private int countSpaces(String des) {
        int count = 0;
        for (int i = 0; i < des.length(); i++) {
            if (des.charAt(i) == ' ') {
                count++;
            }
        }
        return count;
    }

    /**
     * The possibleCommands() method serves as an in-built user-guide that
     * furnishes the user with information on how to use and format all
     * the commands available.
     */
    private void possibleCommands() {
        System.out.println("The possible commands are as follows:");
        System.out.println();

        System.out.println("1. bye  -------- exit the Duke chat-bot");
        System.out.println();

        System.out.println("2. list -------- list all tasks");
        System.out.println();

        System.out.println("3. done -------- Usage --> done x, where x is an integer.");
        System.out.println("               - Marks the corresponding task as completed");
        System.out.println();

        System.out.println("4. todo -------- Usage --> \"todo borrow book\"");
        System.out.println("               - Inputs the a ToDo task into the task list");
        System.out.println();

        System.out.println("5. deadline ---- Usage --> \"deadline submit essay /by Sunday \", remember not to miss the \"/by\" symbol!");
        System.out.println("               - Inputs the an Deadline task into the task list");
        System.out.println();

        System.out.println("6. event ------- Usage --> \"event project meeting /at Mon 2-4pm \", remember not to miss the \"/at\" symbol!");
        System.out.println("               - Inputs the an Event task into the task list");

        System.out.println("7. delete ------ Usage --> delete x, where x is an integer.");
        System.out.println("               - Deletes the corresponding task");
        System.out.println();

    }

    private void createFile() {
        File dir = new File("data/");
        File tasks = new File("data/tasks.txt");
        try {
            dir.mkdir();
            if (tasks.createNewFile()) {
                System.out.println(tasks.getName() + " created");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void writeToFile() {
        try {
            FileWriter writer = new FileWriter("data/tasks.txt");
            for (Task t : taskList) {
                writer.write(t.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void readFromFile() {
        try {
            File tasks = new File("data/tasks.txt");
            Scanner sc = new Scanner(tasks);
            while (sc.hasNextLine()) {
                String atHand = sc.nextLine();
                String taskType = atHand.substring(1, 2);
                String done = (atHand.charAt(4) == ' ') ? " " : "X";
                String des;
                String time = "NA";
                Task t;

                if (taskType.equals("T")) {
                    des = atHand.substring(7);
                    t = new ToDo(done, des);
                } else if (taskType.equals("E")) {
                    int openBracket = atHand.indexOf('(');
                    int closeBracket = atHand.indexOf(')');
                    des = atHand.substring(7, openBracket - 1);
                    time = atHand.substring(openBracket + 5, closeBracket);
                    t = new Event(done, des, time);
                } else if (taskType.equals("D")) {
                    int openBracket = atHand.indexOf('(');
                    int closeBracket = atHand.indexOf(')');
                    des = atHand.substring(7, openBracket - 1);
                    time = atHand.substring(openBracket + 5, closeBracket);
                    t = new Deadline(done, des, time);
                } else {
                    throw new DukeException("Task Type not recognised. Task not loaded into Duke chat-bot");
                }
                taskList.add(t);
            }

        } catch (FileNotFoundException e) {
            return;
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
