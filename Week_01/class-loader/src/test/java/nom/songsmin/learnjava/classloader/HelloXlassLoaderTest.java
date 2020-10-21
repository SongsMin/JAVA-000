/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package nom.songsmin.learnjava.classloader;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class HelloXlassLoaderTest {
    @Test
    public void should_trans_xlass_to_class() {
        File xlassFile = new File(getClass().getResource("/Hello.xlass").getPath());
        HelloXlassLoader loader = new HelloXlassLoader(xlassFile);
        byte[] content = loader.getBytes();
        File classFile = new File(getClass().getResource("/Hello.class").getPath());
        int len = (int) classFile.length();
        byte[] want = new byte[len];
        try (InputStream classIs = new FileInputStream(classFile)) {
            classIs.read(want, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(want.length, content.length);
        for (int i = 0; i < want.length; i++) {
            assertEquals(want[i], content[i], "'Hello.xlass' should be transformed as same as 'Hello.class'.");
        }
    }
}
