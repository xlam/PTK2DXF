package sergsm.ptk2dxf;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sergey
 */
public class VertexTest {

    public VertexTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testVertexEquality() {
        Vertex v1 = new Vertex(10, 10);
        Vertex v2 = new Vertex(10, 10);
        assertTrue(v1.equals(v2));
    }
}
