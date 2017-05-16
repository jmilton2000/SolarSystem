import java.awt.*;
import java.util.*;
import java.io.*;

public class Testing {
    public static void main(String[] args) throws FileNotFoundException {
        Solar test = new Solar();
        test.addPlanet("planet 1", 2, 1, 40);
        test.addPlanet("planet 3", 4, 3, 40);
        test.addPlanet("planet 2", 1, 1, 40);
        //test.load("SolarSystem");
        test.export();
        System.out.println("Done!");
    }
}