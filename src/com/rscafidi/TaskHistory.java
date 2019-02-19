package com.rscafidi;

import javafx.scene.chart.XYChart;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TaskHistory {

    private HashMap<String, Long> completed = new HashMap<>();
    private HashMap<Integer, Integer> itemsPerMinute = new HashMap();

    public void recordTaskCompleted(String name, Long timeCompleted) {
        completed.put(name, timeCompleted);
    }

    public void removeCompletedTask(String name) {
        completed.remove(name);
    }

    public void generateChart() {
        long currentTime = System.currentTimeMillis();
        long threshold = 3600000L;
        long age = 0L;
        double index;
        long denom = 60000L;
        int frequency = 0;

        Iterator it = completed.entrySet().iterator();
        itemsPerMinute.clear();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            age = currentTime - (Long)pair.getValue();
            if (age > threshold) {
                // Do nothing - don't add to completed list for chart because the task was completed too long ago
            }
            else {
                index = ((long)((float)pair.getValue()/(float)denom)*100);
                if(index > 60){
                    index = 0L;
                }
                if (itemsPerMinute.containsKey(index)) {
                    frequency = itemsPerMinute.get(index);
                }

                itemsPerMinute.put((int)index, ++frequency);
            }
        }



    }

}
