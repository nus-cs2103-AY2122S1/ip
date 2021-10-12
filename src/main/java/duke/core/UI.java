package duke.core;

/**
 * Message and ui for duke
 */
public class UI {

    public final String indentation = "       ";
    public final String line = indentation + "---------------------------------------------------------------";
    public final String greeting = indentation
            + "Hello! I'm Duke developed by Feng Zhunyi.\n" + indentation + "What can I do for you?\n";
    public final String logo = indentation + " ____        _        \n"
            + indentation + "|  _ \\ _   _| | _____ \n"
            + indentation + "| | | | | | | |/ / _ \\\n"
            + indentation + "| |_| | |_| |   <  __/\n"
            + indentation + "|____/ \\__,_|_|\\_\\___|\n";
    public final String done_message = indentation + "Nice! I've marked this task as done:";
    public final String remove_message = indentation + "Noted. I've removed this task:";
    public final String bye_message = indentation + "Bye. Hope to see you again soon!" + "\n";
    public final String no_task_message = indentation + "Sorry, you do not have this task" + "\n";
    public final String task_num_message = indentation + "Sorry, please enter a task number bigger than 0" + "\n";
    public final String lack_content_message = indentation + "OOPS!!! The description or time of a task cannot be empty." + "\n";
    public final String index_message = indentation
            + "please follow the format of adding task" + "\n";
    public final String unknown_message = indentation
            + "OOPS!!! I'm sorry, but I don't know what that means :-(" + "\n";
    public final String added_message = indentation
            + "Got it. I've added this task:" + "\n";
    public final String reading_file_message = indentation + "Reading your tasks from record : )";
    public final String creating_file_message = indentation + "Creating a file to store your tasks : )";
    public final String wrong_message = index_message + "OOPS!! Something wrong!";
}
