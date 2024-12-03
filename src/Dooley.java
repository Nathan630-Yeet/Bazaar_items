import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dooley {
    public static ArrayList<Item> createItems(String fileName) {
        ArrayList<Item> items = new ArrayList<>();
        File file = new File(fileName);

        try {
            Scanner scan = new Scanner(file);
            boolean isFirstLine = true;

            while (scan.hasNextLine()) {
                String line = scan.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                try {
                    String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");


                    String size = values.length > 4 ? values[4].trim() : "Unknown";
                    String type = values.length > 3 ? values[3].trim() : "Unknown";
                    String effect = values.length > 1 ? values[1].trim() : "Unknown";
                    Double CD = values.length > 2 ? parseCooldown(values[2].trim()) : 0.0;
                    String name = values.length > 0 ? values[0].trim() : "Unnamed";


                    Item item = new Item(size, type, effect, CD, name);
                    items.add(item);
                } catch (Exception e) {
                    System.err.println("Error processing line: " + line);
                    e.printStackTrace();
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return items;
    }

    private static Double parseCooldown(String cd) {
        try {
            return Double.parseDouble(cd.replaceAll("[^0-9.]", "").trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public static PriorityQueue<Item> prioritizeItems(ArrayList<Item> items) {

        Comparator<Item> itemComparator = (item1, item2) -> {
            double item1Score = calculateScore(item1);
            double item2Score = calculateScore(item2);


            return Double.compare(item2Score, item1Score);
        };

        // PriorityQueue with custom comparator
        PriorityQueue<Item> priorityQueue = new PriorityQueue<>(itemComparator);

        // Add all items to the queue
        for (Item item : items) {
            priorityQueue.add(item);
        }

        return priorityQueue;
    }

    // Helper method to calculate score based on Shield or DPS
    public static double calculateScore(Item item) {
        double shieldValue = extractShield(item.Effect);
        if (shieldValue > 0) {
            return shieldValue; // Prioritize Shield if present
        }

        double damage = extractDamage(item.Effect);
        if (item.CD > 0) { // Avoid division by zero
            return damage / item.CD; // Calculate DPS
        }

        return 0.0; // Default score for invalid items
    }

    // Helper method to extract damage from the Effect field
    private static double extractDamage(String effect) {
        try {
            String damageString = effect.replaceAll("[^0-9.]", " "); // Extract numeric parts
            String[] parts = damageString.trim().split("\\s+");
            if (parts.length > 0) {
                return Double.parseDouble(parts[0]); // Return the first numeric value as damage
            }
        } catch (Exception e) {
            // If parsing fails, return 0
        }
        return 0.0;
    }

    // Helper method to extract Shield value from the Effect field
    private static double extractShield(String effect) {
        try {
            if (effect.toLowerCase().contains("shield")) {
                String shieldString = effect.replaceAll("[^0-9.]", " "); // Extract numeric parts
                String[] parts = shieldString.trim().split("\\s+");
                if (parts.length > 0) {
                    return Double.parseDouble(parts[0]); // Return the first numeric value as Shield
                }
            }
        } catch (Exception e) {
            // If parsing fails, return 0
        }
        return 0.0;
    }

    public static void printData(String fileName) {
        File file = new File(fileName);
        try{
            Scanner scan = new Scanner(file);
            scan.useDelimiter(",");
            while(scan.hasNext()){
                String data = scan.next();
                if(data == "Small" || (data == "Medium"|| data == "Size")){
                    System.out.println(data + "|");
                }
                else{
                    System.out.print(data + "|");
                }
            }
        }catch (FileNotFoundException e){

            e.printStackTrace();
        }
    }
}
