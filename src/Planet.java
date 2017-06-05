class Planet {
    private String name; //default is Planet
    private int size; //size are 30, 40, 50, 60 (default = 30)
    private int distance;
    private String color;

    private Planet() {
        name = "Planet";
        color = "R";
        size = 30;
    }

    //getters and setters
    String getName() {
        return name;
    }

    int getSize() {
        return (size - 20) / 10;
    }

    int getSizeReal() {
        return size;
    }

    int getDistance() {
        return distance;
    }

    String getColor() {
        return color;
    }

    void setName(String name) {
        this.name = name;
    }

    void setSize(int size) {
        this.size = 20 + (size * 10);
    }

    void setDistance(int distance) {
        this.distance = distance;
    }

    void setColor(String color) {
        this.color = color;
    }
}