package com.duclos.composer;


import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class ComposerPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        ComposerExtension extension = project.getExtensions().create("composer", ComposerExtension.class, project);

        project.getTasks().create("compose", ComposerTask.class, composerTask -> {
            composerTask.setModules(extension.getModules());
            composerTask.setSource(extension.getSource());
        });
    }
}
