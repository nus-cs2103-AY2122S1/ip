import java.util.Scanner;

public class Ui {

    private Scanner input;

    public Ui() {
        this.input = new Scanner(System.in);
    }

    public void processInput(String in) {
        if (in.equals("bye") || in.equals("Bye")) {
            Parser.byeOutput();
            input.close();
        } else if (in.equals("lis   t") || in.equals("List")) {
            Parser.processOutput("list");
        } else if (in.substring(0,4).equals("done") ) {
            Parser.processOutput("done");
        } else if (in.substring(0,6).equals("delete")) {
            Parser.processOutput("delete");
        } else if (in.substring(0,4).equals("todo")) {
            Parser.processOutput("todo");
        } else if (in.substring(0,5).equals("event")) {
            Parser.processOutput("event");
        } else if (in.substring(0,8).equals("deadline")) {
            Parser.processOutput("deadline");
        } else {
            Parser.invalidInputResponse();
        }
    }
}

