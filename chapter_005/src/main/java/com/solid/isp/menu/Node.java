package com.solid.isp.menu;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {

    private String name;
    private int level = 0;
    private Action action = new DefaultAction(this);
    private List<Node> nodes = new ArrayList<>();

    public Node(String name) {
        this.name = name;
    }

    public Node(String name, Action action) {
        this(name);
        this.action = action;
    }

    public void print() {
        System.out.printf("%s %s%n", "-".repeat(this.level * 3), this.name);
        for (Node node : this.nodes) {
            node.print();
        }
    }

    public Node add(Node node) {
        node.setLevel(this.level + 1);
        this.nodes.add(node);
        return node;
    }

    public Node find(String name) {
        Node rsl = null;
        for (Node node : this.nodes) {
            if (node.name.equals(name)) {
                rsl = node;
                break;
            } else {
                rsl = node.find(name);
                if (rsl != null) {
                    break;
                }
            }
        }
        return rsl;
    }

    List<Node> getChildNodes() {
        return this.nodes;
    }

    public String getName() {
        return name;
    }

    public Action getAction() {
        return this.action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
