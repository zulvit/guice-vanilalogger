package ru.zulvit.logservice;

import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;
import ru.zulvit.CustomFormatter;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileLogService implements LoggerService {
    @NotNull
    private final Logger fileLogger = Logger.getLogger(FileLogService.class.getName());
    @NotNull
    private final String tag;
    private static int counterLog = 0;


    @Inject
    public FileLogService(@NotNull String tag) throws IOException {
        this.tag = tag;
        final FileHandler fileHandler = new FileHandler("MyLogFile.log", false);
        //region file logger settings
        fileHandler.setFormatter(new CustomFormatter());
        fileLogger.addHandler(fileHandler);
        fileLogger.setUseParentHandlers(false);
        //endregion
    }

    @Override
    public void log(String message) {
        fileLogger.log(Level.INFO, counterLog + ". " +
                "<" + tag + ">" +
                message +
                "</" + tag + ">");
        counterLog++;
    }
}
