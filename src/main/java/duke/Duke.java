package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Duke {

    /** Path string to data of tasks to load/save */
    private final static String PATH = "data/tasks.txt";

    /** Stores a list of tasks */
    private static TaskList taskList = new TaskList();

    /** Enum type to differentiate and parse user inputs */
    private enum Command {
        BYE       ("Usage: bye"),
        LIST      ("Usage: list"),
        DONE      ("Usage: done [task number]"),
        DELETE    ("Usage: delete [task number]"),
        TODO      ("Usage: todo [description]"),
        DEADLINE  ("Usage: deadline [description] /by [date in YYYY-MM-DD format]"),
        EVENT     ("Usage: event [description] /at [date in YYYY-MM-DD format]");

        private static Command[] taskCommands = {TODO, DEADLINE, EVENT};

        /** Help text for the command */
        private final String help_text;

        Command(String str) {
            this.help_text = str;
        }

        /**
         *
         * @param input String that is the user's input directly from scanner
         * @return Command that is activated by valid user input
         * @throws DukeException when user input is invalid
         */
        private static Command validate(String input) throws DukeException {
            // Separate user input to get command
            String[] input_split = input.split(" ", 2);

            // Throw error when there user keys in nothing
            int key_phrases = input_split.length;
            if (key_phrases < 1) {
                throw new DukeException("You better type sumthin or else..");
            }

            // Check for command to activate in user input's first word
            switch (input_split[0]) {
                case "bye": {
                    // Check if there is a key phrase after, if true throw error to double check with user
                    if (key_phrases > 1) {
                        throw new DukeException("Bye or nah?\n\t" + Command.BYE.help_text);
                    }
                    return Command.BYE;
                }
                case "list": {
                    // Check if there is a key phrase after, if true throw error to double check with user
                    if (key_phrases > 1) {
                        throw new DukeException("You want your list or nah?\n\t" + Command.LIST.help_text);
                    }
                    return Command.LIST;
                }
                case "done": {
                    // Make sure there is key phrase after to determine task to mark as done
                    if (key_phrases == 1) {
                        throw new DukeException("Done what brah??\n\t" + Command.DONE.help_text);
                    }
                    // Makes sure that key phrase is a valid int and within range of task list
                    try {
                        int index = Integer.parseInt(input_split[1]) - 1;
                        taskList.getTask(index);
                        return Command.DONE;
                    }
                    catch (NumberFormatException e) {
                        throw new DukeException("Not a number!\n\t" + Command.DONE.help_text);
                    }
                    catch (IndexOutOfBoundsException e) {
                        throw new DukeException("Task number does not exist!\n\t" + Command.DONE.help_text);
                    }
                }
                case "delete": {
                    // Make sure there is key phrase after to determine task to mark as done
                    if (key_phrases == 1) {
                        throw new DukeException("Delete what brah??\n\t" + Command.DELETE.help_text);
                    }
                    // Makes sure that key phrase is a valid int and within range of task list
                    try {
                        int index = Integer.parseInt(input_split[1]) - 1;
                        taskList.getTask(index);
                        return Command.DELETE;
                    }
                    catch (NumberFormatException e) {
                        throw new DukeException("Not a number!\n\t" + Command.DELETE.help_text);
                    }
                    catch (IndexOutOfBoundsException e) {
                        throw new DukeException("Task number does not exist!\n\t" + Command.DELETE.help_text);
                    }
                }
                case "todo": {
                    // Make sure there is key phrase after to record as task
                    if (key_phrases == 1) {
                        throw new DukeException("Todo what brah??\n\t" + Command.TODO.help_text);
                    }
                    return Command.TODO;
                }
                case "deadline": {
                    // Make sure there is key phrase after to record as task
                    if (key_phrases == 1) {
                        throw new DukeException("Deadline for what brah??\n\t" + Command.DEADLINE.help_text);
                    }
                    // Makes sure key phrase after contains /by descriptor to record datetime
                    String[] desc_date = input_split[1].split(" /by ", 2);
                    if (desc_date.length != 2) {
                        throw new DukeException("Deadline when brah??\n\t" + Command.DEADLINE.help_text);
                    }
                    // Make sure date format is correct
                    try {
                        LocalDate.parse(desc_date[1]);
                    }
                    catch (DateTimeParseException e) {
                        throw new DukeException("Follow the gahdam format!!\n\t" + Command.DEADLINE.help_text);
                    }
                    return Command.DEADLINE;
                }
                case "event": {
                    // Make sure there is key phrase after to record as task
                    if (key_phrases == 1) {
                        throw new DukeException("What event brah??\n\t" + Command.EVENT.help_text);
                    }
                    // Makes sure key phrase after contains /at descriptor to record datetime
                    String[] desc_date = input_split[1].split(" /at ", 2);
                    if (desc_date.length != 2) {
                        throw new DukeException("Event when brah??\n\t" + Command.EVENT.help_text);
                    }
                    // Make sure date format is correct
                    try {
                        LocalDate.parse(desc_date[1]);
                    }
                    catch (DateTimeParseException e) {
                        throw new DukeException("Follow the gahdam format!!\n\t" + Command.EVENT.help_text);
                    }
                    return Command.EVENT;
                }
                default: {
                    // If invalid command, show all help text to user
                    String all_help_text = "";
                    for (Command c : Command.values()) {
                        all_help_text = all_help_text + "\t" + c.help_text + "\n";
                    }
                    throw new DukeException("What you sayin brah! You better start makin sense or else...\n" +
                            "*Speak in words Pepper Jack can understand*\n" + all_help_text);
                }
            }
        }
    }

    /**
     *
     * @param str
     * @return String of reply with top and bottom borders
     */
    private static String reply(String str) {
        String y_border = "------------------------------------------------------------\n";
        return y_border + "[PEPPER JACK]\n" + str + "\n" + y_border;
    }

    /**
     *
     * @param t
     * @return String of reply for adding task
     */
    private static String showTasksReply(Command c, String t) {
        String show_task = "\nYou have " + taskList.size() +" task(s) now so get off that crack rock!";
        // If adding new task reply with add task message
        for (Command taskCommand : Command.taskCommands) {
            if (c == taskCommand) {
                return reply("Pepper Jack added this task:\n\t" + t + show_task);
            }
        }
        // Else reply with custom message
        return reply( t + "\nYou have " + taskList.size() +
                " task(s) now so get off that crack rock!");
    }

    /**
     * Starts conversation with Pepper Jack
     */
    private static void startConvo() {
        Scanner sc = new Scanner(System.in);

        while(true) {
            // Get user input
            System.out.print("[YOU] ");
            String user_input = sc.nextLine();

            // Check for valid while parsing user input
            // Check for valid while parsing user input
            try {
                // Validate user input
                Command c = Command.validate(user_input);

                // If command is BYE, end convo
                if (c == Command.BYE) {
                    System.out.print(reply("Pepper Jack love Fraggle Rock!"));
                    break;
                }
                else {
                    // Determine action according to command
                    switch (c) {
                        case LIST: {
                            // Show list
                            String lst_display = "\n";

                            for (int i = 0; i < taskList.size(); i++) {
                                lst_display = lst_display + String.format("\t%d. %s\n", i + 1, taskList.getTask(i));
                            }
                            System.out.print(reply(lst_display));
                            break;
                        }
                        case DONE: {
                            // Mark task as done
                            String desc = user_input.split(" ", 2)[1];
                            int index = Integer.parseInt(desc) - 1;
                            Task t = taskList.getTask(index);
                            t.setDone();
                            System.out.print(reply("Noice! Pepper Jack marked this task as done:\n\t" + t));
                            break;
                        }
                        case DELETE: {
                            // Delete task
                            String desc = user_input.split(" ", 2)[1];
                            int index = Integer.parseInt(desc) - 1;
                            Task t = taskList.removeTask(index);
                            System.out.print(showTasksReply(c, "Aights! Pepper Jack deleted this task:\n\t" + t));
                            break;
                        }
                        case TODO: {
                            // Add new to do
                            String desc = user_input.split(" ", 2)[1];
                            Task t = new Todo(desc);
                            taskList.addTask(t);
                            System.out.print(showTasksReply(c, t.toString()));
                            break;
                        }
                        case DEADLINE: {
                            // Add new deadline
                            String desc_date = user_input.split(" ", 2)[1];
                            Task t = Deadline.build(desc_date);
                            taskList.addTask(t);
                            System.out.print(showTasksReply(c, t.toString()));
                            break;
                        }
                        case EVENT: {
                            // Add new event
                            String desc_date = user_input.split(" ", 2)[1];
                            Task t = Event.build(desc_date);
                            taskList.addTask(t);
                            System.out.print(showTasksReply(c, t.toString()));
                            break;
                        }
                    }
                }
            }
            catch (DukeException e) {
                // Invalid user input
                System.out.print(reply(e.getMessage()));
            }
        }
    }

    public static void main(String[] args) {
        String logo =
                "$$$$$$$\\                                                             $$$$$\\                     $$\\       \n" +
                "$$  __$$\\                                                            \\__$$ |                    $$ |      \n" +
                "$$ |  $$ | $$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\              $$ | $$$$$$\\   $$$$$$$\\ $$ |  $$\\ \n" +
                "$$$$$$$  |$$  __$$\\ $$  __$$\\ $$  __$$\\ $$  __$$\\ $$  __$$\\             $$ | \\____$$\\ $$  _____|$$ | $$  |\n" +
                "$$  ____/ $$$$$$$$ |$$ /  $$ |$$ /  $$ |$$$$$$$$ |$$ |  \\__|      $$\\   $$ | $$$$$$$ |$$ /      $$$$$$  / \n" +
                "$$ |      $$   ____|$$ |  $$ |$$ |  $$ |$$   ____|$$ |            $$ |  $$ |$$  __$$ |$$ |      $$  _$$<  \n" +
                "$$ |      \\$$$$$$$\\ $$$$$$$  |$$$$$$$  |\\$$$$$$$\\ $$ |            \\$$$$$$  |\\$$$$$$$ |\\$$$$$$$\\ $$ | \\$$\\ \n" +
                "\\__|       \\_______|$$  ____/ $$  ____/  \\_______|\\__|             \\______/  \\_______| \\_______|\\__|  \\__|\n" +
                "                    $$ |      $$ |                                                                        \n" +
                "                    $$ |      $$ |                                                                        \n" +
                "                    \\__|      \\__|                                                                        ";
        System.out.println("The name is\n" + logo);
        System.out.print(reply("This Pepper Jack, wassup!"));

        // Load tasks from text file if exists
        Storage.loadTasks(taskList);

        // Start conversation with Pepper Jack
        startConvo();

        // Save tasks
        Storage.saveTasks(taskList);
    }
}
