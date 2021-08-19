import java.util.Scanner;

/**
 * This class represents the chat bot.
 * @author Nigel Tan
 */

//CHANGE TO CASE SWITCH
//Remove pos from TASKS use the int after done to put inside Response
public class Duke {
    public enum Command {
        LIST("list"),
        DONE("done"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete"),
        OTHER(" ");

        private String text;

        Command(String text) {
            this.text = text;
        }
        public static Command fromString(String text) {
            for (Command c : Command.values()) {
                if (c.text.equalsIgnoreCase(text)) {
                    return c;
                }
            }
            return OTHER;
        }
    }

    public static void main(String[] args) throws Exception {
        final String horizontalLine = "    ____________________________________________________________\n";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        Response response = new Response();
        int position = 0;
        Scanner sc = new Scanner(System.in);
        response.greet();
        String text = sc.nextLine();

        while (!text.equals("bye")) {
            String[] splitWords = text.split(" ", 2);
            String commandWord = splitWords[0];
            Command command =  Command.fromString(commandWord);
            switch (command) {
                case LIST:
                    response.display();
                    break;
                case DONE:
                    int pos = Integer.parseInt(splitWords[1]);
                    response.markDone(pos - 1);
                    break;
                case TODO:
                    try {
                        response.echo(new ToDo(splitWords[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println(horizontalLine +
                                "    :( OOPS! The description of a todo cannot be empty.\n" +
                                horizontalLine);
                    }
                    break;
                case DEADLINE:
                    try {
                        String[] splitBy = splitWords[1].split("/by ", 2);
                        response.echo(new Deadline(splitBy[0], splitBy[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println(horizontalLine +
                                "    :( OOPS! The description or a time of a deadline cannot be empty.\n" +
                                horizontalLine);
                    }
                    break;
                case EVENT:
                    try {
                        String[] splitAt = splitWords[1].split("/at ", 2);
                        response.echo(new Events(splitAt[0], splitAt[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println(horizontalLine +
                                "    :( OOPS! The description or a time of an event cannot be empty.\n" +
                                horizontalLine);
                    }
                    break;
                case DELETE:
                    int pos2 = Integer.parseInt(splitWords[1]);
                    response.delete(pos2 - 1);
                    break;
                case OTHER:
                    System.out.println(horizontalLine +
                            "    :( OOPS! I'm sorry, but I don't know what that means.\n" +
                            horizontalLine);
                    break;
            }
            text = sc.nextLine();
        }
        response.exit();
    }
}
