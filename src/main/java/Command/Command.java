package Command;

import java.util.Scanner;

public abstract class Command {
    private String args;
    protected Command(String args){
        this.args = args;
    }
    public static Command createCommand(String input) throws Exception{
        String[] cmd_args = input.split(" ", 2);
        Command cmd;
        switch (cmd_args[0]){
            case "bye":
                cmd = new ByeCommand();
                break;
            case "list":
                cmd = new ListCommand();
                break;
            case "done":
                cmd = new DoneCommand(cmd_args[1]);
                break;
            case "delete":
                cmd = new DeleteCommand(cmd_args[1]);
                break;
            case "todo":
                cmd = new TodoCommand(cmd_args[1]);
                break;
            case "event":
                cmd = new EventCommand(cmd_args[1]);
                break;
            case "deadline":
                cmd = new DeadlineCommand(cmd_args[1]);
                break;
            default:
                throw new Exception();
        }
        return cmd;
    }

    public abstract void execute();

    public static void main(String[] args) throws Exception {
        Scanner Sc = new Scanner(System.in);
        while(true){
            String input = Sc.nextLine() + " ";
            Command cmd = Command.createCommand(input);
            cmd.execute();
        }
    }
}
