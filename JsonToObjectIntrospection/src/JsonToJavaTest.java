import org.junit.Test;
import org.junit.Assert;
import java.io.File;
import java.io.IOException;

public class JsonToJavaTest {
    private final JsonToJava jsonToJava = new JsonToJava();

    @Test
    public void testDumpEmptyJsonToNull() throws IOException {
        File f = new File("C:\\Users\\naelm\\Documents\\GitHub\\tpcsid\\JsonToObjectIntrospection\\src\\emptyJson.json");
        Object obj = jsonToJava.dumpJsonToObject(f);
        Assert.assertNull(obj);
    }

    @Test
    public void testDumpStringJsonToString() throws IOException {
        File f = new File("C:\\Users\\naelm\\Documents\\GitHub\\tpcsid\\JsonToObjectIntrospection\\src\\stringJson.json");
        Object obj = jsonToJava.dumpJsonToObject(f);
        Assert.assertEquals("thisisastring", obj);
    }

    @Test
    public void testDumpIntJsonToInt() throws IOException {
        File f = new File("C:\\Users\\naelm\\Documents\\GitHub\\tpcsid\\JsonToObjectIntrospection\\src\\intJson.json");
        Object obj = jsonToJava.dumpJsonToObject(f);
        Assert.assertEquals(99, obj);
    }

    @Test
    public void testDumpObjJsonToObject() throws IOException {
        File f = new File("C:\\Users\\naelm\\Documents\\GitHub\\tpcsid\\JsonToObjectIntrospection\\src\\file.json");
        Object obj = jsonToJava.dumpJsonToObject(f);
        Human nael = new Human("Nael", "Mezdari");
        Assert.assertEquals(nael.toString(), obj.toString());
    }
}