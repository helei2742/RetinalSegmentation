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

    public void useSegmentationScript(String srcPath,String savePath) throws IOException, InterruptedException{
        System.out.println(scriptLocation+"\n"+appendFileLct);

        PrintWriter fw;
        Process proc;
        try {
            fw = new PrintWriter(appendFileLct);
            fw.write("");
            fw.println(srcPath + " " + maskFileLct + " " + savePath);
            fw.flush();

            proc = Runtime.getRuntime().exec("python " + scriptLocation);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

            in.close();
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            throw e;
        }
    }
}
