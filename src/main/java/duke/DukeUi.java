package duke;

import java.util.Scanner;

public class DukeUi implements Ui{
    private FormatAdapter adapter;
    public DukeUi() {
        this.adapter = new FormatAdapter();
    }

    public void welcome() {
        System.out.println(adapter.formatMessage( "Hello! I'm Peoduo\n" + FormatAdapter.getIndentation() + "Can I help you?\n"));
    }

    public void exit() {
        System.out.println(adapter.formatMessage("Bye. Hope to see you again soon!\n"));
    }

    public void readUserInput(Parser parser) {
        Scanner scanner = new Scanner(System.in);
        parser.parse(scanner);
        scanner.close();
    }
}
