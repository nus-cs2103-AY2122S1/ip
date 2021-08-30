public enum PrintType {
    GREETING_LINES("Hello! I'm Duke\nWhat can I do for you?"),
    BYE_LINE("Bye. Hope to see you again soon!"),
    TASK_DONE_LINE("Nice! I've marked this task as done:\n "),
    TASK_DELETED_LINE("Noted. I've removed this task:"),
    LIST_INTRO_LINE("Here are the tasks in your list"),
    NOT_RECOGNISED_LINE(":( Oh no! Command not recognized"),
    DONE_FORMAT_LINE(":( Oh no! Delete must follow the format: delete <index>"),
    DELETED_FORMAT_LINE(":( Oh no! Delete must follow the format: delete <index>"),
    OUT_OF_RANGE_LINE(":( Oh no! The index given is out of the range of the number of tasks."),
    COMPLETED_LINE(":( Oh no! The task has already been completed."),
    TODO_LINE(":( Oh no! Description of todo cannot be empty"),
    DEADLINE_LINE(":( Oh no! Deadline must follow the format <description> /by <time/date> "),
    EVENT_LINE(":( Oh no! Event must follow the format <description> /at <time/date> ");

    public final String str;

    PrintType(String str) {
        this.str = str;
    }

    public String getPrintType() {
        return str;
    }

}
