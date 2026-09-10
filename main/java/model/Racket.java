package model;

public class Racket {
    private int width;
    private int height;
    private double x;
    private double y;
    private double speed = 10.0;  // Movement speed

    public Racket() {}

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }

    // Movement methods
    public void moveUp() {
        y -= speed;
    }

    public void moveDown() {
        y += speed;
    }

    // Keep racket within bounds
    public void constrainToBounds(double minY, double maxY) {
        if (y < minY) y = minY;
        if (y + height > maxY) y = maxY - height;
    }
}