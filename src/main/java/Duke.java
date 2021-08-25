import config.Setting;
import ui.Ui;
import parser.Parser;
import processor.Processor;
import storage.Storage;

public class Duke {

    private static final Parser parser = new Parser(new Processor(new Storage(Setting.FILE_DIRECTORY, Setting.FILE_NAME)));

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        Ui.print("Hello from\n" + logo);
        Ui.print("Wait, I'm not Duke. I'm Dub!\nWhat can I do for you?");
        listen();
    }

    public static void listen() {
        while(true) {
            boolean bool = parser.nextLine();
            if (!bool) {
                break;
            }
        }
    }
}
