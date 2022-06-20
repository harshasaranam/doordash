package com.doordash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MenuTree {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Node a = new Node("a", 1, true);
        Node b = new Node("b", 2, true);
        Node c = new Node("c", 3, true);
        Node d = new Node("d", 4, true);
        Node e = new Node("e", 5, true);
        Node g = new Node("f", 6, true);

        a.children.add(b);
        a.children.add(c);

        b.children.add(d);
        b.children.add(e);

        c.children.add(g);

        Node a1 = new Node("a", 1, true);
        Node b1 = new Node("b", 2, false);
        Node c1 = new Node("c", 3, true);
        Node d1 = new Node("d", 4, false);
        Node e1 = new Node("e", 5, false);
        Node f1 = new Node("f", 66, true);
        Node g1 = new Node("g", 7, false);

      //  a1.children.add(b1);
        a1.children.add(c1);

//        b1.children.add(d1);
//        b1.children.add(e1);
//        b1.children.add(f1);

        c1.children.add(g1);

        MenuTree s = new MenuTree();
        System.out.println(s.f(a, a1));

    }

    public int f(Node n1, Node n2) {
        int r = 0;
        if (n1 == null && n2 == null) return r;
        if (n1 == null || n2 == null || !equals(n2, n1)) r++;
        Map<String, Node> child1 = mapChildren(n1);
        Map<String, Node> child2 = mapChildren(n2);
        for (String k : child1.keySet()) {
            r += f(child1.get(k), child2.getOrDefault(k, null));
        }
        for (String k : child2.keySet()) {
            if (!child1.containsKey(k)) {
                r += f(null, child2.get(k));
            }
        }
        return r;
    }

    private static Map<String, Node> mapChildren(Node n) {
        Map<String, Node> map = new HashMap<>();
        if (n == null) return map;
        for (Node c : n.children) map.put(c.key, c);
        return map;
    }

    public boolean equals(Node n1, Node n2) {
        return n2.key.equals(n1.key) && n2.value == n1.value && n2.active == n1.active;
    }
}

class Node {
    String key;
    int value;
    boolean active;
    List<Node> children;
    public Node(String key, int val, boolean active)
    {
        this.key = key;
        this.value = val;
        this.active = active;
        this.children = new ArrayList<>();
    }
}
