package model;

public class Ball {
    private int speed;
    private int accelFreq;
    private int accelInterval;

    // Position and velocity
    private double x;
    private double y;
    private double dx;  // x velocity
    private double dy;  // y velocity
    private double radius = 10;

    public Ball() {}

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) {
        this.speed = speed;
        // Update velocity magnitudes when speed changes
        if (dx != 0 || dy != 0) {
            double currentSpeed = Math.sqrt(dx * dx + dy * dy);
            if (currentSpeed > 0) {
                dx = (dx / currentSpeed) * speed;
                dy = (dy / currentSpeed) * speed;
            }
        }
    }

    public int getAccelFreq() { return accelFreq; }
    public void setAccelFreq(int accelFreq) { this.accelFreq = accelFreq; }

    public int getAccelInterval() { return accelInterval; }
    public void setAccelInterval(int accelInterval) { this.accelInterval = accelInterval; }

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    public double getDx() { return dx; }
    public void setDx(double dx) { this.dx = dx; }

    public double getDy() { return dy; }
    public void setDy(double dy) { this.dy = dy; }

    public double getRadius() { return radius; }
    public void setRadius(double radius) { this.radius = radius; }

    // Movement
    public void move() {
        x += dx;
        y += dy;
    }

    // Bounce off top/bottom
    public void reverseY() {
        dy = -dy;
    }

    // Bounce off racket
    public void reverseX() {
        dx = -dx;
    }

    // Reset to center with random direction
    public void reset(double centerX, double centerY) {
        this.x = centerX;
        this.y = centerY;
        // Random angle between -45 and 45 degrees, going left or right
        double angle = Math.toRadians((Math.random() * 90) - 45);
        int direction = Math.random() > 0.5 ? 1 : -1;
        this.dx = direction * speed * Math.cos(angle);
        this.dy = speed * Math.sin(angle);
    }
}