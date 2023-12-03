class SolidCuboid extends Cuboid implements Solid {
    private final double density;
    private final SolidImpl solid;

    SolidCuboid(double height, double width, double length, double density) {
        super(height, width, length);
        this.density = density;
        this.solid = new SolidImpl(super.get(), this.density);
    }

    public double mass() {
        return solid.mass();
    }

    @Override
    public String toString() {
        return String.format("solid-%s with a mass of %.2f", super.toString(), this.mass());
    }
}
