package org.example;

public class Node {

    int data;
    Color color;

    Node left;
    Node right;
    Node parent;

    public Node(int data) {
        this.data = data;
        this.color = Color.RED;

        this.left = null;
        this.right = null;
        this.parent = null;
    }
}

enum Color {
    RED,
    BLACK
}

