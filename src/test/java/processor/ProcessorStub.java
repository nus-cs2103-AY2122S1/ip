package processor;

import java.util.List;

import models.Command;

public class ProcessorStub implements IProcessor {

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

    @Override
    public void processFind(String keyword) {

    }
}
