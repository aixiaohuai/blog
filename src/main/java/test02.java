import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class test02 {
    //文件复制
    public static void main(String[] args) {
        File src=new File("C:\\Python27");
        File des=new File("C:\\new\\复制过来的");
        copyfiles(src,des);
    }

    private static void copyfiles(File src, File des) {
        if(!des.exists()){
            des.mkdirs();
        }
        File[] files = src.listFiles();
        for (File file : files) {
            //判断是否为文件
            if(file.isFile()){
                //复制文件
                copyfile(file,new File(des,file.getName()));
            }else {
                //文件夹
                copyfiles(file.getAbsoluteFile(),new File(des,file.getName()).getAbsoluteFile());
            }
        }
    }

    private static void copyfile(File src, File des) {
        FileInputStream fis=null;
        FileOutputStream fos=null;
        try {
            fis=new FileInputStream(src);
            fos=new FileOutputStream(des);
            byte[] content=new byte[1024];
            while(fis.read(content)!=-1){
                fos.write(content,0,content.length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
