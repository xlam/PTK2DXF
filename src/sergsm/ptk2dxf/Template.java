package sergsm.ptk2dxf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Sergey
 */
public class Template {

    private String filename;
    private HashMap<String, String> vars = new HashMap();
    private StringBuilder stringBuilder = null;

    public Template(String filename) {
        this.filename = filename;
    }

    public void set(String name, String value) {
        vars.put(name, value);
    }

    private void readTemplate() {
        if (stringBuilder != null) return;
        try {
            BufferedReader r = new BufferedReader(new FileReader("./templates/" + filename + ".dtpl"));
            while (r.ready()) {
                stringBuilder.append(r.readLine());
            }
            r.close();
        } catch (IOException e) {}
    }

    public void render() {
        readTemplate();
        String result = stringBuilder.toString();

        result = result.replaceAll("%%POINT_X%%", "10.0");
        result = result.replaceAll("%%POINT_Y%%", "20.0");
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
