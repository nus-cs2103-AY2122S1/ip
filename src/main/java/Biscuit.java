import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Biscuit is a Personal Assistant Chatbot that helps a person to keep track of various things
 */
public class Biscuit {
    private static List<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        String logo =
                "████████████████████████████████████████\n"+
                "█▄─▄─▀█▄─▄█─▄▄▄▄█─▄▄▄─█▄─██─▄█▄─▄█─▄─▄─█\n" +
                "██─▄─▀██─██▄▄▄▄─█─███▀██─██─███─████─███\n" +
                "▀▄▄▄▄▀▀▄▄▄▀▄▄▄▄▄▀▄▄▄▄▄▀▀▄▄▄▄▀▀▄▄▄▀▀▄▄▄▀▀\n\n" +

                "      ████████████████████\n" +
                "    ██░░░░░░░░░░░░░░░░░░░░██\n" +
                "  ██░░░░██░░░░░░░░░░░░██░░░░██\n" +
                "  ██░░░░██░░██░░░░██░░██░░░░██\n" +
                "  ██░░████░░░░░░░░░░░░████░░██\n" +
                "    ██  ██░░░░████░░░░██  ██\n" +
                "        ██░░░░░██░░░░░██                  ██\n" +
                "          ██░░░░░░░░██░░████████        ██░░██\n" +
                "        ██░░████████░░░░░░░░░░░░██      ██░░██\n" +
                "      ██░░░░░░░░░░░░░░░░░░░░░░░░░░██  ██░░░░██\n" +
                "      ██░░░░░░░░░░░░░░██░░░░░░░░░░░░██░░░░██\n" +
                "      ██░░░░░░░░░░░░░░██░░░░░░░░░░░░██░░░░██\n" +
                "      ██░░░░░░██░░░░░░██░░░░░░░░░░░░░░░░██\n" +
                "      ██░░░░░░██░░░░░░██░░░░░░░░░░░░░░██\n" +
                "      ██░░░░░░██░░░░░░██░░░░░░░░░░████\n" +
                "████████████████████████████████████████\n";

        String separator = "────────────────────────────────────────";
        System.out.println("Woof from\n" + logo);
        System.out.println("Woof! I'm Biscuit.\nWhat can I do for you?\n" + separator);
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage("data/biscuit.csv");


        try {
            list = storage.load();
        } catch (BiscuitException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Something went wrong, could not load data...");
        }


        boolean isContinue = true;
        while (isContinue) {
            try {
                String input = scanner.nextLine();
                String[] processedInput = input.trim().split("\\s+", 2);
                System.out.println(separator);
                switch (processedInput[0]) {
                    case "todo":
                    case "deadline":
                    case "event":
                        addTask(processedInput);
                        storage.save();
                        break;
                    case "done":
                        doneTask(processedInput);
                        storage.save();
                        break;
                    case "delete":
                        deleteTask(processedInput);
                        storage.save();
                        break;
                    case "list":
                        if (list.isEmpty()) {
                            System.out.println("List is currently empty.");
                        }
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println(i + 1 + ". " + list.get(i));
                        }
                        break;
                    case "bye":
                        System.out.println("Bye! Hope to see you again soon! 8==8");
                        isContinue = false;
                        break;
                    default:
                        throw new BiscuitException("໒(◉ᴥ◉)७ OOPS!!! I'm sorry, but I don't know what that means...");
                }
            } catch (BiscuitException be) {
                System.out.println(be.getMessage());
            } catch (Exception e) {
                System.out.println("Woof! Looks like something went wrong, please try again.");
            } finally {
                System.out.println(separator);
            }
        }
    }

    /**
     * Adds specified task into list
     * @param userInput task user wants to add
     * @throws BiscuitException invalid input by user
     */
    public static void addTask(String[] userInput) throws BiscuitException {
        if (userInput.length == 2) {
            Task task;
            switch (userInput[0]) {
                case "deadline":
                    String[] deadlineData = userInput[1].split("/by ", 2);
                    if (deadlineData.length == 2) {
                        task = new Deadline(deadlineData[0], deadlineData[1]);
                    } else {
                        throw new BiscuitException("໒(◉ᴥ◉)७ OOPS!!! The date/time for deadline cannot be empty.\n" +
                                "If you do not wish to add a date, use todo instead.");
                    }
                    break;
                case "event":
                        String[] eventData = userInput[1].split("/at ", 2);
                        if (eventData.length == 2) {
                            task = new Event(eventData[0], eventData[1]);
                        } else {
                            throw new BiscuitException("໒(◉ᴥ◉)७ OOPS!!! The date/time for event cannot be empty.\n" +
                                    "If you do not wish to add a date, use todo instead.");
                        }
                    break;
                default: // todo task
                    task = new ToDo(userInput[1]);
                    break;
            }
            list.add(task);
            System.out.println("Got it. I've added this task:\n\t" + task
                    + "\nNow you have " + list.size() + " tasks in the list.");
        } else {
            throw new BiscuitException("໒(◉ᴥ◉)७ OOPS!!! The description of " + userInput[0] + " cannot be empty.");
        }
    }

    /**
     * Mark specified task as done
     * @param userInput task user wants to mark as done
     * @throws BiscuitException invalid input by user
     */
    public static void doneTask(String[] userInput) throws BiscuitException {
        if (userInput.length == 2) {
            try {
                Task current = list.get(Integer.parseInt(userInput[1]) - 1);
                current.setDone(true);
                System.out.println("Nice! I've marked this task as done, woof!\n\t" + current);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new BiscuitException("໒(◉ᴥ◉)७ OOPS!!! Please enter a valid number" +
                        (list.size() == 1 ? " of 1" : " from 1 to " + list.size()) + ".");
            }
        } else {
            throw new BiscuitException("໒(◉ᴥ◉)७ OOPS!!! The done task number cannot be empty.");
        }
    }

    /**
     * Delete specified task
     * @param userInput task user wants to delete
     * @throws BiscuitException invalid input by user
     */
    public static void deleteTask(String[] userInput) throws BiscuitException {
        if (userInput.length == 2) {
            try {
                int index = Integer.parseInt(userInput[1]) - 1;
                Task toDelete = list.get(index);
                list.remove(index);
                System.out.println("Noted. I've removed the following task:\n\t" + toDelete);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new BiscuitException("໒(◉ᴥ◉)७ OOPS!!! Please enter a valid number" +
                        (list.size() == 1 ? " of 1" : " from 1 to " + list.size()) + ".");
            }
        } else {
            throw new BiscuitException("໒(◉ᴥ◉)७ OOPS!!! The delete task number cannot be empty.");
        }
    }
}
