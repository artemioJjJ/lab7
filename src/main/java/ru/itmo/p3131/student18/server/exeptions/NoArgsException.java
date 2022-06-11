package ru.itmo.p3131.student18.server.exeptions;

/**
 * Exception NoArgsException might be thrown in ConsoleReader method startScanning on a stage of command choosing.
 * Situation when it could be thrown:
 * If command requires any parameter's for execution, but user haven't inserted them with a command.
 */
public class NoArgsException extends Exception {
    public NoArgsException(String message) {
        super(message);
    }
}
