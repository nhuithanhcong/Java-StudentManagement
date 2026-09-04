package command;

import exception.RegistrationException;

public interface Command {
    void execute() throws RegistrationException;
    void undo();
}