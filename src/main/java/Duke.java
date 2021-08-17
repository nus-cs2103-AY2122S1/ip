import java.util.Scanner;

//automated testing script closes super fast; still have to manually run the final FC command in terminal
//managed to fix the issue with FC not being found however. Git push issues also fixed.

//most obvious uncaught exceptions are incorrect delete/done commands. fix in the future.
public class Duke {
    private static TaskList store = new TaskList();

    //subroutine for adding tasks to the collection of tasks
    public static void addTask(String descriptor) throws DukeException {
        if (descriptor.startsWith("todo")) {
            descriptor = descriptor.replaceFirst("todo", "");
            if (descriptor.equals("")) {
                throw new DukeException("empty description");
            }
            store.add(new Todo(descriptor.stripLeading()));
        } else if (descriptor.startsWith("deadline")) {
            descriptor = descriptor.replaceFirst("deadline", "");
            if (descriptor.equals("")) {
                throw new DukeException("empty description");
            }
            descriptor = descriptor.replaceFirst("/by ", "(by: ");
            descriptor = descriptor + ")";
            store.add(new Deadline(descriptor.stripLeading()));
        } else if (descriptor.startsWith("event")) {
            descriptor = descriptor.replaceFirst("event", "");
            if (descriptor.equals("")) {
                throw new DukeException("empty description");
            }
            descriptor = descriptor.replaceFirst("/at ", "(at: ");
            descriptor = descriptor + ")";
            store.add(new Event(descriptor.stripLeading()));
        } else {
            //based on logic in Main, should never reach this branch.
            throw new DukeException();
        }

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + store.get(store.size() - 1));
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        System.out.println(
                "░▄░█░░░▄▀▀▀▀▀▄░░░█░▄░\n" +
                "▄▄▀▄░░░█─▀─▀─█░░░▄▀▄▄\n" +
                "░░░░▀▄▒▒▒▒▒▒▒▒▒▄▀░░░░\n" +
                "░░░░░█────▀────█░░░░░\n" +
                "░░░░░█────▀────█░░░░░\n");
        System.out.println("I'm Frosty, your personal task manager! How can I help?");

        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        while(!in.equals("bye")) {
            if (in.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < store.size(); i++) {
                    System.out.println((i + 1) + ". " + store.get(i));
                }
            } else if (in.startsWith("done")) {
                try {
                    String[] temp = in.split(" ");
                    store.get(Integer.parseInt(temp[1]) - 1).setFlag(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + store.get(Integer.parseInt(temp[1]) - 1));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Sorry! Your done command has an invalid index choice");
                } catch (NumberFormatException e) {
                    System.out.println("Sorry! I can't understand the index for your done command");
                }
            } else if (in.startsWith("todo") || in.startsWith("deadline") || in.startsWith("event")) {
                try {
                    addTask(in);
                } catch (DukeException e) {
                    System.out.println("Sorry! Your request caused " + e);
                }
            } else if (in.startsWith("delete")) {
                try {
                    String[] temp = in.split(" ");
                    Task removed = store.remove(Integer.parseInt(temp[1]) - 1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + removed);
                    System.out.println("Now you have " + store.size() + " tasks in the list.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Sorry! Your delete command has an invalid index choice");
                } catch (NumberFormatException e) {
                    System.out.println("Sorry! I can't understand the index for your delete command");
                }
            } else {
                System.out.println("Sorry! I don't know what your request means. Please try again?");
            }
            in = sc.nextLine();
        }

        //after executing "bye", we reach this point where we should save.
        System.out.println("Have a Merry Christmas and a Happy New Year!");
    }
}
