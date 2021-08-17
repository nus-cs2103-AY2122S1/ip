package Processes;

import java.util.List;

import Enum.Command;

public interface IProcessor {
    void processCommand(Command command, List<String> arguments);
    void processBye();
    void processList();
    void processDone(String index);
    void processDefault(String line);
}