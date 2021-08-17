package parser;

public class BasicCommandParser implements CommandParser{
    @Override
    public String[] parse(String command, String[] commandArgs) {
        return new String[]{command};
    }
}
