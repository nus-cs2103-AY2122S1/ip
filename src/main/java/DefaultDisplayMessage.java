public class DefaultDisplayMessage extends Command {

    @Override
    public void execute() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("-----------------------------------------");
        System.out.println(" Hello! I am Duke");
        System.out.println(" What can I do for you?");
        System.out.println("-----------------------------------------");
        System.out.println();
    }
}
