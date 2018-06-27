package mypackage.figures;


public class Circle extends Figures{

    private double radius;

    public Circle(double radius){
        this.radius = radius;
    }

    public double area() {
        return Math.PI*(this.radius*this.radius);
    }
}
