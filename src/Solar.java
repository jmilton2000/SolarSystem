import java.awt.*;
import java.util.*;
import java.io.*;

public class Solar {
    private ArrayList<Planet> planets = new ArrayList<Planet>();
    private int sunSize;
    private String name;

    public Solar() {
        name = "SolarSystem";
        sunSize = 100;
    }

    //getters and setters
    public void setSunSize(int sunSize) {
        this.sunSize = sunSize;
    }

    public int getSunSize() {
        return sunSize;
    }

    public ArrayList<Planet> getPlanets() {
        return planets;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    //adds a planet to plaets array
    public void addPlanet() throws FileNotFoundException {
        if (planets.size() != 6) {
            int size = 0;
            String name = "planet " + (1 + planets.size());
            Planet planet = new Planet();
            planet.setDistance(makeDistance(planet));
            planet.setName(name);
            planets.add(planet);
            export();
        }
    }

    //fines a planet by name
    public Planet findPlanet(String name) {
        int index = 0;
        if(planets.size() == 0) {
            System.out.println("Error: no planets in solar system"); //change later
        } else {
            for (int A = 0; A < planets.size(); A++) {
                if (planets.get(A).getName().equals(name)) {
                    index = A;
                }
            }
        }
        return planets.get(index);
    }

    //adds a planet to plaets array (only used by load)
    public void addPlanet(String name, int size, int color, int distance) {
        Planet planet = new Planet();
        planet.setName(name);
        planet.setDistance(distance);
        planet.setSize(size);
        planet.setColor(color);
        planets.add(planet);
    }

    //exports planets array into sol file
    public void export() throws FileNotFoundException {
        PrintStream output = new PrintStream(new File("src/save/" + name + ".sol"));
        output.println(planets.size());
        output.println(getSunSize());
        for(int A = 0; A < planets.size(); A++) {
            Planet planet = planets.get(A);
            output.println(planet.getName().replaceAll(" ", "_"));
            output.println(planet.getSize());
            output.println(planet.getColor());
            output.println(planet.getDistance());
        }
    }

    //loads a sol file in to planets array
    public void load(String name) throws FileNotFoundException {
        planets = new ArrayList<Planet>();
        Scanner input = new Scanner(new File("src/save/" + name + ".sol"));
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

    //createds distance for one planet
    public int makeDistance(Planet p) {
        if (planets.size() == 0) {
            return (sunSize / 2) + 200 + (p.getSizeReal() / 2);
        } else {
            Planet pre = planets.get(planets.size() - 1);
            return (pre.getSizeReal() / 2) + (pre.getDistance()) + 100 + (p.getSizeReal() / 2);
        }
    }

    //resets distances for all planets when one is removed
    public void resetDis() {
        for (int A = 0; A < planets.size(); A++) {
            Planet curr = planets.get(A);
            if (A == 0) {
                curr.setDistance((sunSize / 2) + 200 + (curr.getSizeReal() / 2));
            } else {
                Planet pre = planets.get(A - 1);
                curr.setDistance((pre.getSizeReal() / 2) + (pre.getDistance()) + 100 + (curr.getSizeReal() / 2));
            }
        }
    }
}