package sergsm.ptk2dxf;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses CSV file into array of polylines
 * @author Sergey
 */
public class Parser {
    private final String CSVFileName = "./workdir/table.csv";

    public Parser() {

    }

    public List parse() {
        List polylines = new ArrayList();
        List vertexes = new ArrayList();
        vertexes.add(new Vertex(10, 20));
        vertexes.add(new Vertex(20, 30));
        polylines.add(vertexes);
        return polylines;
    }
}
