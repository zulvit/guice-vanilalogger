package ru.zulvit.logservice;

import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;
import ru.zulvit.CustomFormatter;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompositeLogService implements LoggerService {
    @NotNull
    private final Logger fileLogger = Logger.getLogger(CompositeLogService.class.getName() + "1");
    @NotNull
    private final Logger consoleLogger = Logger.getLogger(CompositeLogService.class.getName() + "2");
    @NotNull
    private final String tag;
    private static int counterLog = 0;


    @Inject
    public CompositeLogService(@NotNull String tag) throws IOException {
        this.tag = tag;
        final FileHandler fileHandler = new FileHandler("MyLogFile.log", false);
        final ConsoleHandler consoleHandler = new ConsoleHandler();

        //region file logger settings
        fileHandler.setFormatter(new CustomFormatter());
        fileLogger.addHandler(fileHandler);
        fileLogger.setUseParentHandlers(false);
        //endregion

        //region console logger settings
        consoleHandler.setFormatter(new CustomFormatter());
        consoleLogger.addHandler(consoleHandler);
        consoleLogger.setUseParentHandlers(false);
        //endregion
    }

    @Override
    public void log(String msg) {
        fileLogger.log(Level.INFO, counterLog + ". " +
                "<" + tag + ">" +
                msg +
                "</" + tag + ">");
        counterLog++;

        consoleLogger.log(Level.INFO, counterLog + ". " +
                "<" + tag + ">" +
                msg +
                "</" + tag + ">");
        counterLog++;
    }
}
