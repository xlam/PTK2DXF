package sergsm.ptk2dxf;

/**
 *
 * @author Sergey
 */
public class Version {
    private static final String VERSION_MAJOR = "1";
    private static final String VERSION_MINOR = "0";
    private static final String VERSION_BUILD = "0";
    private static final String VERSION_STAGE = "";

    public static final String getVersion() {
        return
            VERSION_MAJOR + "." +
            VERSION_MINOR + "." +
            VERSION_BUILD +
            (VERSION_STAGE.isEmpty() ? "" : "-" + VERSION_STAGE);
    }
}
