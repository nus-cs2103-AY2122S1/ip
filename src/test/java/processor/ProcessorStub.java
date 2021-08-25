package processor;

import models.Command;

import java.util.List;

public class ProcessorStub implements IProcessor{

    @Override
    public void processCommand(Command command, List<String> arguments) {

    }

    @Override
    public void processDefault(List<String> arguments) {

    }

    @Override
    public void processList() {

    }

    @Override
    public void processDone(String index) {

    }

    @Override
    public void processDelete(String index) {

    }

    @Override
    public void processBye() {

    }
}
