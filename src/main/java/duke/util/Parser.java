package duke.util;

public class Parser {
    
    public DukeCommands parseCommand(String input){
        return DukeCommands.valueOf(input.toUpperCase());
    }
    
}
