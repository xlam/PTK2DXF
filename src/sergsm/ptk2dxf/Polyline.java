package sergsm.ptk2dxf;

import java.util.ArrayList;

/**
 *
 * @author Sergey
 */
class Polyline {
    private ArrayList<Vertex> vertixes;

    public Polyline() {
        vertixes = new ArrayList();
    }

    public void add(Vertex v) {
        vertixes.add(v);
    }

    public void remove(Vertex v) {
        vertixes.remove(v);
    }

    public Vertex get(int i) {
        return vertixes.get(i);
    }

    public int size() {
        return vertixes.size();
    }

    public boolean equals(Polyline p) {
        if (vertixes.size() != p.size())
            return false;
        for (int i=0; i < vertixes.size(); i++) {
            Vertex v = vertixes.get(i);
            if (!(v.equals(p.get(i))))
                return false;
        }
        return true;
    }
}
