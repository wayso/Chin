package output;

import java.io.*;

/** jdbc.properties書き換え用 */
public class PropertiesReplaceOutput extends AbstractReplaceOutput {
    static final String PROP_FILE = "src/main/resources/jdbc.properties";

    static final String JDBC_URL = "jdbc.url";
    static final String USER = "jdbc.username";
    static final String PASS = "jdbc.password";
    static final String SCHEMA = "schema.name";

    protected String getTargetFilePathName() {
        return PROP_FILE;
    }
    
    protected void replaceOutput(File inputFile,
                                 File outputFile,
                                 String jdbcUrl,
                                 String user,
                                 String password,
                                 String schema) throws Exception {
        BufferedReader fr = null;
        PrintWriter fw = null;
        try {
            fr = new BufferedReader(new FileReader(inputFile));
            fw = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));
            boolean isUrl = (jdbcUrl != null && jdbcUrl.length() > 0);
            String line;
            while((line = fr.readLine()) != null) {
                if (line.indexOf("#") == -1 && line.indexOf("rms") == -1) {
                    if (line.indexOf(JDBC_URL) != -1 && isUrl) {
                        line = JDBC_URL + "=" + jdbcUrl;
                    }
                    if (line.indexOf(USER) != -1) {
                        line = USER + "=" + user;
                    }
                    if (line.indexOf(PASS) != -1) {
                        line = PASS + "=" + password;
                    }
                    if (line.indexOf(SCHEMA) != -1) {
                        line = SCHEMA + "=" + schema;
                    }
                }
                fw.println(line);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (fr != null) try {fr.close();} catch(Exception e){}
            if (fw != null) fw.close();
        }
    }
}
