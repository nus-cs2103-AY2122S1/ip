package Common;

public class Message {
    public static final String HELPCOMMANDS =  "These are the services I can provide you:\n" +
            "todo             " +
            "\tMake a todo task\n" +
            "deadline           " +
            "\tMake a new deadline event\n" +
            "event              " +
            "\tMake a event\n" +
            "list                  " +
            "\tList out your events\n" +
            "done {index}    " +
            "\tComplete the task at mentioned index\n" +
            "delete {index} " +
            "\tComplete the task at mentioned index\n" +
            "help                " +
            "\tShow all available commands\n" +
            "bye                 " +
            "\tQuit Duke.\n";
    
    public static String byeReply(String str) {
        return "I hear you say '" + str + "' aladdin " +
                "\nI am the Genie & I can do lots of things. " +
                "\nTo find out what I can do, say help.";
    }
    
    public static final String WELCOMEMESSAGE = "HELLOOOO Aladdin! I am the Genie.\n" +
            "What can I do for you today?\n" +
            "Say 'help' and I can show you what I can do.";
}
