package sergsm.ptk2dxf;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sergey
 */
public class PolylineTest {

    public PolylineTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testPolylineAddAndRemoveVertexes() {
        Polyline p = new Polyline();
        Vertex v = new Vertex(10, 10);
        assertEquals(0, p.size());
        p.add(v);
        assertEquals(1, p.size());
        p.remove(new Vertex(10, 10));
        assertEquals(1, p.size());
        p.remove(v);
        assertEquals(0, p.size());
    }

    @Test
    public void testPolylineEquality() {
        Polyline p1 = new Polyline();
        Polyline p2 = new Polyline();
        p1.add(new Vertex(10, 10));
        p1.add(new Vertex(20, 10));
        p1.add(new Vertex(20, 20));
        p2.add(new Vertex(10, 10));
        p2.add(new Vertex(20, 10));
        p2.add(new Vertex(20, 20));
        assertTrue(p1.equals(p2));
    }
}
