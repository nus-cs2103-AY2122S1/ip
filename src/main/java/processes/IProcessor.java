package processes;

import java.util.List;

import models.Command;

public interface IProcessor {
    void processCommand(Command command, List<String> arguments);
    void processBye();
    void processList();
    void processDone(String index);
    void processDelete(String index);
    void processDefault(List<String> arguments);
}