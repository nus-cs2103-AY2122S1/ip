package parser;

public interface CommandParser {
    String[] parse(String command, String[] commandArgs);
}
