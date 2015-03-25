package sergsm.ptk2dxf;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sergey
 */
public class TemplateTest {

    public TemplateTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testTemplate() {
        String expected = ""
                + "  0\n"
                + "VERTEX\n"
                + "  8\n"
                + "Top_Layer\n"
                + "  10\n"
                + "10.0\n"
                + "  20\n"
                + "20.0\n"
                + "  42\n"
                + "0\n";

        Template t = new Template("vertex");
        t.set("POINT_X", "10.0");
        t.set("POINT_Y", "20.0");
        assertEquals(expected, t.toString());
    }

}
