package com.demo.lesson02.dependency_injection;

interface Shape {
    void draw();
}

class CircleShape implements Shape {
    @Override
    public void draw() {
        System.out.println("Draw circle!");
    }
}

class RectangleShape implements Shape {
    @Override
    public void draw() {
        System.out.println("Draw rectangle!");
    }
}

public class DrawShape {
    public Shape shape;
    public DrawShape(Shape shape) {
        this.shape = shape;
    }

    public void Draw() {
        shape.draw();
    }
    public static void main(String[] args) {
        DrawShape drawShape = new DrawShape(new CircleShape());
        drawShape.Draw();

        drawShape = new DrawShape(new RectangleShape());
        drawShape.Draw();
    }
}
