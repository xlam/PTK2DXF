package sergsm.ptk2dxf;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Sergey
 */
class Converter {

    private String filename;
    private Parser csvParser = new Parser();

    public Converter(String filename) {
        this.filename = filename;
    }

    public void convert() {
        csvParser.setCSVFileName(filename);
        ArrayList<Polyline> polylines = csvParser.parse();
        csvParser.printStats();

        try {
            FileWriter w = new FileWriter(new File(constructDXFFilename(filename)));
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

    public String constructDXFFilename(String filename) {
        int index = filename.lastIndexOf('.');
        if (index < 0)
            return filename + Const.DXF_EXT;
        return filename.substring(0, index) + Const.DXF_EXT;
    }

}
