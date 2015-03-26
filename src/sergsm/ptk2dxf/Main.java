package sergsm.ptk2dxf;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Sergey
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("PTK2DXF v" + Version.getVersion() + ".\n");
        // Need to add arguments handling stuff (later maybe...)
        if (args.length == 0) {
            System.out.println("Provide filename");
            System.exit(0);
        }

        String dxfFileName = args[0] + ".dxf";
        System.out.println("Converting [" + args[0] + "] -> [" + dxfFileName + "]...");

        Parser csvParser = new Parser();
        csvParser.setCSVFileName(args[0]);
        ArrayList<Polyline> polylines = csvParser.parse();

        try {
            FileWriter w = new FileWriter(new File(dxfFileName));
            Template headerTpl = new Template("header");
            Template footerTpl = new Template("footer");
            Template vertexTpl = new Template("vertex");
            Template polylineTpl = new Template("polyline");

            w.write(headerTpl.toString());

            for (Polyline polyline: polylines) {
                String vertex_array = "";
                for (Vertex vertex: polyline.getVertices()) {
                    // TODO: int to string convertion and formatting
                    //System.out.println(vertex.getX() + ", " + vertex.getY() + "\n");
                    vertexTpl.set("POINT_X", "" + vertex.getX() + ".0");
                    vertexTpl.set("POINT_Y", "" + vertex.getY() + ".0");
                    vertex_array += vertexTpl.toString();
                }
                polylineTpl.set("VERTEX_ARRAY", vertex_array);
                w.write(polylineTpl.toString());
            }

            w.write(footerTpl.toString());

            w.close();
        } catch (IOException e) {};
    }

}
