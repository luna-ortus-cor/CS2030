class Sphere implements Shape3D {
    private final double radius;
    private static final double VOLUME_CONST = (double) 4.0 / 3.0;
    private static final int POWER = 3;

    Sphere(double radius) {
        this.radius = radius;
    }

    public double volume() {
        return VOLUME_CONST * Math.PI * Math.pow(this.radius, POWER);
    }

    @Override
    public String toString() {
        return String.format("sphere [%.2f]", this.radius);
    }

    public Sphere get() {
        return this;
    }
}
