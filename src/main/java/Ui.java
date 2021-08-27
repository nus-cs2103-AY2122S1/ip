public class Ui {
    public Ui() {

    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n" + logo);
    }

    public void showLine() {
        System.out.println("_____________________________________________");
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }


}
