import java.util.*;

public class Duke {

    /** Stores a list of tasks */
    private static ArrayList<Task> lst = new ArrayList<>();

    /** Enum type to differentiate and parse user inputs */
    private enum Command {
        BYE       ("Usage: bye"),
        LIST      ("Usage: list"),
        DONE      ("Usage: done [task number]"),
        TODO      ("Usage: todo [description]"),
        DEADLINE  ("Usage: deadline [description] /by [date and/or time]"),
        EVENT     ("Usage: event [description] /at [date and/or time]");

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
                        lst.get(index);
                        return Command.DONE;
                    }
                    catch (NumberFormatException e) {
                        throw new DukeException("Not a number!\n\tUsage: done [number]");
                    }
                    catch (IndexOutOfBoundsException e) {
                        throw new DukeException("Task number does not exist!\n\tUsage: done [number]");
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
     * @param sum
     * @return String of reply for adding task
     */
    private static String addTaskReply(String t, int sum) {
        return reply("Pepper Jack added this task:\n\t" + t + "\nYou have " + String.valueOf(sum) +
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
                    System.out.print(reply("Pepper Jack loves Fraggle Rock!"));
                    break;
                }
                else {
                    // Determine action according to command
                    switch (c) {
                        case LIST: {
                            // Show list
                            String lst_display = "\n";

                            for (int i = 0; i < lst.size(); i++) {
                                lst_display = lst_display + String.format("\t%d. %s\n", i + 1, lst.get(i));
                            }
                            System.out.print(reply(lst_display));
                            break;
                        }
                        case DONE: {
                            // Mark task as done
                            String desc = user_input.split(" ", 2)[1];
                            int index = Integer.parseInt(desc) - 1;
                            Task t = lst.get(index);
                            t.setDone();
                            System.out.print(reply("Noice! Pepper Jack marked this task as done:\n\t" + t));
                            break;
                        }
                        case TODO: {
                            // Add new to do
                            String desc = user_input.split(" ", 2)[1];
                            Task t = new Todo(desc);
                            lst.add(t);
                            System.out.print(addTaskReply(t.toString(), lst.size()));
                            break;
                        }
                        case DEADLINE: {
                            // Add new deadline
                            String[] desc_date = user_input.split(" ", 2)[1].split(" /by ",2);
                            Task t = new Deadline(desc_date[0], desc_date[1]);
                            lst.add(t);
                            System.out.print(addTaskReply(t.toString(), lst.size()));
                            break;
                        }
                        case EVENT: {
                            // Add new event
                            String[] desc_date = user_input.split(" ", 2)[1].split(" /at ",2);
                            Task t = new Event(desc_date[0], desc_date[1]);
                            lst.add(t);
                            System.out.print(addTaskReply(t.toString(), lst.size()));
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

        startConvo();
    }
}
