package sergsm.ptk2dxf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Parses CSV file into array of polylines
 * @author Sergey
 */
public class Parser {

    private final String delimeter = ";";
    private String csvFileName;
    private int linesCount = 0;
    private int errorLinesCount = 0;
    private int polylinesCount = 0;
    public Parser() {

    }

    public ArrayList parse() {

        ArrayList polylines = new ArrayList();
        Polyline p = new Polyline();
        int x;
        int y;
        String prevX = "";
        String prevY = "";
        linesCount = 0;
        polylinesCount = 0;
        errorLinesCount = 0;

        try {
            BufferedReader r = new BufferedReader(new FileReader(csvFileName));
            while (r.ready()) {
                linesCount++;
                String line = r.readLine();
                if (lineHasErrors(line)) {
                    System.err.println("ERROR: Skipping error line " + linesCount + ": \"" + line + "\"");
                    continue;
                }
                String[] data = line.split(delimeter, 4);

                if (!data[0].isEmpty()) {
                    polylinesCount++;
                    p = new Polyline();
                    polylines.add(p);
                    prevX = "";
                    prevY = "";
                }

                if (data[2].isEmpty()) {
                    if (prevX.isEmpty()) {
                        errorLinesCount++;
                        System.err.println("ERROR: (" + linesCount + ") missing X: \"" + line + "\"");
                        continue;
                    }
                    x = Integer.decode(prevX);
                } else {
                    x = Integer.decode(data[2]);
                }
                prevX = data[2];

                if (data[3].isEmpty()) {
                    if (prevY.isEmpty()) {
                        errorLinesCount++;
                        System.err.println("ERROR: (" + linesCount + ") missing Y: \"" + line + "\"");
                        continue;
                    }
                    y = Integer.decode(prevY);
                } else {
                    y = Integer.decode(data[3]);
                }
                prevY = data[3];

                p.add(new Vertex(x, y));

            }
            r.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return polylines;
    }

    public void printStats() {
        System.out.println("Lines processed: " + linesCount);
        System.out.println("Polylines: " + polylinesCount);
        System.out.println("Error lines: " + errorLinesCount);
    }

    public boolean lineHasErrors(String line) {
        if (3 != countDelimeters(line)) {
            errorLinesCount++;
            return true;
        }
        return false;
    }

    public int countDelimeters(String str) {
        int count = 0;
        for (char c: str.toCharArray()) if (c == delimeter.charAt(0)) count++;
        return count;
    }

    public void setCSVFileName(String csvFileName) {
        this.csvFileName = csvFileName;
    }
}
