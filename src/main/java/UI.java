public class UI {

    public final String indentation = "       ";
    public final String line = indentation + "---------------------------------------------------------------";
    public final String greeting = indentation + "Hello! I'm Duke developed by Feng Zhunyi.\n" + indentation + "What can I do for you?\n";
    public final String logo = indentation + " ____        _        \n"
            + indentation + "|  _ \\ _   _| | _____ \n"
            + indentation + "| | | | | | | |/ / _ \\\n"
            + indentation + "| |_| | |_| |   <  __/\n"
            + indentation + "|____/ \\__,_|_|\\_\\___|\n";
    public final String done_message = indentation + "Nice! I've marked this task as done:";
    public final String remove_message = indentation + "Noted. I've removed this task:";
    public final String bye_message = line + "\n" + indentation + "Bye. Hope to see you again soon!" + "\n" + line;
    public final String no_task_message = line + "\n" + indentation + "Sorry, you do not have this task" + "\n" + line;
    public final String task_num_message = line + "\n" + indentation + "Sorry, please enter a task number bigger than 0" + "\n" + line;
    public final String lack_content_message = line + "\n" + indentation + "OOPS!!! The description or time of a task cannot be empty." + "\n" + line;
    public final String index_message = line + "\n" + indentation + "please follow the format of adding task" + "\n" + line;
    public final String unknown_message = line + "\n" + indentation + "OOPS!!! I'm sorry, but I don't know what that means :-(" + "\n" + line;
    public final String added_message = line + "\n" + indentation + "Got it. I've added this task:" + "\n" + line;
}