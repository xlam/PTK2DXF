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
    private StringBuilder stringBuilder = null;
    private String result = null;

    public Template(String filename) {
        this.filename = filename;
    }

    public void set(String name, String value) {
        vars.put(name, value);
    }

    private void readTemplate() {
        if (stringBuilder != null) return;
        try {
            stringBuilder = new StringBuilder();
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

        Set<Map.Entry<String, String>> varsSet = vars.entrySet();
        for (Map.Entry<String, String> var: varsSet) {
            result = result.replaceAll("%%" +var.getKey() + "%%", var.getValue());
        }
        
        this.result = result;
        
//        result = result.replaceAll("%%POINT_X%%", "10.0");
//        result = result.replaceAll("%%POINT_Y%%", "20.0");
    }

    @Override
    public String toString() {
        if (null == result) {
            render();
        }
        System.out.println(result);
        return result;
    }
}
