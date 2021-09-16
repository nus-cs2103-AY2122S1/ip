package genie.common;

public class Message {
    public static final String HELPCOMMANDS =  "These are the services I can provide you:\n" +
            "Todo task:    " +
            "\ttodo {task name} {priority level}\n" +
            "Deadline task:" +
            "\tdeadline {task name} /by {yyyy-mm-dd} {priority}\n" +
            "Event:      " +
            "\t        event {event name} /at {yyyy-mm-dd} {priority}\n" +
            "List everything:" +
            "\tlist\n" +
            "Mark task done:" +
            "\tdone {index}\n" +
            "Delete a task:    " +
            "\tdelete {index}\n" +
            "Show all commands:" +
            "\thelp\n" +
            "Priority Levels:     \tHIGH, MEDIUM, LOW\n" +
            "Quit Duke:  " +
            "\t        bye\n";
    
    public static String repeatReply(String str) {
        return "I hear you say '" + str + "' aladdin " +
                "\nI am the genie.Genie & I can do lots of things. " +
                "\nTo find out what I can do, say help.";
    }
    
    public static final String GOODBYEMESSAGE = "Goodbye Aladdin! \nIf you need anything, just rub the lamp. " +
            "\nOh btw, atb getting Jasmine, that I can't help you.";
    
    public static final String WELCOMEMESSAGE = "HELLOOOO Aladdin! I am the genie.Genie.\n" +
            "What can I do for you today?\n" +
            "Say 'help' and I can show you what I can do.";
}
