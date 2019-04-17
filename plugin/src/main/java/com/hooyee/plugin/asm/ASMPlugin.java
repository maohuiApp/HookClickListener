package com.hooyee.plugin.asm;

import com.android.build.api.dsl.extension.AppExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.util.Collections;

/**
 * @author maohui
 * @date Created on 2019/4/17.
 * @description
 * @added
 */

public class ASMPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        System.out.println("ASMPlugin ASMTransformtest");
//        AppExtension appExtension = (AppExtension)project.getProperties().get("android");
//        appExtension.registerTransform(new ASMTransform(), Collections.EMPTY_LIST);
//        System.out.println("ASMPlugin test");
    }
}
