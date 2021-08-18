public class Speech {

    private final boolean testing;
    private int emoticonState;
    private final int baseBubbleLimit;
    private final String baseTemplate;
    private int currentBubbleLimit;


    public Speech(boolean testMode, int speechLimit){
        this.testing = testMode;
        this.emoticonState = 0;
        this.baseBubbleLimit = speechLimit;
        this.currentBubbleLimit = 0;
        this.baseTemplate = String.format("%"+(speechLimit+1)+"s"," ").replace(' ', '_');
    }

    /**
     * Welcome msg speech ( To be called only once at the start of DUKE
     */
    public void welcome() {
        String[] dukeWelcome = {"Hello! I'm Duke!" , "What can I do for you"};
        currentBubbleLimit = baseBubbleLimit;
        speak(dukeWelcome);
    }

    public void listMsg(String[] msg, Storage storage) {
        currentBubbleLimit = storage.listMaxLen();
        speak(msg);
    }

    /**
     * Takes in a msg and formats it to the "done" success string
     * @param msg string that contains details of the task
     */
    public void doneMsg(String msg) {
        String[] dukeAdded = {"Nice! I've marked this task as done:", msg};
        currentBubbleLimit = msg.length();
        speak(dukeAdded);
    }

    /**
     * Takes in a String and task and formats it to the task_added success msg
     * @param msg string that contains details of the task
     * @param storage takes in storage instance to check how many task left
     */
    public void taskAdded(String msg, Storage storage) {
        int task_left = storage.task_left();
        String[] dukeAddedTask = {"Got it. I've added this task:", "  " + msg, "Now you have " + task_left + " tasks in the list."};
        currentBubbleLimit = msg.length() + 2;
        speak(dukeAddedTask);
    }

    /**
     * takes in a string and formats it to the error found msg
     * @param error_msg string that contains details of the error
     */
    public void error(String error_msg) {
        String[] dukeError = {error_msg, "Please try again" };
        currentBubbleLimit = dukeError[0].length();
        speak(dukeError);
    }

    /**
     * Goodbye msg ( to be called when user inputs "bye"
     */
    public void goodbye() {
        currentBubbleLimit = baseBubbleLimit;
        speak("Bye. Hope to see you again soon!");
    }


    /**
     * Duke Speech Template
     * @param in takes in a string array and cycle through and printing msg
     */
    public void speak(String[] in) {
            int limit = baseBubbleLimit;
            String base = baseTemplate;
            if (baseBubbleLimit < currentBubbleLimit) {
                limit = currentBubbleLimit;
                base = String.format("%" + (limit + 1) + "s", " ").replace(' ', '_');
            }
            String space = "          ";
            String topBorder = "\n" + space + " " + base + "\n";
            String botBorder = space + "|" + base + "|\n" + space + "|/\n";
            String emoticon = emoticonRand();
            StringBuilder msges = new StringBuilder();
            for( String x : in){
                msges.append(message_format(x, limit));
            }
            System.out.println( topBorder + msges + botBorder + emoticon);
    }

    /**
     * Duke speech template
     * @param in takes in a string and coverts it to a string array to be used on speak(String[])
     */
    public void speak(String in) {
        String[] temp = {in};
        currentBubbleLimit = in.length();
        speak(temp);
    }

    /**
     * Formats the msg to be printed so it fits within duke's speech bubble
     * @param input input to be formatted
     * @param longest maximum spaces
     * @return String that is reformated to fit duke's speech bubble
     */
    private String message_format(String input, int longest) {
        String spaces = "          | ";
        return spaces + String.format("%-"+longest+"s", input) +"|\n";
    }

    /**
     * Adds a simple emoticon to add variety/ personality to the response printed (basically program glitter)
     * @return String containing the emoticon
     */
    private String emoticonRand() {
        String[] emoticon = {"( ﾟ◇ﾟ)/" ,"( ＾◡＾)ノ", "(*°▽°)ノ", "(o^ω^)" , "(✿˘▽˘)"};
        int state = emoticonState;
        emoticonState = (emoticonState+1) % emoticon.length;
        return emoticon[state];
    }


}
