import java.awt.*;
public class Planet {
    private String name; //defalt is Planet
    private int size; //size are 30, 40, 50, 60 (defalt = 30)
    private boolean hasLife; //based on distance and sun
    private int distance; //for now = 20
    private int color;

    public Planet() {
        hasLife = false;
        name = "Planet";
        color = 1; //this may be changed later
        size = 30;
    }

    //geters and seters
    public String getName() { return name; }
    public int getSize() { return size; }
    public boolean getHasLife() { return hasLife; }
    public int getDistance() { return distance; }
    public int getColor() { return color; }

    public void setName(String name) { this.name = name; }
    public void setSize(int size) { this.size = 20 + (size * 10); }
    public void setHasLife(boolean hasLife) { this.hasLife = hasLife; }
    public void setDistance(int distance) { this.distance = distance; }
    public void setColor(int color) { this.color = color; }

}