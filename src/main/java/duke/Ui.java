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
     * Show message on boot.
     */
    public static String showGreet() {
        return GREET;
    }

    String showSavedMessage() {
        return print(SAVE);
    }

    String showMarkedDoneMessage(Task task) {
        return print(DONE + task);
    }

    String showDeletedMessage(Task task, int size) {
        return print("Understood, meow! Deleted this task:\n   "
                + task + "\n" + "Now you have " + size + " tasks in the list.");
    }

    String showAddedMessage(Task task, int size) {
        return print("Meow. I've added this task:\n   "
                + task + "\n" + "Now you have " + size + " tasks in the list.");
    }

    String showLoadedMessage() {
        return print("Meow. Your tasks have been loaded.");
    }

    String showExitMessage() {
        return print(EXIT);
    }

    /**
     * Returns printable string fancily in the theme of the chatbot.
     *
     * @param message Message to print
     * @return String to print
     */
    public String print(String message) {
        return message;
    }

}
