import java.io.*;

public class test {

    public  static  void  main(String[]  args) throws FileNotFoundException {
        String srcFile="D:/qycache/download/壮志高飞/壮志高飞第1集-蓝光4K.qsv";
        String desFile="c:/new/456.qsv";

        copyFile1(srcFile,desFile);

    }

    private static void copyFile1(String srcFile, String desFile) {
        FileInputStream fis=null;
        FileOutputStream fos=null;
        try {
            fis=new FileInputStream(srcFile);
            fos=new FileOutputStream(desFile);
            byte[] b = new byte[10240];
            while (fis.read(b)!=-1){
                fos.write(b,0,b.length);
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
