package org.lab5.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {
    private static final int MAX_HISTORY_SIZE = 6;
    private static final List<String> history = new ArrayList<>();
    private static int current = 0;

    public static void add(String command) {
        if (history.size() == MAX_HISTORY_SIZE) {
            history.remove(0);
        }
        history.add(command);
        current = history.size();
    }

    public static String getPrevious() {
        if (current > 0) {
            current--;
        }
        return history.get(current);
    }

    public static String getNext() {
        if (current < history.size() - 1) {
            current++;
        }
        return history.get(current);
    }

    public static List<String> getHistory() {
        return history;
    }
}
