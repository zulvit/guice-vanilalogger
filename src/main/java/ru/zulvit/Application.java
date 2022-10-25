package ru.zulvit;

import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;
import ru.zulvit.logservice.LoggerService;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Application {
    private final LoggerService loggerService;

    @Inject
    public Application(@NotNull LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    public void waitForInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Waiting for new lines. Key in Ctrl+D to exit.");
            while (true) {
                if (scanner.hasNextLine()) {
                    loggerService.log(scanner.nextLine());
                } else {
                    System.exit(0);
                }
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            e.printStackTrace();
        }
    }
}
