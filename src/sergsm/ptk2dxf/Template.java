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
    private boolean update = true;

    public Template(String filename) {
        this.filename = filename;
    }

    public void set(String name, String value) {
        vars.put(name, value);
        update = true;
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
        } catch (IOException e) {
            System.err.println("ERROR: Unable to read template '" + getFileName() + "'");
            System.err.println("MESSAGE: " + e.getMessage());
        }
    }

    private String getFileName() {
        return Const.TEMPLATE_DIR + filename + Const.TEMPLATE_EXT;
    }

    public void render() {
        readTemplate();
        String result = input.toString();

        Set<Map.Entry<String, String>> varsSet = vars.entrySet();
        for (Map.Entry<String, String> var: varsSet) {
            result = result.replaceAll(getPlaceholder(var.getKey()), var.getValue());
        }

        this.result = result;
        update = false;
    }

    private String getPlaceholder(String var) {
        return Const.VAR_LEFT_BRACKET + var + Const.VAR_RIGHT_BRACKET;
    }

    @Override
    public String toString() {
        if (null == result || update) {
            render();
        }
        return result;
    }
}
