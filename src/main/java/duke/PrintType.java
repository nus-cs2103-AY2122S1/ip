package duke;

public enum PrintType {
    GREETING_LINES("Hello! I'm Duke\nWhat can I do for you?"),
    BYE_LINE("Bye. Hope to see you again soon!"),
    TASK_ADDED_LINE("Got it. I've added this task:"),
    TASK_DONE_LINE("Nice! I've marked this task as done: "),
    TASK_DELETED_LINE("Noted. I've removed this task:"),
    LIST_INTRO_LINE("Here are the tasks in your list"),
    NOT_RECOGNISED_LINE(":( Oh no! Command not recognized"),
    FIND_INTRO_LINE("Here are the matching tasks in your list:");

    public final String str;

    PrintType(String str) {
        this.str = str;
    }

    public String getPrintType() {
        return str;
    }

}
