package duke;

/**
 * Ui handles printing messages to UI interface.
 *
 * @author Gabriel Goh
 */
public class Ui {

    // Credits to https://manytools.org/hacker-tools/convert-images-to-ascii-art/go/ and
    // Mafumafu Line stickers
    static final String LOGO = ""
            + "                     .,%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/&%%&(@@@@@,                 \n"
            + "              .(@@@@@@@@@@@@@@@@@@@@@@@&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(&#%@@@@&.                 \n"
            + "         *&@@@@@@@@@@@@@@@@@@@@@@@@@@&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%/@@@@@/                  \n"
            + "     .@@@@@@@@@@&(*((@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&%.                  \n"
            + "      *@@@@@&*(#&%%%#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/&@@@@@@@@@@@@@@@@@@@&&&,                   \n"
            + "       ,@@@@@@(%%%&#@@@@@@@@&@@&@@@@@@@@@@@&@@@@@@@@@@@@#@(@@@@@@@@@@@@@@@@@@@@.                    \n"
            + "        .&@@@@@@*%&(@@@@@@@@@@@@@@@@@@@@@@@/@@@@@@@@@@@%%@@@(@@@@@@@@@@@@@&@@@@.                    \n"
            + "          ,@@@@@@@/%@@@@@@@@@@@@@@@@@@@@@@(&%@@@@@@@@@@(@@@@@@/@@@@@@@@@@@@&@@#@*                   \n"
            + "            *@@&&&&@@@@@@@@@@@@@@@@@@%@@@(@@(@@@@@@@@@&%@@@@@@@@/@@@@@@@@@@#@@@/.%.                 \n"
            + "              ,&&&@@@@@&@@@@@@@@@@@@@@@@&%@@#@@@@@@@@@/@@@@@@@@@@@/@@@@@@@@#&%@@,  ..               \n"
            + "                .%&@@@@#@@@@@@@@@@@@@@@@#@@@&%@@@@@@@%&@@%. ....... #@@@@@&#&& &&                   \n"
            + "                  %@@@@#@@@@@@@@@@@(@@@#@&@@@(@@@@@%@#@@@%**%%***/*@%&@@@@&(&&  (.                  \n"
            + "                  %&@@@@#@@@@@@%@@#.....,@@@@&&@@@%##@@@@@*((((/((/@&&@@@@&(@#   .                  \n"
            + "                 .(*@@@@%&@@@@@, .*//*@*/*@@@@(@(/@/@@@@@@@#*((#@&@@&&@@@&/%&.                      \n"
            + "                 ...%&&@@(@@@@@#@,*/(((((/@@@@@@@@@@@@@@@@@@@@(/#,&(#&%@&&.&                        \n"
            + "                    .&&%&@%@@@@&&@*((&,/@@@@@@@@@@@@@@@@@@@@@@@/#%%&#%.%@...                        \n"
            + "                      *#&%&&(@@@(@&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#/.#                           \n"
            + "                       .%*%&&%(&(*@&&&&&@@@@@@@@@@#%@@@@@@@@@@@@@@@&..,,..                          \n"
            + "\n";
    private static final String BOX_LINE =
            "__________________________________________________________________________________\n";
    private static final String BOX_MIDDLE = "|  Cat:  ";


    private static final String GREET = "Meow-ning!";
    private static final String EXIT = "See you again, meow!";
    private static final String SAVE = "Your list has been saved to DATA/DUKE.TXT";
    private static final String DONE = "Good job, meow! Marked this task as done:\n   ";

    /**
     * Show starting logo and message on boot.
     */
    void showLogo() {
        System.out.println(LOGO);
        print(GREET);
    }

    void showSavedMessage() {
        print(SAVE);
    }

    void showMarkedDoneMessage(Task task) {
        print(DONE + task);
    }

    void showDeletedMessage(Task task, int size) {
        print("Understood, meow! Deleted this task:\n   "
                + task + "\n" + "|  Now you have " + size + " tasks in the list.");
    }

    void showAddedMessage(Task task, int size) {
        print("Meow. I've added this task:\n   "
                + task + "\n" + "|  Now you have " + size + " tasks in the list.");
    }

    void showExitMessage() {
        print(EXIT);
    }

    /**
     * Print fancily in the theme of the chatbot.
     *
     * @param message Message to print
     */
    public void print(String message) {
        System.out.println(BOX_LINE + BOX_MIDDLE + message + "\n" + BOX_LINE);
    }

}
