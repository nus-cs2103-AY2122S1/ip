public class Ui {
    public void showLoadingError() {
        System.out.println("Error encountered when loading data");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Bobby\nWhat can I do for you?\n");
        showLine();
    }

    /**
     * Say Bye and close program
     */
    public void sayBye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!\n");
        showLine();
        System.exit(1);
    }
}
