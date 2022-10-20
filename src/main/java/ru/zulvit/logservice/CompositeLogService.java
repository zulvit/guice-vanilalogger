package ru.zulvit.logservice;

import com.google.inject.Inject;
import ru.zulvit.CustomFormatter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
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

    @Inject
    public CompositeLogService(@NotNull String tag) {
        this.tag = tag;
    }

    @Override
    public void waitForInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            int counterLog = 0;
            //region file logger settings
            FileHandler fileHandler = new FileHandler("MyLogFile.log", false);
            fileHandler.setFormatter(new CustomFormatter());
            fileLogger.addHandler(fileHandler);
            fileLogger.setUseParentHandlers(false);
            //endregion

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
                } else {
                    System.exit(0);
                }
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
