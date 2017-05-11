import java.util.*;
import java.io.*;

public class SolarLoad extends Solar  {

    public void export() throws FileNotFoundException {
        PrintStream output = new PrintStream(new File(getName()));
        ArrayList<Planet> planets = getPlanets();
        output.println(getSunSize());
        //for(int A = 0; A < planets.size(); A++) {
        Planet planet = planets.get(0);
        output.println(planet.getName());
        output.println(planet.getSize());
        output.println(planet.getColor());
        output.println(planet.getDistance());
        //}
    }

    public void load() {
        //Scanner input = new Scanner(new File(inputFileName));
    }
}
