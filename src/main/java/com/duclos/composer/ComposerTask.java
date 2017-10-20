package com.duclos.composer;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ComposerTask extends DefaultTask {

    private String source;
    private List<String> modules;

    @Input
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Input
    public List<String> getModules() {
        return modules;
    }

    public void setModules(List<String> modules) {
        this.modules = modules;
    }

    @TaskAction
    public void compose() throws IOException {
        File sourceDir = new File(source);

        if (!sourceDir.isDirectory()) {
            return;
        }

        File buildFile = new File(Paths.get(".").toString() + "/build.gradle");
        if (buildFile.exists()) {
            buildFile.delete();
            buildFile.createNewFile();
        }

        for (String module : modules) {
            String gradleFileName = module + ".gradle";
            File sourceModule = new File(sourceDir, gradleFileName);

            FileInputStream fis = new FileInputStream(sourceModule);
            InputStreamReader isr = new InputStreamReader(fis);

            char[] chars = new char[(int) sourceModule.length()];
            isr.read(chars);

            FileWriter fw = new FileWriter(buildFile, true);
            fw.write(chars);
            fw.write("\n\n");
            fw.flush();

            isr.close();
            fis.close();
            fw.close();
        }
    }
}
