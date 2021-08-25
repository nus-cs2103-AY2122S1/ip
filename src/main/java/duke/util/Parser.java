package duke.util;

public class Parser {
    
    public static DukeCommands parseCommand(String input){
        return DukeCommands.valueOf(input.toUpperCase());
    }
    
}
