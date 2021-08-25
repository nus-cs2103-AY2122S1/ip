package Duke.Ui;

import Duke.Errors.DukeError;


import java.util.Scanner;

public class Ui {

    public void showWelcome() {
        System.out.println("Henlo, Duke here! How can I be of assistance?");
    }

    public void showError(DukeError e) {
        System.out.println(e);
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }


}
