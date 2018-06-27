package mypackage.figures;

public class Triangle extends Figures{

    private double side_1;
    private double side_2;
    private double side_3;

    public Triangle(double side_1, double side_2, double side_3){
        this.side_1 = side_1;
        this.side_2 = side_2;
        this.side_3 = side_3;
    }

    public double area() {
        Double p = (this.side_1 + this.side_2 + this.side_3) * 0.5;
        return Math.sqrt(p*(p-this.side_1)*(p-this.side_2)*(p-this.side_3));
    }
}
