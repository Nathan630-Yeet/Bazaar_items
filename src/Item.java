public class Item {
    String name;
    Double CD;
    String Effect;
    String Type;
    String Size;

    public Item(String size, String type, String effect, Double CD, String name) {
        Size = size;
        Type = type;
        Effect = effect;
        this.CD = CD;
        this.name = name;
    }
}
