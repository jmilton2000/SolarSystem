import java.awt.*;
import java.util.*;
import java.io.*;

public class Solar {
    private ArrayList<Planet> planets = new ArrayList<Planet>();
    private int sunSize;
    private String name;

    public Solar() {
        name = "SolarSystem";
        sunSize = 80;
    }

    public void setSunSize(int sunSize) { this.sunSize = sunSize; }
    public int getSunSize() { return sunSize; }
    public ArrayList<Planet> getPlanets() { return planets; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public void addPlanet() throws FileNotFoundException {
        int size = 0;
        String name = "planet" + (1 + planets.size());
        Planet planet = new Planet();
        planet.setDistance(0);
        planet.setName(name);
        planets.add(planet);
        export();
        //editPlanet();
    }

    public Planet findPlanet(String name) {
        int index = 0;
        if(planets.size() == 0) {
            System.out.println("Error: no planets in solar system"); //change later
        }
        else {

            for (int A = 0; A < planets.size(); A++) {
                if (planets.get(A).getName().equals(name)) {
                    index = A;
                }
            }
        }
        return planets.get(index);
    }

    public void addPlanet(String name, int size, int color, int distance) {
        Planet planet = new Planet();
        planet.setName(name.replaceAll(" ", "_"));
        planet.setDistance(distance);
        planet.setSize(size);
        planet.setColor(color);
        planets.add(planet);
    }

    public void export() throws FileNotFoundException {
        PrintStream output = new PrintStream(new File(name + ".sol"));
        output.println(planets.size());
        output.println(getSunSize());
        for(int A = 0; A < planets.size(); A++) {
            Planet planet = planets.get(A);
            output.println(planet.getName());
            output.println((planet.getSize() - 20)/10);
            output.println(planet.getColor());
            output.println(planet.getDistance());
        }
    }

    public void load(String name) throws FileNotFoundException {
        Scanner input = new Scanner(new File(name + ".sol"));
        this.name = name;
        int num = input.nextInt();
        sunSize = input.nextInt();
        for(int A = 0; A < num; A++) {
            String pName = input.next();
            pName = pName.replaceAll("_", " ");
            int size = input.nextInt();
            int color = input.nextInt();
            int distance = input.nextInt();
            addPlanet(pName, size, color, distance);
        }
    }
}