import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Item> items = Dooley.createItems("data/dooley_item.csv");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a build type (freeze, haste, crit, destroy, charge, burn, slow, poison, weapon, shield): ");
        String buildType = scanner.nextLine();


        ArrayList<Item> filteredItems = Dooley.filterByBuild(items, buildType);

        if (filteredItems.isEmpty()) {
            System.out.println("No items found for the specified build type: " + buildType);
        } else {
            System.out.println("Items for build type '" + buildType + "':");
            for (Item item : filteredItems) {
                System.out.println("Name: " + item.name + ", Effect: " + item.Effect +
                        ", Cooldown: " + item.CD + ", Type: " + item.Type + ", Size: " + item.Size);
            }
        }


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
                    ", priority score:" + Dooley.calculateScore(item));
        }
    }
}