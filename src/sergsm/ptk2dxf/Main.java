package sergsm.ptk2dxf;

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

        Converter c = new Converter(args[0]);
        System.out.println("Converting [" + args[0] + "] -> [" + c.constructDXFFilename(args[0]) + "]...");
        c.convert();
        System.out.println("Converting finished.\n");

    }
}
