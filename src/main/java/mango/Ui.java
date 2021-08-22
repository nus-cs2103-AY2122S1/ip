package mango;

public class Ui {
    public Ui() {

    }
    public void greet() {
        String logo = " __  __    ___    _  _     ___     ___\n"
                + "|  \\/  |  /   \\  | \\| |   / __|   / _ \\\n"
                + "| |\\/| |  | - |  | .` |  | (_ |  | (_) |\n"
                + "|_|__|_|  |_|_|  |_|\\_|   \\___|   \\___/\n"
                + "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|\n"
                + "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\n";


        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Mango\nWhat can I do for you?");
    }

    public void echo(String str) {
        System.out.println(str);
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public void run() {

    }

    public void showLoadingError(Exception e) {
        System.out.println("Error encountered when loading data: " + e.getMessage());
    }
}
