package ru.zulvit;

import com.google.inject.AbstractModule;
import ru.zulvit.logservice.CompositeLogService;
import ru.zulvit.logservice.ConsoleLogService;
import ru.zulvit.logservice.FileLogService;
import ru.zulvit.logservice.LoggerService;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

class LoggerModule extends AbstractModule {
    @NotNull
    private final Logger logger = Logger.getLogger(LoggerModule.class.getName());

    @NotNull
    private final String[] args;
    public final int TYPE_OF_LOGGER_PARAM_INDEX = 0;
    public final int TAG_PARAM_INDEX = 1;

    public LoggerModule(@NotNull String[] args) {
        this.args = args;
    }

    @Override
    protected void configure() {
        try {
            if (args[TYPE_OF_LOGGER_PARAM_INDEX].equalsIgnoreCase("composite")) {
                bind(LoggerService.class).toInstance(new CompositeLogService(args[TAG_PARAM_INDEX]));
            } else if (args[TYPE_OF_LOGGER_PARAM_INDEX].equalsIgnoreCase("console")) {
                bind(LoggerService.class).toInstance(new ConsoleLogService(args[TAG_PARAM_INDEX]));
            } else if (args[TYPE_OF_LOGGER_PARAM_INDEX].equalsIgnoreCase("file")) {
                bind(LoggerService.class).toInstance(new FileLogService(args[TAG_PARAM_INDEX]));
            } else {
                logger.log(Level.WARNING, """
                        Pass parameters at startup in the format: [logging_type] [tag]
                        Possible types of logging: CONSOLE, FILE, COMPOSITE""", new Throwable("Incorrect launch options"));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "You didn't enter parameters when starting the application", new ArrayIndexOutOfBoundsException());
        }
    }
}
