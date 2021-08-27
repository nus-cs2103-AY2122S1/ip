package duke.util;

import duke.taskTypes.Task;

import java.util.Scanner;

/**
 * Interacts with the user through input and output methods
 */
public class Ui {

    private int emoticonState;
    private final int baseBubbleLimit;
    private final String baseTemplate;
    private int currentBubbleLimit;
    private final Scanner scan;

    /**
     * Basic Constructor
     * @param speechLimit
     */
    public Ui(int speechLimit) {

        this.emoticonState = 0;
        this.baseBubbleLimit = speechLimit;
        this.baseTemplate = String.format("%"+(speechLimit+1)+"s"," ").replace(' ', '_');
        this.currentBubbleLimit = 0;
        this.scan = new Scanner(System.in);
    }

    /**
     * Takes in the user input
     * @return String User input
     */
    public String userInput() {
        System.out.print("Say something to Duke: ");
        return scan.nextLine();
    }

    /**
     * Formats the welcome message
     */
    public void welcome() {
        String[] dukeWelcome = {"Hello! I'm Duke!" , "What can I do for you"};
        currentBubbleLimit = baseBubbleLimit;
        speak(dukeWelcome);
    }

    /**
     * Formats list message
     * @param msg
     * @param taskList
     */
    public void listMsg(String[] msg, TaskList taskList) {
        currentBubbleLimit = taskList.listMaxLen();
        speak(msg);
    }

    /**
     * Formats done message
     * @param task
     */
    public void doneMsg(Task task) {
        String msg = task.toString();
        String[] dukeAdded = {"Nice! I've marked this task as done:", msg};
        currentBubbleLimit = msg.length();
        speak(dukeAdded);
    }

    /**
     * Formats task added message
     * @param task
     * @param taskList
     */
    public void taskAdded(Task task, TaskList taskList) {
        String msg = task.toString();
        int task_left = taskList.taskLeft();
        String[] dukeAddedTask = {"Got it. I've added this task:", "  " + msg, "Now you have " + task_left + " tasks in the list."};
        currentBubbleLimit = msg.length() + 2;
        speak(dukeAddedTask);
    }

    /**
     * Formats task deleted message
     * @param task
     * @param taskList
     */
    public void taskDeleted(Task task, TaskList taskList) {
        String msg = task.toString();
        int taskLeft = taskList.taskLeft();
        String[] dukeAddedTask = {"Noted. I've removed this task:", "  " + msg, "Now you have " + taskLeft + " tasks " +
                "in the list."};
        currentBubbleLimit = msg.length() + 2;
        speak(dukeAddedTask);
    }

    /**
     * Formats error found msg
     * @param error_msg string that contains details of the error
     */
    public void error(String error_msg) {
        String[] dukeError = {error_msg, "Please try again" };
        currentBubbleLimit = dukeError[0].length();
        speak(dukeError);
    }

    /**
     * Formats Goodbye msg
     */
    public void goodbye() {
        currentBubbleLimit = baseBubbleLimit;
        speak("Bye. Hope to see you again soon!");
    }


    /**
     * Outputs message in Duke Template msg
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
     * Outputs Message in Duke Template
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
