package sergsm.ptk2dxf;

import java.util.ArrayList;

/**
 *
 * @author Sergey
 */
class Polyline {
    private ArrayList<Vertex> vertices;

    public Polyline() {
        vertices = new ArrayList();
    }

    public void add(Vertex v) {
        vertices.add(v);
    }

    public void remove(Vertex v) {
        vertices.remove(v);
    }

    public Vertex get(int i) {
        return vertices.get(i);
    }

    public int size() {
        return vertices.size();
    }

    public boolean equals(Polyline p) {
        if (vertices.size() != p.size())
            return false;
        for (int i=0; i < vertices.size(); i++) {
            Vertex v = vertices.get(i);
            if (!(v.equals(p.get(i))))
                return false;
        }
        return true;
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }
}
