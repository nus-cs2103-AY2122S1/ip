import java.util.Random;

public class Speech {

    static boolean testing = false;

    /**
     * Sets the speech to output simple of formatted
     */
    public static void testMode(boolean mode){
        testing = mode;
    }

    /**
     * Welcome msg speech ( To be called only once at the start of DUKE
     */
    public static void welcome() {
        String[] dukeWelcome = {"Hello! I'm Duke!" , "What can I do for you"};
        speak(dukeWelcome);
    }

    /**
     * Takes in a msg and formats it to the "done" success string
     * @param msg
     */
    public static void done_msg(String msg) {

        String[] dukeAdded = {"Nice! I've marked this task as done:", msg};
        speak(dukeAdded);
    }

    /**
     * Takes in a String and task and formats it to the task_added success msg
     * @param msg
     * @param task_left
     */
    public static void task_added(String msg, int task_left) {
        String[] dukeAddedTask = {"Got it. I've added this task:", "  " + msg, "Now you have " + task_left + " tasks in the list."};
        speak(dukeAddedTask);
    }

    /**
     * takes in a string and formats it to the error found msg
     * @param error_msg
     */
    public static void error(String error_msg) {
        String[] dukeError = {"Hmm, i dont quite understand you", "I think you made " + error_msg };
        speak(dukeError);
    }

    /**
     * Goodbye msg ( to be called when user inputs "bye"
     */
    public static void goodbye() {
        speak("Bye. Hope to see you again soon!");
    }


    /**
     * Duke Speech Template
     * @param in takes in a string array and cycle through and printing msg
     */
    public static void speak(String[] in) {
        if (!testing){
            String topBorder = "          ___________________________________________________\n";
            String botBorder = "          | ________________________________________________|\n" + "          |/\n";
            String emoticon = emoticonRand();
            String msges = "";
            for( String x : in){
                msges += message_format(x, 48);
            }
            System.out.println( topBorder + msges + botBorder + emoticon);
        } else {
            for(String x : in){
                System.out.println(x);
            }
        }

    }

    /**
     * Duke speech template
     * @param in takes in a string and coverts it to a string array to be used on speak(String[])
     */
    public static void speak(String in) {
        String[] temp = {in};
        speak(temp);
    }

    /**
     * Formats the msg to be printed so it fits within duke's speech bubble
     * @param in
     * @param longest
     * @return
     */
    private static String message_format(String in, int longest) {
        String spaces = "          | ";
        String msg = spaces + String.format("%-"+longest+"s", in) +"|\n";
        return msg;
    }

    /**
     * Adds a simple emoticon to add variety/ personality to the response printed (basically program glitter)
     * @return String containing the emoticon
     */
    private static String emoticonRand() {
        String[] emoticon = {"(ﾍﾟ◇ﾟ)」" ,"( ＾◡＾)っ", "(*°▽°)ノ", "(o^ω^)" , "(✿˘▽˘)"};
        int rando = new Random().nextInt(emoticon.length-1);
        return emoticon[rando];
    }


}
