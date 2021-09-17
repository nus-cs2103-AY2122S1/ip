package duke;

public class Ui {

    public Ui(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public String great(){
        String greatSent = "\n" +
                "   ____________________________________________________________\n" +
                "   Hello! I'm Duke\n" +
                "   What can I do for you? \n" +
                "   ____________________________________________________________";

        return greatSent;
    }

    public String byeSent(){
        String byesent = "\n" +
                "   ____________________________________________________________\n" +
                "   Bye. Hope to see you again soon!\n" +
                "   ____________________________________________________________";

        return byesent;
    }



}
