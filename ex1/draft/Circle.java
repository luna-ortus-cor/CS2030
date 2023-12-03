class Circle {
    private final Point centre;
    private final double radius;

    Circle(Point centre, double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    public String toString() {
        return String.format("circle of radius %.1f centred at %s", 
                this.radius, this.centre.toString()); 
    }

    public boolean contains(Point p) {
        return this.centre.distBetween(p) <= this.radius;
    }

    public Point getCentre() {
        return this.centre;
    }

    public double getRadius() {
        return this.radius;
    }
}
