package BobCat.executor.parser;

import BobCat.exception.CommandArityException;

import java.util.Arrays;

public class CreationCommandParser implements CommandParser {
    @Override
    public String[] parse(String command, String[] commandArgs) {
        if (command.equals("todo")) {
            String commandArg = String.join(" ",
                    Arrays.copyOfRange(commandArgs, 1, commandArgs.length));
            if (commandArg.length() <= 0) {
                throw new CommandArityException("The description of a " + command + " cannot be empty.");
            }
            return new String[]{command, commandArg};
        } else {
            String args = String.join(" ",
                    Arrays.copyOfRange(commandArgs, 1, commandArgs.length));
            String[] argArr = args.split("\\s/" + (command.equals("deadline") ? "by" : "at"));
            if (argArr[0].trim().length() <= 0) {
                throw new CommandArityException("The description of a " + command + " cannot be empty.");
            } else if (argArr.length == 1) {
                throw new CommandArityException("The date of a " + command + " cannot be empty.");
            }
            return new String[]{command, argArr[0].trim(), argArr[1].trim()};
        }
    }
}
