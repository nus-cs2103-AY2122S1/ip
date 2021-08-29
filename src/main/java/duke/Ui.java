package duke;

import duke.command.ByeCommand;
import duke.command.ListCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.TodoCommand;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;

import java.util.Scanner;

public class Ui {

    private static final String LOGO =
            "$$$$$$$\\                                                             $$$$$\\                     $$\\       \n" +
                    "$$  __$$\\                                                            \\__$$ |                    $$ |      \n" +
                    "$$ |  $$ | $$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\              $$ | $$$$$$\\   $$$$$$$\\ $$ |  $$\\ \n" +
                    "$$$$$$$  |$$  __$$\\ $$  __$$\\ $$  __$$\\ $$  __$$\\ $$  __$$\\             $$ | \\____$$\\ $$  _____|$$ | $$  |\n" +
                    "$$  ____/ $$$$$$$$ |$$ /  $$ |$$ /  $$ |$$$$$$$$ |$$ |  \\__|      $$\\   $$ | $$$$$$$ |$$ /      $$$$$$  / \n" +
                    "$$ |      $$   ____|$$ |  $$ |$$ |  $$ |$$   ____|$$ |            $$ |  $$ |$$  __$$ |$$ |      $$  _$$<  \n" +
                    "$$ |      \\$$$$$$$\\ $$$$$$$  |$$$$$$$  |\\$$$$$$$\\ $$ |            \\$$$$$$  |\\$$$$$$$ |\\$$$$$$$\\ $$ | \\$$\\ \n" +
                    "\\__|       \\_______|$$  ____/ $$  ____/  \\_______|\\__|             \\______/  \\_______| \\_______|\\__|  \\__|\n" +
                    "                    $$ |      $$ |                                                                        \n" +
                    "                    $$ |      $$ |                                                                        \n" +
                    "                    \\__|      \\__|                                                                        ";

    private static final String END_TEXT = "Pepper Jack love Fraggle Rock!";

    private static final String[] LIST_OF_COMMAND_USAGE_TEXT = {
            ByeCommand.USAGE_TEXT,
            ListCommand.USAGE_TEXT,
            DoneCommand.USAGE_TEXT,
            DeleteCommand.USAGE_TEXT,
            TodoCommand.USAGE_TEXT,
            DeadlineCommand.USAGE_TEXT,
            EventCommand.USAGE_TEXT,
    };

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        System.out.print("[YOU] ");
        return sc.nextLine();
    }

    public static String formatReply(String str) {
        String y_border = "------------------------------------------------------------\n";
        return y_border + "[PEPPER JACK]\n" + str + "\n" + y_border;
    }

    public void reply(String str) {
        System.out.print(formatReply(str));
    }

    public void showTasksReply(boolean isAdd, String taskDesc, int numOfTasks) {
        String end = "\nYou have " + numOfTasks +" task(s) now so get off that crack rock!";
        // If adding new task reply with add task message
        if (isAdd) {
            System.out.print(formatReply("Pepper Jack added this task:\n\t" + taskDesc + end));
        } else {
            // Else reply with custom message
            System.out.print(formatReply(taskDesc + end));
        }
    }

    public void showWelcome() {
        System.out.println("The name is\n" + LOGO);
        System.out.print(formatReply("This Pepper Jack, wassup!"));
    }

    public void showLoadingError(String errorMessage) {
        System.out.print(formatReply(errorMessage));
    }

    public void showHelp() {
        String all_help_text = "";
        for (String help_text : LIST_OF_COMMAND_USAGE_TEXT) {
            all_help_text = all_help_text + "\t" + help_text + "\n";
        }
        System.out.println(formatReply("What you sayin brah! You better start makin sense or else...\n"
                + "*Speak in words Pepper Jack can understand*\n" + all_help_text));
    }

    public void showBye() {
        reply(END_TEXT);
    }
}
