package mypackage.figures;

public class Square extends Figures {

    private double side;

    public Square(double side){
        this.side = side;
    }

    public double area() {
        return side*side;
    }
}
