package sergsm.ptk2dxf;

import java.util.HashMap;

/**
 *
 * @author Sergey
 */
public class Template {

    private String filename;
    private HashMap<String, String> vars = new HashMap();

    public Template(String filename) {
        this.filename = filename;
    }

    public void set(String name, String value) {
        vars.put(name, value);
    }

    @Override
    public String toString() {
        return ""
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
    }
}
