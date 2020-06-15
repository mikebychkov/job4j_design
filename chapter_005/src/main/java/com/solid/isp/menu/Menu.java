package com.solid.isp.menu;

import java.util.Scanner;

public class Menu {

    public static Node makeMenu() {
        Node rootNode = new MenuNode("root");
        Node node1 = rootNode.add(new MenuNode("say 1"));
        node1.add(new MenuNode("say 1.1"));
        node1.add(new MenuNode("say 1.2")).add(new MenuNode("say 1.2.1"));
        Node node2 = rootNode.add(new MenuNode("say 2"));
        node2.add(new MenuNode("say 2.1"));
        node2.add(new MenuNode("say 2.2"));
        Node node3 = rootNode.add(new MenuNode("say 3"));
        node3.add(new MenuNode("say 3.1")).add(new MenuNode("say 3.1.1"));
        return rootNode;
    }

    public static void main(String[] args) {
        Node menu = makeMenu();
        boolean exitMode = false;
        Scanner in = new Scanner(System.in);
        while (!exitMode) {
            menu.print();
            System.out.println("Choose the menu item (or type \"exit\"):");
            String input = in.nextLine();
            if ("exit".equals(input)) {
                exitMode = true;
                continue;
            }
            Node target = menu.find(input);
            if (target == null) {
                System.out.println("Can't find entered menu. Pls try again.");
                continue;
            }
            target.getAction().execute();
        }
    }
}
