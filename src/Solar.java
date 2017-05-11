import java.util.*;
import java.io.*;

public class Solar {
    private ArrayList<Planet> planets = new ArrayList<Planet>();
    private int sunSize;
    private String name;

    public Solar() {
        name = "Solar System.txt";
        sunSize = 80;
    }

    public void setSunSize(int sunSize) { this.sunSize = sunSize; }
    public int getSunSize() { return sunSize; }
    public ArrayList<Planet> getPlanets() { return planets; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public void addPlanet() {
        int size = 0;
        String name = "planet" + (1 + planets.size());
        Planet planet = new Planet();
        planet.setDistance(0);
        planet.setName(name);
        planets.add(planet);
    }

    public void editPlanet(String name) {
        if(planets.size() == 0) {
            System.out.println("Error: no planets in solar system"); //change later
        } else {
            Planet planet = this.contains(name);
        }
    }

    public Planet contains(String name) {
        int index = 0;
        for(int A = 0; A < planets.size(); A++) {
            if(planets.get(A).getName().equals(name)) {
                index = A;
            }
        }
        return planets.get(index);
    }

    public void addPlanet(String name, int size, int color) {
        Planet planet = new Planet();
        planet.setDistance(0);
        planet.setSize(size);
        planet.setColor(color);
        planets.add(planet);
        export();
        //new SolarLoad.export();
    }
    public void export() { }
}