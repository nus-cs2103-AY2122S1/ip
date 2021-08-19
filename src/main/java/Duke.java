import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Duke {

    //List of enum keywords
    enum Keywords {
        list,
        bye,
        todo,
        deadline,
        event,
        done,
        delete,
        error,
    }

    //Logo
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Nullary Function that prints a divider when called
     */
    static void printDivider() {
        String divider = "____________________________________________________________";
        System.out.println(divider);
    }

    /**
     * Initial Greeting from Duke
     * Nullary Function that prints the intro message when called
     */
    static void greetings() {
        System.out.println(logo);
        printDivider();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        printDivider();
    }

    /**
     * Goodbye from Duke
     * Nullary Function that prints a closing message when called
     */
    static void close() {
        printDivider();
        System.out.println("Bye. Hope to see you again soon!\n");
        printDivider();
    }

    /**
     * Unary Function that prints list of task
     * @param lst List of tasks to print
     */
    static void printList(List<Task> lst) {
        printDivider();
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < lst.size(); i++) {
            Task task = lst.get(i);
            System.out.println((i+1) + "." + task.toString());
        }
        printDivider();
    }

    /**
     * Unary Function that marks task as Done and prints a confirmation message
     * @param task Task that is marked as complete
    */
    static void completeTask(Task task) {
        task.markAsDone();

        printDivider();
        System.out.println("Nice! I've marked this task as done:"
                            + "\n\t" + task.toString());
        printDivider();
    }

    /**
     * Binary Function that deletes a task and prints a confirmation message
     * @param index  index number of the task to be removed
     * @param lst Target list of tasks to have a task removed
     */
    static void removeTask(List<Task> lst, int index) {
        Task temp = lst.get(index);
        lst.remove(index);

        printDivider();
        System.out.println("Noted. I've removed this task: \n"
                + "\t" + temp.toString() + "\n"
                + "Now you have " + lst.size() + " tasks in the list.");
        printDivider();
    }

    /**
     * Unary Function that prints the most recently added Task
     * @param lst List of Task that was added to
     */
    static void printTask(List<Task> lst) {
        int lastItem = lst.size() - 1;
        printDivider();
        System.out.println("Got it. I've added this task: \n"
                + "\t" + lst.get(lastItem).toString() + "\n"
        + "Now you have " + lst.size() + " tasks in the list.");
        printDivider();
    }

    /**
     * Checks if String is an int
     * @param str Target string to check
     */
    static boolean checkForInt(String str) {
        try {
            int test = parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Binary Function that processes String into separate Desc and Time
     * @param divider The pivot string to divide the input (\by or \at)
     * @param input Array of Strings containing individual words that was input
     * @return Returns a list of 2 Strings,
     *         index 0 contains description,
     *         index 1 contains the time of the event or the deadline
     */
    static List<String> processDesc(String divider, String[] input) {
        List<String> list = Arrays.asList(input);
        int index = list.indexOf(divider);
        String desc = "";
        String time = "";
        for (int i = 1; i < index; i++) {
            desc += input[i] + " ";
        }
        for (int i = index + 1; i < input.length; i++) {
            time += input[i] + " ";
        }
        List<String> temp = new ArrayList<>();
        temp.add(desc.trim());
        temp.add(time.trim());
        return temp;
    }


    public static void main(String[] args) {
        List<Task> list = new ArrayList<>();
        greetings();
        Scanner userInput = new Scanner(System.in);

        //Waits for userInput
        while (true) {
            try {
                String input = userInput.nextLine();
                //Processed input
                String[] words = input.split(" ");
                String desc = input;
                String time = "";

                //Switch variables
                Keywords key = Keywords.error;
                int index = 0;

                //Logic that looks for keywords
                if (input.equals("bye")) {
                    key = Keywords.bye;
                } else if (input.equals("list")) {
                    key = Keywords.list;
                } else if (words.length > 1) {
                    String temp = words[0];
                    if (temp.equals("done")) {
                        if (checkForInt(words[1])) {
                            index = parseInt(words[1]) - 1;
                            if (index < 0) {
                                throw new DukeException("!!! Please input a number greater than 0 !!!");
                            }
                            key = Keywords.done;
                        }
                    } else if (temp.equals("delete")) {
                        if (checkForInt(words[1])) {
                            index = parseInt(words[1]) - 1;
                            if (index < 0) {
                                throw new DukeException("!!! Please input a number greater than 0 !!!");
                            }
                            key = Keywords.delete;
                        }
                    } else if (temp.equals("todo")) {
                        key = Keywords.todo;
                        desc = input.replaceFirst("todo ", "");
                    } else if (temp.equals("deadline")) {
                        key = Keywords.deadline;
                        List<String> postFilter = processDesc("/by", words);
                        desc = postFilter.get(0);
                        time = postFilter.get(1);
                        if (time == "") {
                            throw new DukeException("!!! The date of a deadline cannot be empty. Use /by to input date!!!");
                        } else if (desc == "") {
                            throw new DukeException("!!! Input the description then use /by to input date !!!");
                        }

                    } else if (temp.equals("event")) {
                        key = Keywords.event;
                        List<String> postFilter = processDesc("/at", words);
                        desc = postFilter.get(0);
                        time = postFilter.get(1);
                        if (time == "") {
                            throw new DukeException("!!! The date of a event cannot be empty. Use /at to input date !!!");
                        } else if (desc == "") {
                            throw new DukeException("!!! Input the description then use /at to input date !!!");
                        }
                    }
                } else {
                    //Error Handling (Can be improved)
                    if (input.equals("todo") || input.equals("deadline") || input.equals("event")) {
                        throw new DukeException("!!! The description cannot be empty. !!!");
                    }
                }

                switch (key) {
                    case list:
                        printList(list);
                        break;
                    case todo:
                        list.add(new ToDo(desc));
                        printTask(list);
                        break;
                    case deadline:
                        list.add(new Deadline(desc, time));
                        printTask(list);
                        break;
                    case event:
                        list.add(new Event(desc, time));
                        printTask(list);
                        break;
                    case done:
                        if (index >= list.size()) {
                            throw new DukeException("!!! The number you input exceeds the size of the list !!!");
                        }
                        completeTask(list.get(index));
                        break;
                    case delete:
                        if (index >= list.size()) {
                            throw new DukeException("!!! The number you input exceeds the size of the list !!!");
                        }
                        removeTask(list, index);
                        break;
                    case bye:
                        close();
                        System.exit(0);
                        break;
                    case error:
                        throw new DukeException("!!! I'm sorry, but I don't know what that means. !!!");
                }
            } catch (DukeException ex) {
                printDivider();
                System.out.println(ex.getMessage());
                printDivider();
            }
        }
    }
}
