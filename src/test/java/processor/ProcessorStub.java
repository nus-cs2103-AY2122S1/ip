package processor;

import java.util.List;

import models.Command;

public class ProcessorStub implements IProcessor {

    @Override
    public String processCommand(Command command, List<String> arguments) {
        return "";
    }

    @Override
    public String processDefault(List<String> arguments) {
        return "";
    }

    @Override
    public String processList() {
        return "";
    }

    @Override
    public String processDone(String index) {
        return "";
    }

    @Override
    public String processDelete(String index) {
        return "";
    }

    @Override
    public String processBye() {
        return "";
    }

    @Override
    public String processFind(String ... keywords) {
        return "";
    }

    @Override
    public String processUndo() {
        return "";
    }
}
