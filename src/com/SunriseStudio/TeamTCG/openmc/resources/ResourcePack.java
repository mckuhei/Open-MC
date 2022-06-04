package com.SunriseStudio.TeamTCG.openmc.resources;

import com.SunriseStudio.TeamTCG.openmc.GameSetting;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ResourcePack {
    private ZipFile file;
    private ArrayList<ZipEntry> entries=new ArrayList<>();

    /**
     * load file from disk，read all entries to file path
     * @param filePath abs path
     */
    public void loadAndDecompressToTemp(String filePath) {
        try {
            filePath= GameSetting.instance.gamePath+"/resourcepacks/"+filePath;
            this.file = new ZipFile(new File(filePath));
            this.entries.clear();
            ZipInputStream inputStream=new ZipInputStream(new FileInputStream(filePath));
            ZipEntry e;
            while ((e= inputStream.getNextEntry())!=null){
                this.entries.add(e);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(ZipEntry entry:this.entries){
            if(!entry.isDirectory()) {
                File file = new File(GameSetting.instance.gamePath + "/resourcepacks/temp/" + entry.getName());
                try {
                    file.getParentFile().mkdirs();
                    OutputStream outputStream = new FileOutputStream(file);
                    InputStream stream = this.file.getInputStream(entry);
                    int i = 0;
                    while (((i = stream.read()) != -1)) {
                        outputStream.write(i);
                    }
                    stream.close();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        ResourcePack az=new ResourcePack();
        az.loadAndDecompressToTemp("default.zip");

    }
}