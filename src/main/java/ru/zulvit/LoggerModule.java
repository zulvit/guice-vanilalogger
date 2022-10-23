package ru.zulvit;

import com.google.inject.AbstractModule;
import org.jetbrains.annotations.NotNull;
import ru.zulvit.logservice.CompositeLogService;
import ru.zulvit.logservice.ConsoleLogService;
import ru.zulvit.logservice.FileLogService;
import ru.zulvit.logservice.LoggerService;

class LoggerModule extends AbstractModule {
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
            if (args.length != 2) {
                throw new Exception("You must pass 2 arguments");
            }

            if (args[TYPE_OF_LOGGER_PARAM_INDEX].equalsIgnoreCase("composite")) {
                bind(LoggerService.class).toInstance(new CompositeLogService(args[TAG_PARAM_INDEX]));
            } else if (args[TYPE_OF_LOGGER_PARAM_INDEX].equalsIgnoreCase("console")) {
                bind(LoggerService.class).toInstance(new ConsoleLogService(args[TAG_PARAM_INDEX]));
            } else if (args[TYPE_OF_LOGGER_PARAM_INDEX].equalsIgnoreCase("file")) {
                bind(LoggerService.class).toInstance(new FileLogService(args[TAG_PARAM_INDEX]));
            } else {
                throw new Exception("""
                        Pass parameters at startup in the format: [logging_type] [tag]
                        Possible types of logging: CONSOLE, FILE, COMPOSITE
                        """);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
