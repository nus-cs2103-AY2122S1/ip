import java.util.Scanner;

public class Ui {

    private static final String LINE = "----------------------------------------------";

    private Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public void greeting() {
        // Credits to http://allaboutfrogs.org/gallery/frogstuff/ascii.html
        // for the frog ASCII text art!
        String frog =
                        "    _____\n" +
                        "   /     \\______\n" +
                        "  | o     |     \\____\n" +
                        "  /\\_____/           \\___\n" +
                        " /                       \\\n" +
                        "|_______/                 \\\n" +
                        "  \\______   _       ___    \\\n" +
                        "        /\\_//      /   \\    |\n" +
                        "       // //______/    /___/\n" +
                        "      /\\/\\/\\      \\   / \\ \\\n" +
                        "                    \\ \\   \\ \\\n" +
                        "                      \\ \\   \\ \\\n" +
                        "                       \\ \\  /\\/\\\n" +
                        "                       /\\/\\\n";
        String greeting = "I am Jo the Frog! RIBBIT! \n";
        System.out.println(frog + greeting + "How may I help you?\n" + LINE);
    }

    public void goodbye() {
        System.out.println("See you again in my frog hole! RIBBIT!");
        in.close();
    }

    public String readCommand() throws DukeException {
        String input = in.nextLine();
        try {
            DukeException.checkInput(input.trim());
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
        return input;
    }

    public void showLoadingError() {
        System.out.println("File not detected, new file will be created!");
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showError(String msg) {
        System.out.println("ERROR: " + msg);
    }

    public void doneMessage(TaskList tasks, int index) {
        System.out.println("You have swallowed that pesky fly! RIBBIT!");
        System.out.println("  " + tasks.get(index).toString());
    }

    public void deleteMessage(TaskList tasks, int index) {
        System.out.println("Rotten flies deserve to die!");
        System.out.println("  " + tasks.get(index).toString());
        System.out.println("Now you have " + tasks.size() + " flies to eat! RIBBIT!");
    }

    public void listMessage(TaskList tasks) {
        System.out.println("Here is your menu for today:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i).toString());
        }
    }

    public void addTaskMessage(TaskList tasks) {
        System.out.println("A fly has been added to the menu:");
        System.out.println("  " + tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " flies to eat! RIBBIT!");
    }
}
