package com.jylee.util;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class JarClassLoader extends ClassLoader {
    static Log logger = LogFactory.getLog(JarClassLoader.class);
    
    private List<String> loadedClasses;

    public JarClassLoader(String jarPath) throws FileNotFoundException,IOException {
        loadedClasses = new LinkedList<String>();
        loadClasses(jarPath);
    }

    public List<String> getLoadedClasses() {
        return loadedClasses;
    }

    /**
     * 주어진 경로의 jar 파일을 읽어 파일 내의 class를 loading한다.
     * @param jarFilePath               class를 loading할 jar 파일 경로
     * @throws FileNotFoundException    주어진 jar 파일이 존재하지 않는 경우
     * @throws IOException              jar 파일을 열거나 읽을 때 IOException이 발생하는 경우
     */
    private void loadClasses(String jarFilePath) throws FileNotFoundException, IOException {
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(jarFilePath);

            Enumeration<JarEntry> entries = jarFile.entries();
            
            JarEntry entry = null;
            while (entries.hasMoreElements()) {
                entry = entries.nextElement();

                if (entry.getName().endsWith(".class")) {
                    BufferedInputStream jarInputStream = null;
                    try {
                        String className = getClassName(entry);

                        jarInputStream = new BufferedInputStream(jarFile.getInputStream(entry));
                        byte[] data = new byte[(int) entry.getSize()];

                        jarInputStream.read(data);
                        defineClass(className, data, 0, data.length);
                        this.loadedClasses.add(className);
                    } // end of try
                    finally {
                        if (null != jarInputStream)
                            try {
                                jarInputStream.close();
                            } catch (Exception e) {/* ignore */
                            } finally {
                                jarInputStream = null;
                            }
                    }
                } // end of if class 파일만..
            } // end of while jar 파일에 있는 모든 파일에 대해
        } // end try
        finally {
            if (jarFile != null)
                try {
                    jarFile.close();
                } catch (Exception e) { /* ignore */
                } finally {
                    jarFile = null;
                }
        }
    }

    private String getClassName(JarEntry classEntry) {
        return classEntry.getName().replace(".class", "").replace("/", ".");
    }
}









