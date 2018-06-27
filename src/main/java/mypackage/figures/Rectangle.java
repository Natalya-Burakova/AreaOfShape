package mypackage.figures;


public class Rectangle extends Figures {

    private double side_1;
    private double side_2;

    public Rectangle(double side_1, double side_2){
        this.side_1 = side_1;
        this.side_2 = side_2;
    }

    public double area() {
        return this.side_1*this.side_2;
    }
}
