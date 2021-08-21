package duke;

public class Ui {

    public void reply(String content) {
        // wrap the reply in a divider
        String divider = "    ____________________________________________________________";
        System.out.println(divider + "\n" + content + divider);
    }

    public void hi() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greeting = "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" ;
        reply(greeting);
    }

    public void bye() {
        String message = "     Bye. Hope to see you again soon!\n";
        reply(message);
    }

    public void add(Task t, int size) {
        String message = "     Got it. I've added this task: \n" +
                String.format("       %s\n", t) +
                String.format("     Now you have %d tasks in the list.\n", size);
        reply(message);
    }

    public void done(Task t) {
        String message = String.format("     Nice! I've marked this task as done: \n" +
                "       %s\n", t);
        reply(message);
    }

    public void delete(Task t, int size) {
        String message = "     Noted. I've removed this task: \n" +
                String.format("       %s\n", t) +
                String.format("     Now you have %d tasks in the list.\n", size);
        reply(message);
    }
}
