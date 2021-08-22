package duke;

public class Parser {

    public static String[] parseUserInput(String input){
        String[] cmd_args = input.split(" ", 2);
        return stripStrings(cmd_args);
    }

    public static String[] parseArgs(String args, String delim){
        String[] name_time = args.split(delim);
        checkArg(name_time);
        return stripStrings(name_time);
    }

    public static String[] parseStorage(String line){
        String[] parts = line.split("\\|");
        return stripStrings(parts);
    }

    private static void checkArg(String[] arg) throws DukeException{
        if(arg.length < 2){
            throw new DukeException("Please specify time");
        }else if(arg.length > 2){
            throw new DukeException("too many argument");
        }
    }

    private static String[] stripStrings(String[] strings){
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].strip();
        }
        return strings;
    }
}
