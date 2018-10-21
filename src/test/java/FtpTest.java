
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;

public class FtpTest {
    public String hostname = "106.15.207.141";
    //ftp服务器端口号默认为21
    public Integer port = 21 ;
    //ftp登录账号
    public String username = "ftpadmin";
    //ftp登录密码
    public String password = "ftpadmin";

    public FTPClient ftpClient = null;
    @Test
    public void testFtpClient() throws IOException {

//        FTPClient ftpClient = new FTPClient();
//        ftpClient.connect("106.15.207.141", 21);//服务器地址和端口
//        ftpClient.login("ftpadmin", "ftpadmin");//登录的用户名和密码
//        //读取本地文件，给出的是本地文件地址
//        FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\Wismo_Developer\\Desktop\\902065103901826445.jpg"));
//        //设置上传路径
//        ftpClient.changeWorkingDirectory("/www/ftproot/upload");
//        //设置文件类型
//        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//        //1.服务器端保存的文件名，2.上传文件的inputstream
//        ftpClient.storeFile("2018.jpg", inputStream);
//        ftpClient.listDirectories();
//
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        try {
            System.out.println("connecting...ftp服务器:"+this.hostname+":"+this.port);
            ftpClient.connect(hostname, port); //连接ftp服务器
            ftpClient.login(username, password); //登录ftp服务器
            int replyCode = ftpClient.getReplyCode(); //是否成功登录服务器
            if(!FTPReply.isPositiveCompletion(replyCode)){
                System.out.println("connect failed...ftp服务器:"+this.hostname+":"+this.port);
            }
            System.out.println("connect successfu...ftp服务器:"+this.hostname+":"+this.port);
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

//    public static boolean doUpLoad(String ftpPath, File file) {
//        boolean result = false;
//        Integer i = 0;
//        while (!result) {
//            try {
//                try {
//                    client.changeDirectory(ftpPath);
//
//
//                } catch (Exception e) {
//                    LOGGER.error("ftp文件目录不存在:" + ftpPath);
//                }
//                client.upload(file);
//                if (i > 0) {
//                    LOGGER.info("ftp重试文件上传成功，ftp路径:" + ftpPath + ",文件名称:" + file.getName());
//                } else {
//                    LOGGER.info("ftp文件上传成功，ftp路径为" + ftpPath + ",文件名称:" + file.getName());
//                }
//                result = true;
//            } catch (Exception e) {
//                i++;
//                LOGGER.error("ftp文件上传失败，重试中。。。第" + i + "次，错误信息" + e.getMessage());
//                if (i > uploadRettry) {
//                    LOGGER.error("ftp文件上传失败，超过重试次数结束重试，错误信息" + e.getMessage());
//                    return result;
//                }
//                try {
//                    TimeUnit.MILLISECONDS.sleep(uploadSleep);
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        }
//
//
//        return result;
//    }


}
