import java.util.Locale;
import java.util.Scanner;

/**
 * This class represents the chat bot.
 * @author Nigel Tan
 */

//CHANGE TO CASE SWITCH
//Remove pos from TASKS use the int after done to put inside Response
public class Duke {
    public enum Command {
        LIST,
        DONE,
        TODO,
        DEADLINE,
        EVENT
    }

    public static void main(String[] args) {
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
            String commandWord = splitWords[0].toUpperCase();
            Command command =  Command.valueOf(commandWord);
            switch (command) {
                case LIST:
                    response.display();
                    break;
                case DONE:
                    int pos = Integer.parseInt(splitWords[1]);
                    response.markDone(pos - 1);
                    break;
                case TODO:
                    response.echo(new ToDo(splitWords[1]));
                    break;
                case DEADLINE:
                    String[] splitBy = splitWords[1].split("/by ", 2);
                    response.echo(new Deadline(splitBy[0], splitBy[1]));
                    break;
                case EVENT:
                    String[] splitAt = splitWords[1].split("/at ", 2);
                    response.echo(new Events(splitAt[0], splitAt[1]));
                    break;
                default:
                    response.echo(new Task(text));
                    break;
            }
            text = sc.nextLine();
        }
        
        response.exit();
    }
}
