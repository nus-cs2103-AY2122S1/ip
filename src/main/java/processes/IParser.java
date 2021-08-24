package processes;

public interface IParser {
    boolean parseLine(String line);
    boolean nextLine();
}