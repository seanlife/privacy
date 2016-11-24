/*
 * www.yiji.com Inc.
 * Copyright (c) 2016 All Rights Reserved
 */
/*
 * 修订记录:
 * kshujun@yiji.com 2016/11/24 15:10 创建
 *
 */
package onem.lyb.utils.common.jvm;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.jar.JarEntry;

/**
 * @author kshujun(kshujun@yiji.com)
 * @version 1.0
 * @date 2016/11/24
 */
public abstract class AbstractScanner implements Scanner {
    private ScannerBuilder scannerBuilder = new ScannerBuilder();

    private Set<String> acceptableClassNames = new HashSet<String>();

    private Set<String> acceptablePackages = new HashSet<String>();

    private Set<String> acceptableJarNames = new HashSet<String>();

    private Set<Class<? extends Annotation>> annotationClasses = new HashSet<Class<? extends Annotation>>();

    private Set<String> classFiles;

    public <T> Set<T> scan() {
        return (Set<T>) scan(null);
    }

    public Scanner acceptableClassName(String... classNames) {
        for (String className : classNames) {
            scannerBuilder.acceptableClassNames.add(className);
        }
        return this;
    }

    public Scanner acceptablePackagePath(String... packagePaths) {
        for (String packagePath : packagePaths) {
            scannerBuilder.acceptablePackages.add(packagePath);
        }
        return this;
    }

    public Scanner acceptableJarName(String... jarNames) {
        for (String jarName : jarNames) {
            scannerBuilder.acceptableJarNames.add(jarName);
        }
        return this;
    }

    public Scanner annotation(Class<? extends Annotation>... annotations) {
        for (Class<? extends Annotation> annotationClass : annotations) {
            scannerBuilder.annotationClasses.add(annotationClass);
        }
        return this;
    }

    public Scanner init() {
        scannerBuilder.build(this);
        //- 初始化classpath下需要扫描的jar包
        Set<URL> scan = initScan();
        //- jar包过滤
        filterJarFile(scan);
        //- 扫描分析
        this.classFiles = scanJarsResolve(scan);

        return this;
    }

    private Set<URL> initScan() {
        Set<URL> scan = new HashSet<URL>();

        String classPaths = System.getProperty("java.class.path");

        for (String classpath : classPaths.split(System.getProperty("path.separator"))) {
            if (classpath.endsWith("jar")) {//只处理jar包
                try {
                    scan.add(new URL(StandardScanner.JAR_PRE + classpath + StandardScanner.JAR_SUF));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(String.format("非法的URL路径", classpath), e);
                }
            }
        }

        return scan;
    }

    private void filterJarFile(Set<URL> scan) {
        Set<String> jarFileNames = getAcceptableJarNames();

        if (jarFileNames != null && jarFileNames.size() > 0) {
            for (Iterator<URL> it = scan.iterator(); it.hasNext(); ) {
                URL targetURL = it.next();
                boolean isDelete = true;

                Iterator<String> acceptableJarNamesIt = getAcceptableJarNames().iterator();
                while (acceptableJarNamesIt.hasNext()) {
                    String acceptableJarName = acceptableJarNamesIt.next();

                    if (targetURL.getFile().indexOf(acceptableJarName) != -1) { //存在！不删除。
                        isDelete = false;
                        break;
                    }
                }

                if (isDelete) {
                    it.remove();
                }
            }
        }
    }

    private Set<String> scanJarsResolve(Set<URL> scan) {

        Set<String> classNames = new HashSet<String>();

        //-1. 遍历所有jar包
        for (URL url : scan) {
            try {
                JarURLConnection conn = (JarURLConnection) url.openConnection();

                Enumeration<JarEntry> entries = conn.getJarFile().entries();

                //-2. 循环所有jar包中的entry元素
                while (entries.hasMoreElements()) {
                    //-3. 匹配以.class结尾的文件
                    String fileName = entries.nextElement().getName();
                    if (fileName.endsWith(".class")) {
                        //-4. 根据acceptablePackages匹配符合包&&根据acceptableClassName匹配符合的class文件
                        String className = fileName.substring(0, fileName.indexOf(".class")).replace("/", ".");

                        if (filterPackage(className) && filterClass(className)) {
                            classNames.add(className);
                        }
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(String.format("非法的URL路径，url=%s", url), e);
            }
        }

        return classNames;
    }

    private boolean filterClass(String className) {

        Set<String> classNames = getAcceptableClassNames();

        if (classNames != null && classNames.size() > 0) {
            for (String clazzName : classNames) {
                if (clazzName.equals(className)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    private boolean filterPackage(String className) {
        Set<String> packages = getAcceptablePackages();

        if (packages != null && packages.size() > 0) {
            for (String pkg : packages) {
                if (className.startsWith(pkg)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public Set<String> getAcceptableClassNames() {
        return acceptableClassNames;
    }

    public Set<String> getAcceptablePackages() {
        return acceptablePackages;
    }

    public Set<String> getAcceptableJarNames() {
        return acceptableJarNames;
    }

    public Set<Class<? extends Annotation>> getAnnotationClasses() {
        return annotationClasses;
    }

    public Set<String> classFiles() {
        return classFiles;
    }

    private static class ScannerBuilder {

        private Set<String> acceptableClassNames = new HashSet<String>();

        private Set<String> acceptablePackages = new HashSet<String>();

        private Set<String> acceptableJarNames = new HashSet<String>();

        private Set<Class<? extends Annotation>> annotationClasses = new HashSet<Class<? extends Annotation>>();

        public void build(AbstractScanner abstractScanner) {
            abstractScanner.acceptableClassNames = this.acceptableClassNames;
            abstractScanner.acceptableJarNames = this.acceptableJarNames;
            abstractScanner.acceptablePackages = this.acceptablePackages;
            abstractScanner.annotationClasses = this.annotationClasses;
        }

    }
}
