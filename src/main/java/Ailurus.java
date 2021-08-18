import java.util.Scanner;
import java.util.ArrayList;

/**
 * The main task chatbot class for Ailurus Chatbot
 *
 * @author Leeroy Liu
 */
public class Ailurus {
    private static final String CHATBOT = "Ailurus";
    private static final String YOU = "You";
    private static ArrayList<Task> list = new ArrayList<>();

    /**
     * Customized display for chatbot messages
     *
     * @param message display message to be printed
     */
    private static void say(String message) {
        System.out.println(String.format("%s: %s", Ailurus.CHATBOT, message));
    }

    /**
     * Printing out of the list of tasks
     */
    private static void sayList() {
        if (list.size() == 0) {
            throw new AilurusException(AilurusException.Error.EMPTYLIST);
        }
        Ailurus.say("");
        for(int i = 0; i < list.size(); i++) {
            System.out.println(String.format("%d.%s", i + 1, list.get(i).toString()));
        }
    }

    /**
     * Marking a specific task as done
     *
     * @param str String to be converted to integer, representing task number to be marked as done
     */
    private static void done(String str) {
        try {
            int taskNo = Integer.parseInt(str);
            if (taskNo > list.size() || taskNo < 1) {
                throw new AilurusException(AilurusException.Error.NUMBER);
            } else {
                Task task = list.get(taskNo - 1);
                task.markAsDone();
                Ailurus.say(String.format("Nice! I've marked this task as done:\n\t%s", task));
            }
        } catch (NumberFormatException e) {
            throw new AilurusException(AilurusException.Error.NAN);
        }
    }

    /**
     * Adding task to list of tasks
     *
     * @param task Task that has been added
     */
    private static void addTask(Task task) {
        list.add(task);
        Ailurus.say(String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                task.toString(), list.size()));
    }

    /**
     * Deleting task from a list of tasks
     *
     * @param str String to be converted to integer, representing task number to be deleted
     */
    private static void delete(String str) {
        try {
            int taskNo = Integer.parseInt(str);
            if (taskNo > list.size() || taskNo < 1) {
                throw new AilurusException(AilurusException.Error.NUMBER);
            } else {
                Task task = list.get(taskNo - 1);
                list.remove(taskNo - 1);
                Ailurus.say(String.format("Noted. I've removed this task:\n\t%s\nNow you have %d tasks in the list.",
                        task, list.size()));
            }
        } catch (NumberFormatException e) {
            throw new AilurusException(AilurusException.Error.NAN);
        }
    }

    /**
     * Parse message and return string after command
     *
     * @param message message to be parsed
     */
    public static String parseMessage(String message) {
        int index = message.indexOf(" ");
        if (index > 0) {
            return message.substring(index + 1);
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        boolean endChat = false;
        Scanner scanner = new Scanner(System.in);

        Ailurus.say(String.format("Hello! I'm %s. What can I do for you?", Ailurus.CHATBOT));
        while (!endChat) {
            System.out.print(Ailurus.YOU + ": ");
            String input = scanner.nextLine();
            String first = input.split(" ")[0];
            switch (first) {
                case "bye":
                    endChat = true;
                    break;
                case "list":
                    try {
                        Ailurus.sayList();
                    } catch (AilurusException e) {
                        Ailurus.say(e.toString());
                    }
                    break;
                case "done":
                    try {
                        String str = parseMessage(input);
                        Ailurus.done(str);
                    } catch (AilurusException e) {
                        Ailurus.say(e.toString());
                    }
                    break;
                case "todo":
                    try {
                        String todoMessage = parseMessage(input);
                        Ailurus.addTask(new Todo(todoMessage));
                    } catch (AilurusException e) {
                        Ailurus.say(e.toString());
                    }
                    break;
                case "deadline":
                    try {
                        String deadlineMessage = parseMessage(input);
                        Ailurus.addTask(new Deadline(deadlineMessage));
                    } catch (AilurusException e) {
                        Ailurus.say(e.toString());
                    }
                    break;
                case "event":
                    try {
                        String eventMessage = parseMessage(input);
                        Ailurus.addTask(new Event(eventMessage));
                    } catch (AilurusException e) {
                        Ailurus.say(e.toString());
                    }
                    break;
                case "delete":
                    try {
                        String str = parseMessage(input);
                        Ailurus.delete(str);
                    } catch (AilurusException e) {
                        Ailurus.say(e.toString());
                    }
                    break;
                default:
                    Ailurus.say("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        Ailurus.say("Bye. Hope to see you again soon!");
    }
}
