public class Ui {
    private TaskList tasklist;

    public Ui(TaskList tl) {
        tasklist = tl;
    }

    public void init() {
        System.out.println(
                        "░▄░█░░░▄▀▀▀▀▀▄░░░█░▄░\n" +
                        "▄▄▀▄░░░█─▀─▀─█░░░▄▀▄▄\n" +
                        "░░░░▀▄▒▒▒▒▒▒▒▒▒▄▀░░░░\n" +
                        "░░░░░█────▀────█░░░░░\n" +
                        "░░░░░█────▀────█░░░░░\n");
        System.out.println("I'm Frosty, your personal task manager! How can I help?");
    }

    public void displayList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasklist.size(); i++) {
            System.out.println((i + 1) + ". " + tasklist.get(i));
        }
    }

    public void notifySuccessfulAdd() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasklist.get(tasklist.size() - 1));
        System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
    }


}
