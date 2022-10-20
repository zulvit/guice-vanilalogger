package ru.zulvit.logservice;

import com.google.inject.Inject;
import ru.zulvit.CustomFormatter;
import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleLogService implements LoggerService {
    @NotNull
    private final Logger consoleLogger = Logger.getLogger(ConsoleLogService.class.getName());
    @NotNull
    private final String tag;

    @Inject
    public ConsoleLogService(@NotNull String tag) {
        this.tag = tag;
    }

    @Override
    public void waitForInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            int counterLog = 0;
            //region console logger settings
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new CustomFormatter());
            consoleLogger.addHandler(consoleHandler);
            consoleLogger.setUseParentHandlers(false);
            //endregion

            System.out.println("Waiting for new lines. Key in Ctrl+D to exit.");
            while (true) {
                if (scanner.hasNextLine()) {
                    String msg = scanner.nextLine();
                    consoleLogger.log(Level.INFO, counterLog + ". " +
                            "<" + tag + ">" +
                            msg +
                            "</" + tag + ">");
                    counterLog++;
                } else {
                    System.exit(0);
                }
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            e.printStackTrace();
        }
    }
}
