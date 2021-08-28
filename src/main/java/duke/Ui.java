package duke;

import java.util.Scanner;

public class Ui {

    private final static String LOGO =
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

    public String formatReply(String str) {
        String y_border = "------------------------------------------------------------\n";
        return y_border + "[PEPPER JACK]\n" + str + "\n" + y_border;
    }

    public void reply(String str) {
        System.out.print(formatReply(str));
    }

    public void showTasksReply(Duke.Command c, String t, int numOfTasks) {
        String show_task = "\nYou have " + numOfTasks +" task(s) now so get off that crack rock!";
        // If adding new task reply with add task message
        for (Duke.Command taskCommand : Duke.Command.taskCommands) {
            if (c == taskCommand) {
                System.out.print(formatReply("Pepper Jack added this task:\n\t" + t + show_task));
            }
        }
        // Else reply with custom message
        System.out.print(formatReply( t + show_task));
    }

    public void showWelcome() {
        System.out.println("The name is\n" + LOGO);
        System.out.print(formatReply("This Pepper Jack, wassup!"));
    }

    public void showLoadingError(String errorMessage) {
        System.out.println(formatReply(errorMessage));
    }
}
