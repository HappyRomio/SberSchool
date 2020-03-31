package com.sbt.homework.les6classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Enumeration;

public class PluginManager extends ClassLoader {

    private final String pluginRootDirectory;
    private final String name;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
        this.name = "MyClassLoader";
    }

    public String getName(){
        return name;
    }

    public Class<?> load(String pluginName, String pluginClassName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String path = pluginRootDirectory + "." + pluginName + "." + pluginClassName;
        return loadClass(path);
    }


    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> result = findClass(name);
        if(resolve){
            resolveClass(result);
        }
        return result;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = findFile(name.replace(".","/") + ".class");
        if(file == null){
            return findSystemClass(name);
        }
        byte[] classFromFile = readFileToBytes(file);
        Class<?> result = defineClass(name.replace("D:\\",""),classFromFile,0,classFromFile.length);
        return result;
    }

    @Override
    protected URL findResource(String name){
        return null;
    }

    @Override
    protected Enumeration findResources(String name){
        return null;
    }

    public File findFile(String fileName) {
            File file = new File(fileName);
            if(file.exists()){
                return file;
            }
        return null;
    }

    public byte[] readFileToBytes(File f){

        byte[] result = new byte[(int)f.length()];

        try(FileInputStream fis = new FileInputStream(f)){
            fis.read(result,0,(int)f.length());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}

