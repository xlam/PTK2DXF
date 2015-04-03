package sergsm.ptk2dxf;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

/**
 *
 * @author Sergey
 */
class Converter {

    private String filename;
    private Parser csvParser = new Parser();
    private int scale = 1;

    public Converter(String filename) {
        this.filename = filename;
    }

    public void convert() {
        csvParser.setCSVFileName(filename);
        ArrayList<Polyline> polylines = csvParser.parse();
        csvParser.printStats();
        checkPolylines(polylines);

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
                    vertexTpl.set("POINT_X", String.format(Locale.US, "%1$#.2f", (double)vertex.getX() / scale));
                    vertexTpl.set("POINT_Y", String.format(Locale.US, "%1$#.2f", (double)vertex.getY() / scale));
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

    public void setScale(int scale) {
        this.scale = Math.abs(scale);
    }

    public int getScale() {
        return scale;
    }

    public void checkPolylines(ArrayList<Polyline> polylines) {
        for (Polyline pl: polylines) {
            Vertex v1 = pl.get(0);
            Vertex v2 = pl.get(pl.size()-1);
            if (!v1.equals(v2)) {
                System.out.println("WARNING! Polyline not closed at: " + v2.toString());
            }
        }
    }
}
