package duke;

/**
 * Constants and variables used for output
 */
public class Ui {
    public static final String LINE = "____________________________________________________________\n";
    public static final String OUTPUT_DISPLAY = "  →   ";
    public static final String OUTPUT_SPACES =  "      ";

    /**
     * Welcome text to display when duke.Duke starts up
     */
    public static void intro() {
        String welcomeText = "When the Duke is sus";

        String mascot ="\n" +
                "       `:ossys/`              \n" +
                "     .yh+-` ``-o/             \n" +
                "     hh         .o.           \n" +
                "     Ns  -+sssyyo:y:          \n" +
                "    `Ms.o/-/+ossym+do         \n" +
                "    -M+s+::/+osssdy.dh.       \n" +
                "    +M:dsoooossshm- `dm-      \n" +
                "    dM./ddhyyhhdy-   `dN-     \n" +
                "   .Nh  `-/+o+:.      .mh     \n" +
                "   +M:                 -No    \n" +
                "   mm                   +M:   \n" +
                "  -Mo                    dd`  \n" +
                "  sM.                    -M+  \n" +
                " `mh                      hm  \n" +
                " :M/                      :M- \n" +
                " oM`                      `Mo \n" +
                " hh                        Ny \n" +
                "`No       ./syyys+-`       Nh \n" +
                "-M:    `/hmy/:--:+dh:      Nh \n" +
                ":M-  `ody:`        :my`   .Ms \n" +
                "-My/ydo.            `ym:  oM- \n" +
                " :++-                 /mddd: ";

        System.out.println(
                welcomeText + '\n' +
                mascot + '\n'
        );

        System.out.println(
                Ui.LINE +
                "Hello! I'm Duke!\n" +
                "What can I do for you?\n" +
                Ui.LINE
        );
    }

    /**
     * Display list of possible commands
     */
    public static void displayHelp() {
        System.out.println("Duke-san welcomes you! Here are a list of the available commands:\n" +
                "_________________________________________________________________________________________\n" +
                "todo [description]\t- Make a new todo event\n" +
                "deadline [description] /by DD/MM/YYYY XXXX\t- Make a new Deadline (Optional time)\n" +
                "event [description] /at DD/MM/YYYY XXXX\t- Make a new Event (Optional time)\n" +
                "-----------------------------------------------------------------------------------------\n" +
                "list\t- List out your events\n" +
                "done [index]\t- Complete task at index specified in list\n" +
                "delete [index]\t- Deletes the task from the list\n" +
                "-----------------------------------------------------------------------------------------\n" +
                "help\t- Display this text\n" +
                "gubbai\t- Exit duke.Duke UwU\n");
    }

}
