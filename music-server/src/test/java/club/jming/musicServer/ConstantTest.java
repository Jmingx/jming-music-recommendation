package club.jming.musicServer;

import org.junit.jupiter.api.Test;

import java.io.File;

public class ConstantTest {

    public static String path ;

    static {
        path = new File(System.getProperty("user.dir")).getParent();
    }

    @Test
    public void test(){
        System.out.println(path);
    }
}
