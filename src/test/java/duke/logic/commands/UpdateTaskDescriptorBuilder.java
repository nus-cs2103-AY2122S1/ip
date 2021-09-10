package duke.logic.commands;

import java.time.LocalDateTime;

import duke.logic.commands.UpdateCommand.UpdateTaskDescriptor;

public class UpdateTaskDescriptorBuilder {
    private UpdateTaskDescriptor descriptor;

    public UpdateTaskDescriptorBuilder() {
        descriptor = new UpdateTaskDescriptor(null, null, null, null);
    }

    public UpdateTaskDescriptorBuilder(UpdateTaskDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    public UpdateTaskDescriptor build() {
        return descriptor;
    }

    public UpdateTaskDescriptorBuilder setDescription(String description) {
        LocalDateTime by = descriptor.getBy().orElse(null);
        LocalDateTime at = descriptor.getAt().orElse(null);
        LocalDateTime end = descriptor.getEnd().orElse(null);
        descriptor = new UpdateTaskDescriptor(description, by, at, end);
        return this;
    }

    public UpdateTaskDescriptorBuilder setBy(LocalDateTime by) {
        String description = descriptor.getDescription().orElse(null);
        LocalDateTime at = descriptor.getAt().orElse(null);
        LocalDateTime end = descriptor.getEnd().orElse(null);
        descriptor = new UpdateTaskDescriptor(description, by, at, end);
        return this;
    }

    public UpdateTaskDescriptorBuilder setAt(LocalDateTime at) {
        String description = descriptor.getDescription().orElse(null);
        LocalDateTime by = descriptor.getBy().orElse(null);
        LocalDateTime end = descriptor.getEnd().orElse(null);
        descriptor = new UpdateTaskDescriptor(description, by, at, end);
        return this;
    }

    public UpdateTaskDescriptorBuilder setEnd(LocalDateTime end) {
        String description = descriptor.getDescription().orElse(null);
        LocalDateTime by = descriptor.getBy().orElse(null);
        LocalDateTime at = descriptor.getAt().orElse(null);
        descriptor = new UpdateTaskDescriptor(description, by, at, end);
        return this;
    }
}
