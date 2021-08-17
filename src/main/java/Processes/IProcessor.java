package Processes;

import Enum.Command;

public interface IProcessor {
    void processCommand(Command command, String line);
    void processDefault(String line);
    void processBye();
}