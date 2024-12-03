import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Item> items = Dooley.createItems("data/dooley_item.csv");

        // Print items for debugging
        for (Item item : items) {
            System.out.println("Name: " + item.name + ", Size: " + item.Size +
                    ", Type: " + item.Type + ", Effect: " + item.Effect +
                    ", Cooldown: " + item.CD);
        }

        PriorityQueue<Item> priorityQueue = Dooley.prioritizeItems(items);

        while (!priorityQueue.isEmpty()) {
            Item item = priorityQueue.poll();
            System.out.println("Name: " + item.name +
                    ", Effect: " + item.Effect +
                    ", Shield/DPS: " + Dooley.calculateScore(item));
        }
    }
}
//public class Main {
//    public static void main(String[] args) {
//        ArrayList<Item> items = Dooley.createItems("data/dooley_item.csv");
//
//        PriorityQueue<Item> priorityQueue = Dooley.prioritizeItems(items);
//
//        // Print items from the PriorityQueue
//        while (!priorityQueue.isEmpty()) {
//            Item item = priorityQueue.poll();
//            System.out.println("Name: " + item.name +
//                    ", Effect: " + item.Effect +
//                    ", Shield/DPS: " + Dooley.calculateScore(item));
//        }
//    }
//}