import java.util.Scanner;

public class Ui {

    protected Scanner userInput;

    public Ui() {
        userInput = new Scanner(System.in);
    }

    public String getInput() {
        return userInput.nextLine();
    }

    public void showLoadingError() {
        System.out.println("There was an error getting the input");
    }

}
