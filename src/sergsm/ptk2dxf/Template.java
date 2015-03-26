package sergsm.ptk2dxf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Sergey
 */
public class Template {

    private String filename;
    private HashMap<String, String> vars = new HashMap();
    private StringBuilder input = null;
    private String result = null;

    public Template(String filename) {
        this.filename = filename;
    }

    public void set(String name, String value) {
        vars.put(name, value);
    }

    private void readTemplate() {
        if (input != null) return;
        try {
            input = new StringBuilder();
            BufferedReader r = new BufferedReader(new FileReader(getFileName()));
            while (r.ready()) {
                input.append(r.readLine()).append("\n");
            }
            r.close();
        } catch (IOException e) {}
    }

    private String getFileName() {
        return PTKConst.TEMPLATE_DIR + filename + PTKConst.TEMPLATE_EXT;
    }

    public void render() {
        readTemplate();
        String result = input.toString();

        Set<Map.Entry<String, String>> varsSet = vars.entrySet();
        for (Map.Entry<String, String> var: varsSet) {
            result = result.replaceAll(getPlaceholder(var.getKey()), var.getValue());
        }

        this.result = result;
    }

    private String getPlaceholder(String var) {
        return PTKConst.VAR_LEFT_BRACKET + var + PTKConst.VAR_RIGHT_BRACKET;
    }

    @Override
    public String toString() {
        if (null == result) {
            render();
        }
        return result;
    }
}
