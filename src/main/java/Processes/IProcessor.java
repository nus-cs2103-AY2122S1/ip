package Processes;

import Enum.Command;

public interface IProcessor {
    void processCommand(Command command, String line);
    void processBye();
    void processList();
    void processDefault(String line);
}