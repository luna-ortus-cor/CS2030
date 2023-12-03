interface Shape {
    double getArea();
}

interface Printable {
    void print();
}

interface PrintableShape extends Shape, Printable { }

public class Circle implements PrintableShape {
    private final double x;

    public Circle(double x) {
        this.x = x;
    }

    public double getArea(){
        return this.x;
    }

    public void print() {
        System.out.println(String.format("%.3f",this.x));
    }
}
