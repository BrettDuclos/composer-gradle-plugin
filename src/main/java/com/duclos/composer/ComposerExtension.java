package com.duclos.composer;

import org.gradle.api.Project;

import java.util.List;

public class ComposerExtension {

    private String source;
    private List<String> modules;

    public ComposerExtension(Project project) {

    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<String> getModules() {
        return modules;
    }

    public void setModules(List<String> modules) {
        this.modules = modules;
    }
}
