package sergsm.ptk2dxf;

import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Sergey
 */
public class ParserTest {

    private Parser parser;

    public ParserTest() {
    }

    @Before
    public void setUp() {
        parser = new Parser();
    }

    @After
    public void tearDown() {
    }

    private ArrayList<Polyline> getTestPolylineArray() {
        ArrayList polylines = new ArrayList();
        Polyline p;

        p = new Polyline();
        p.add(new Vertex(5, 5));
        p.add(new Vertex(5, 10));
        p.add(new Vertex(10, 10));
        p.add(new Vertex(10, 5));
        p.add(new Vertex(5, 5));
        polylines.add(p);

        p = new Polyline();
        p.add(new Vertex(15, 5));
        p.add(new Vertex(15, 10));
        p.add(new Vertex(20, 10));
        p.add(new Vertex(20, 5));
        p.add(new Vertex(15, 5));
        polylines.add(p);

        p = new Polyline();
        p.add(new Vertex(25, 5));
        p.add(new Vertex(25, 10));
        p.add(new Vertex(30, 10));
        p.add(new Vertex(30, 5));
        p.add(new Vertex(25, 5));
        polylines.add(p);

        return polylines;
    }

    @Test
    public void testParser() {
        parser.setCSVFileName("./samples/table.csv");
        ArrayList<Polyline> expected = getTestPolylineArray();
        ArrayList<Polyline> result = parser.parse();
        assertEquals(expected.size(), result.size());
        // TODO: above assertion should exit test if fail
        for (int i=0; i < expected.size(); i++) {
            Polyline p = expected.get(i);
            if (!p.equals(result.get(i)))
                fail();
        }
    }

}
