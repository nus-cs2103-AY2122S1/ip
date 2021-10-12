package duke.core;

/**
 * Message and ui for duke
 */
public class UI {

    public final String indentation = "       ";
    public final String greeting = indentation
            + "Hello! I'm Duke developed by Feng Zhunyi.\n" + indentation + "What can I do for you?\n";
    public final String logo = indentation + " ____        _        \n"
            + indentation + "|  _ \\ _   _| | _____ \n"
            + indentation + "| | | | | | | |/ / _ \\\n"
            + indentation + "| |_| | |_| |   <  __/\n"
            + indentation + "|____/ \\__,_|_|\\_\\___|\n";
    public final String doneMessage = indentation + "Nice! I've marked this task as done:";
    public final String byeMessage = indentation + "Bye. Hope to see you again soon!" + "\n";
    public final String noTaskMessage = indentation + "Sorry, you do not have this task" + "\n";
    public final String taskNumMessage = indentation + "Sorry, please enter a task number bigger than 0" + "\n";
    public final String lackContentMessage = indentation
            + "OOPS!!! The description or time of a task cannot be empty." + "\n";
    public final String indexMessage = indentation
            + "please follow the format of adding task" + "\n";
    public final String unknownMessage = indentation
            + "OOPS!!! I'm sorry, but I don't know what that means :-(" + "\n";
    public final String addedMessage = indentation
            + "Got it. I've added this task:" + "\n";
    public final String readingFileMessage = indentation + "Reading your tasks from record : )";
    public final String creatingFileMessage = indentation + "Creating a file to store your tasks : )";
    public final String wrongMessage = indexMessage + "OOPS!! Something wrong!";
    public final String keywordNotFound = indentation + "Sorry, we could not find any task contains the keyword";
}
