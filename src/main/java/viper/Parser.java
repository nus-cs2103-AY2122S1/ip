package viper;

import commands.*;
import exceptions.DukeException;

/**
 * deals with making sense of the user command
 */
public class Parser {
    
    public static Command parse(String str) throws DukeException {
        try {
            String[] splitStr = str.split(" ", 2);
            if (splitStr[0].equalsIgnoreCase("list")) {
                return new ListCommand();
            } else if (splitStr[0].equalsIgnoreCase("done")) {
                return new DoneCommand(str);
            } else if (splitStr[0].equalsIgnoreCase("delete")) {
                return new DeleteCommand(str);
            } else if (splitStr[0].equalsIgnoreCase("todo") || 
                    splitStr[0].equalsIgnoreCase("deadline") ||
                    splitStr[0].equalsIgnoreCase("event")) {
                return new AddCommand(str);
            } else if (splitStr[0].equalsIgnoreCase("bye")) {
                return new ByeCommand();
            } else {
                throw new DukeException("Please enter a valid command so that I will be able to help you :p");
            }
        } catch (Exception e) {
            throw new DukeException("Oops! Please enter a command to proceed~");
        }
    }
}
