package ui;

import ui.message.ExitMessage;
import ui.message.GreetMessage;
import ui.message.Message;

import java.util.Scanner;

public class Ui {
    Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        GreetMessage message = new GreetMessage("Hello! I'm Duke, what shall we do today?");
        message.print();
    }

    public void showGoodbye() {
        ExitMessage message = new ExitMessage("Bye, see you again");
        message.print();
    }

    public void showMessage(Message message) {
        message.print();
    }

    public String readInputMessage() {
        return this.scanner.nextLine();
    }
}
