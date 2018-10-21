import org.junit.Test;

public class StringTest {
    @Test
    public void test(){
        String target = "/www/ftproot/preview/upload/test.jpg";
        String[] fullName = target.split("\\/");
        String[] temp = fullName[fullName.length-1].split("\\.");
        String fileName = temp[0];
        String fileFormat = temp[1];
    }
}
