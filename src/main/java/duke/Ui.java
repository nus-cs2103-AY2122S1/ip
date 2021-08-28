package duke;

/**
 * Ui class provides methods for user interaction
 */
public class Ui {
    String line = "____________________________________________________________\n";

    /**
     * Shows a welcome text
     */
    public void Start(){
        System.out.println(line + " Hello! I'm Duke\n" + " What can I do for you?\n" + line);
    }

    /**
     * Shows a farewell text
     */
    public void Bye(){
        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }

    /**
     * Shows a response when user uses the 'list' command
     * @param list
     */
    public void list(String list){
        System.out.println(line + list + line);
    }

    /**
     * Shows a response when user uses the 'done' command
     * @param t takes in a task that user marked as done
     */
    public void Done(Task t){
        System.out.println(line + "Nice! I've marked this task as done:\n" + t);
    }

    /**
     * Shows a response when user uses the 'delete' command
     * @param tasks takes in a TaskList
     * @param delete takes in a Task representing the deleted task
     */
    public void Delete(TaskList tasks, Task delete){
        System.out.println(line + "Noted. I've removed this task:\n" + delete +
                "\nNow you have " + tasks.size() + " tasks in the list.\n" + line);
    }

    /**
     * Shows error message for 'done' command
     * when index is out of bound
     */
    public void showDoneError(){
        System.out.println("\n" + line +
                "\n☹ OOPS!!! No such task to be done.\n" + line);
    }

    /**
     * Shows error message for 'delete' command
     * when index is out of bound
     */
    public void showDeleteError(){
        System.out.println("\n" + line +
                "\n☹ OOPS!!! No such task to be deleted.\n" + line);
    }

    /**
     * Shows a response when user uses the 'todo' command
     * @param tasks takes in a TaskList
     * @param todo takes in a Task that user added as todo
     */
    public void todo(TaskList tasks,Task todo){
        System.out.println(line + "Got it. I've added this task:\n" + todo +
                "\nNow you have " + tasks.size() + " tasks in the list.\n" + line);
    }

    /**
     * Shows a response when user uses the 'deadline' command
     * @param tasks takes in a TaskList
     * @param deadline takes in a Task that user added as deadline
     */
    public void deadline(TaskList tasks,Task deadline){
        System.out.println(line + "Got it. I've added this task:\n" + deadline +
                "\nNow you have " + tasks.size() + " tasks in the list.\n" + line);
    }

    /**
     * Shows an error message for 'deadline' command
     * if date is not in yyyy-mm-dd format
     */
    public void showDeadlineError(){
        System.out.println("\n" + line +
                "\n☹ OOPS!!! The time must be in the format YYYY-MM-DD\n" + line);
    }

    /**
     * Shows a response when user uses the 'event' command
     * @param tasks takes in a TaskList
     * @param event takes in a Task that user added as event
     */
    public void event(TaskList tasks,Task event){
        System.out.println(line + "Got it. I've added this task:\n" + event +
                "\nNow you have " + tasks.size() + " tasks in the list.\n" + line);
    }

    /**
     * Shows a respone when user uses the 'find' command
     * @param findList takes in a String representing the results of find
     */
    public void find(String findList){
        System.out.println(line + "Here are the matching tasks in your list:" + findList + line);
    }

    /**
     * Shows an error message when description of task is empty
     * @return
     */
    public String emptyDescriptionError(){
        return "\n" + line + "\n☹ OOPS!!! The description cannot be empty.\n" + line;
    }

    /**
     * Shows an error message when user command is invalid
     */
    public void defaultError(){
        System.out.println("\n" + line +
                "\n☹ OOPS!!! I'm sorry, but I don't know what that means :-(.\n" + line);
    }
}
