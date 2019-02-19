package com.rscafidi;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class TaskHistory {

    private HashMap<String, LocalDateTime> completed = new HashMap<>();
    private HashMap<Integer, Integer> itemsPerMinute = new HashMap();

    public void recordTaskCompleted(String name, long timeCompleted) {
        LocalDateTime d = LocalDateTime.now();
        completed.put(name, d);
    }

    public void removeCompletedTask(String name) {
        completed.remove(name);
    }

    public void generateChart() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneHourAgo = now.minusMinutes(60);

        for (Map.Entry<String, LocalDateTime> mapEntry : completed.entrySet()) {
            System.out.println(mapEntry.getKey() + ": " + mapEntry.getValue());
            if (mapEntry.getValue().isBefore(oneHourAgo)) {
                System.out.println("This item is too old!");
            }
            else {
                System.out.println("This is a good item that will go on the graph!");
            }
        }
    }

}
