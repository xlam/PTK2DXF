package sergsm.ptk2dxf;

/**
 *
 * @author Sergey
 */
class Vertex {
    private int x;
    private int y;

    public Vertex(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Vertex v) {
        return (x == v.getX()) && (y == v.getY());
    }

    public String toString() {
        return "[" + x + ";" + y + "]";
    }
}
