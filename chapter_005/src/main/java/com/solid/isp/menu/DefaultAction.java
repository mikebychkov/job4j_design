package com.solid.isp.menu;

public class DefaultAction implements Action {

    private Node node;

    public DefaultAction(Node node) {
        this.node = node;
    }

    @Override
    public void execute() {
        System.out.println("=".repeat(50));
        System.out.printf("Executing... %s%n", this.node.getName());
        System.out.println("=".repeat(50));
    }
}
