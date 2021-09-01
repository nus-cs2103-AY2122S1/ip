package duke.ui;

import java.util.Scanner;

public class Ui {

    private static final String tab = "      ";
    private static final String line = "------------------------------------------------------------";
    private Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        String necroLogo =
                " ___    _                \n"
                        + "|   \\  | |   _____     ___    _  __     _____  \n"
                        + "| |\\ \\ | |  / __  \\  /   _|  | v __|  /  ___  \\ \n"
                        + "| | \\ \\| | |    __/  |  <_   |  /    |   |_|   |\n"
                        + "|_|  \\___|  \\_____|  \\____|  |__|     \\  ___  / \n";
        System.out.println("Hello from\n" + necroLogo);
        textBox("Hello. My name is Necro.",
                "What do you want me to do for you on this horrible day?");
    }

    public void showLoadingError() {
        textBox("No such file exists. Creating one for you now. You're welcome.");
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }

    public void showEmptyList() {
        textBox("There are currently no tasks, fool.");
    }

    public void showAdd(String output, int listSize) {
        textBox("Fine. I've added this meaningless task to your list: ",
                " --> " + output,
                "Satisfied now? You have " + listSize + " items in your list. ");
    }

    public void showComplete(String taskString) {
        textBox("Wow. Congratulations. You have completed the following task:",
                taskString,
                "Are you happy now?");
    }

    public void showDelete(String taskString) {
        textBox("Since you are so lazy, I've helped you delete this task:",
                taskString,
                "Go do something useful with your life.");
    }

    public void showGoodbye() {
        textBox("Farewell, may we never meet again.");
    }

    public void textBox(String... messages) {
        System.out.println(tab + line);
        for (String message : messages) {
            System.out.println(tab + " " + message);
        }
        System.out.println(tab + line);
    }

}
