/**
 * @author Hang Zelin
 *
 * @description the Ui Part of Duke Programme. This programme mainly deals with all the user interactions.
 * It will print out the information for each type of operation execution. And it will also accept users'
 * input for each round of task execution.
 *
 */
package Duke.UiManager;

import Duke.Command.Parser;
import Duke.Excpetions.DukeException;
import Duke.Task.TaskList;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    static String line = "____________________________________________________________";


    /**
     * @auther Hang Zelin
     *
     * @description Print Hello Message to users.
     *
     * @param
     * @return void
     */
    public void HelloMessage() {
        String Hello_message = "Hello! I'm Duke\n" +
                "What can I do for you?\n";

        System.out.println(line + "\n" + Hello_message + line);
    }

    /**
     * @auther Hang Zelin
     *
     * @description Print Goodbye Message to users.
     *
     * @param
     * @return void
     */
    public void GoodbyeMessage() {
        String Goodbye_message = "Bye. Hope to see you again soon!";
        System.out.println(Goodbye_message);
    }

    /**
     * @auther Hang Zelin
     *
     * @description Print all the tasks in a given list.
     *
     * @param tasks
     * @return void
     */
    public void PrintList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i).getTaskInfo());
        }
    }

    /**
     * @auther Hang Zelin
     *
     * @description Print the Ui info for the MarkDone method in Tasklists.
     *
     * @param info
     * @return void
     */
    public void MarkDone(String info) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + info);
    }

    /**
     * @auther Hang Zelin
     *
     * @description Print the Ui info for the Delete method in Tasklists.
     *
     * @param info
     * @param size
     * @return void
     */
    public void Delete(String info, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + info);
        System.out.println("Now you have " + size + " tasks in the list.");
    }


    /**
     * @auther Hang Zelin
     *
     * @description Print the Ui info for the add method in Tasklists.
     *
     * @param info
     * @param size
     * @return void
     */
    public void add(String info, int size) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(" " + info);
        System.out.println("Now you have " + size + "" +
                " tasks in the list.");
    }


    /**
     * @auther Hang Zelin
     *
     * @description Print a line for users.
     *
     * @param
     * @return void
     */
    public void PrintAline() {
        System.out.println(line);
    }

    /**
     * @auther Hang Zelin
     *
     * @description Print the Ui info for the getSpecificEventOnTime method in Tasklists.
     *
     * @param
     * @return void
     */
    public void getSpecificDateEvent() {
        System.out.println("Here are all the tasks taking place on the date you give me:");
    }


    /**
     * @author Hang Zelin
     *
     * @description Return the line of input from users.
     *
     * @param
     * @return String
     */
    public String getInput() {
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }

    /**
     * @author Hang Zelin
     *
     * @description Return the key 4 information from users' input encapsulated in a ArrayList<String></String>.
     * They are: tasktype, task, time, index. They will be useful when executing in Duke programme.
     *
     * @param
     * @return ArrayList<String></String>
     * @throws DukeException
     */
    public ArrayList<String> ARoundOfInput() throws DukeException {
        System.out.println();
        Parser p;
        ArrayList<String> Info = new ArrayList<>();
        String Message;

        Message = getInput();
        p = new Parser(Message);

        Info.add(p.getOperationType());
        Info.add(p.getTask());
        Info.add(p.getTime());
        Info.add(p.getIndex().toString());

        return Info;
    }

    /**
     * @author Hang Zelin
     *
     * @description Show the Loading Error Text.
     *
     * @param
     * @return void
     */
    public void showLoadingError() {
        System.out.println("Cannot Load From Data.");
    }

    /**
     * @author Hang Zelin
     *
     * @description Show the Saving Error Text.
     *
     * @param
     * @return void
     */
    public void showSavingError() {
        System.out.println("Cannot Save the Data.");
    }
}
