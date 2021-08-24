public class Parser {
    
    DukeCommands parseCommand(String input){
        return DukeCommands.valueOf(input.toUpperCase());
    }
    
}
