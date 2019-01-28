package com.ev.core;

import com.ev.annoation.Action;
import com.ev.entity.SysLogSwtich;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

/**
 * @author
 * @create 2019-01-27 上午11:44
 **/
@Slf4j
public class PackageScanner {

    private static Class[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList();
        while (resources.hasMoreElements()) {
            URL resource = (URL) resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList classes = new ArrayList();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return (Class[]) classes.toArray(new Class[classes.size()]);
    }
    private static List findClasses(File directory, String packageName) throws ClassNotFoundException {
        List classes = new ArrayList();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }


    public static List<SysLogSwtich> doScan(String packageName){
        List<SysLogSwtich> sysLogSwtiches = new ArrayList<SysLogSwtich>();
        Class[] classes = new Class[0];
        try {
            classes = getClasses("com.ev.controller");
            System.out.println(classes);
            for (Class control:classes) {
                Action action = (Action) control.getAnnotation(Action.class);
                if (action==null){
                    continue;
                }
                Method[] methods = control.getMethods();
                for (Method method :methods) {
                    Action meAction= (Action)method.getAnnotation(Action.class);
                    if (meAction==null){
                        continue;
                    }else {
                        SysLogSwtich sysLogSwtich=new SysLogSwtich();
                        sysLogSwtich.setClassName(control.getName());
                        sysLogSwtich.setModelName(action.modelName().toString());
                        sysLogSwtich.setDescrption(meAction.description());
                        sysLogSwtich.setIsEnable(meAction.isEnable());
                        sysLogSwtich.setMethodName(method.getName());
                        sysLogSwtiches.add(sysLogSwtich);
                    }
                }

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sysLogSwtiches;
    }

}
