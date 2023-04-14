package org.helei.retinalsegmentation.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class PythonService {

    @Value("${helei.script.location}")
    private String scriptLocation;

    @Value("${helei.script.appendFile}")
    private String appendFileLct;

    @Value("${helei.script.maskFile}")
    private String maskFileLct;

    @Value("${helei.script.pythonLct}")
    private String pythonLct;

    public int useSegmentationScript(String srcPath,String savePath) throws IOException, InterruptedException{
        PrintWriter fw;
        Process proc;
        try {
            fw = new PrintWriter(appendFileLct);
            fw.write("");
            fw.println(srcPath + " " + maskFileLct + " " + savePath);
            fw.flush();

            String[] args = new String[] { pythonLct, scriptLocation};
            proc = Runtime.getRuntime().exec(args);
            new Thread() {
                @Override
                public void run() {
                    String line;
                    try (BufferedReader stderr = new BufferedReader(new InputStreamReader(proc.getErrorStream()))){
                        while ((line = stderr.readLine()) != null) {
                            System.out.println("stderr:" + line);
                        }
                    }
                    catch (Exception e) {

                    }
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    String line;
                    try ( BufferedReader stdout = new BufferedReader(new InputStreamReader(proc.getInputStream()))){
                        while ((line = stdout.readLine()) != null) {
                            System.out.println("stdout:" + line);
                        }
                    }
                    catch (Exception e) {

                    }
                }
            }.start();
            return proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
