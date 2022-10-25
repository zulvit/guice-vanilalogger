package ru.zulvit.logservice;

import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;
import ru.zulvit.CustomFormatter;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleLogService implements LoggerService {
    @NotNull
    private final Logger consoleLogger = Logger.getLogger(ConsoleLogService.class.getName());
    @NotNull
    private final String tag;
    private static int counterLog = 0;


    @Inject
    public ConsoleLogService(@NotNull String tag) {
        this.tag = tag;
        final ConsoleHandler consoleHandler = new ConsoleHandler();
        //region console logger settings
        consoleHandler.setFormatter(new CustomFormatter());
        consoleLogger.addHandler(consoleHandler);
        consoleLogger.setUseParentHandlers(false);
        //endregion
    }

    @Override
    public void log(String msg) {
        consoleLogger.log(Level.INFO, counterLog + ". " +
                "<" + tag + ">" +
                msg +
                "</" + tag + ">");
        counterLog++;
    }
}