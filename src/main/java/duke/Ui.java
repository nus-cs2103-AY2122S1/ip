package duke;

public class Ui {
    public Ui() {

    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm duke.Duke\nWhat can I do for you?\n" + logo);
    }

    public void showLine() {
        System.out.println("_____________________________________________");
    }

    public String showBye() {
        return ("Bye. Hope to see you again soon!");
    }


}
