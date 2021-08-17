import java.util.Scanner;

//automated testing script closes super fast; still have to manually run the final FC command in terminal
//managed to fix the issue with FC not being found however. Git push issues also fixed.

//most obvious uncaught exceptions are incorrect delete/done commands. fix in the future.
public class Duke {
    private static TaskList tasklist = new TaskList();
    private static Ui ui = new Ui();
    private Storage storage;

    //subroutine for adding tasks to the collection of tasks
    //shift this to TaskList.java in the future, KIV. I forgot when i initially created the class.
    public static void addTask(String descriptor) throws DukeException {
        if (descriptor.startsWith("todo")) {
            descriptor = descriptor.replaceFirst("todo", "");
            if (descriptor.equals("")) {
                throw new DukeException("empty description");
            }
            tasklist.add(new Todo(descriptor.stripLeading()));
        } else if (descriptor.startsWith("deadline")) {
            descriptor = descriptor.replaceFirst("deadline", "");
            if (descriptor.equals("")) {
                throw new DukeException("empty description");
            }
            tasklist.add(new Deadline(descriptor.stripLeading()));
        } else if (descriptor.startsWith("event")) {
            descriptor = descriptor.replaceFirst("event", "");
            if (descriptor.equals("")) {
                throw new DukeException("empty description");
            }
            tasklist.add(new Event(descriptor.stripLeading()));
        } else {
            //based on logic in Main, should never reach this branch.
            throw new DukeException();
        }

        ui.notifySuccessfulAdd(tasklist);
    }

    public static void main(String[] args) {
        ui.init();
        Storage storage = new Storage("frosty.txt");
        storage.load(tasklist);

        String in = ui.readCommand();

        while(!in.equals("bye")) {
            if (in.equals("list")) {
                ui.displayList(tasklist);
            } else if (in.startsWith("done")) {
                try {
                    String[] temp = in.split(" ");
                    tasklist.get(Integer.parseInt(temp[1]) - 1).setFlag(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasklist.get(Integer.parseInt(temp[1]) - 1));
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
                    Task removed = tasklist.remove(Integer.parseInt(temp[1]) - 1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + removed);
                    System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Sorry! Your delete command has an invalid index choice");
                } catch (NumberFormatException e) {
                    System.out.println("Sorry! I can't understand the index for your delete command");
                }
            } else {
                System.out.println("Sorry! I don't know what your request means. Please try again?");
            }
            in = ui.readCommand();
        }

        //after user keys in bye, save should happen here
        storage.save(tasklist);
        System.out.println("Have a Merry Christmas and a Happy New Year!");
    }
}
