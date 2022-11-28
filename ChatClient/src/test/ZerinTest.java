package test;

import com.zerin.chatclient.service.FileClientService;
import org.junit.Test;

import java.io.*;

public class ZerinTest {
    @Test
    public void test01() throws IOException {
        System.out.println("test");
        String src = "d:\\fuck.txt";
        String des = "d:\\fk.txt";

        // 创建空壳
        byte[] fileBytes = new byte[(int) new File(src).length()];
        // 创建文件输入流 绑定从src处读取文件
        FileInputStream fis = new FileInputStream(src);
        // 将src处的文件信息写入到空壳中去
        fis.read(fileBytes);
        // 创建文件输出流 绑定输出到des处
        FileOutputStream fos = new FileOutputStream(des);
        // 将空壳+信息写入到des处
        fos.write(fileBytes);
        fos.close();
        System.out.println("fuck");
    }
}

//        Message msg = new Message();
//        msg.setFileBytes(fileBytes);