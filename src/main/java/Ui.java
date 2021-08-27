public class Ui {

    public void showWelcome() {
        System.out.println("\tHello! I'm Duke. \n\tWhat can I do for you?\n");
    }

    public String readCommand() {
        return "";
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showLine() {
        System.out.println("\t____________________________________________________________\n");
    }
}
