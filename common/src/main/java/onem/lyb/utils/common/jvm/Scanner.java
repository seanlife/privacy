/*
 * www.yiji.com Inc.
 * Copyright (c) 2016 All Rights Reserved
 */
/*
 * 修订记录:
 * kshujun@yiji.com 2016/11/24 14:57 创建
 *
 */
package onem.lyb.utils.common.jvm;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @author kshujun(kshujun@yiji.com)
 * @version 1.0
 * @date 2016/11/24
 */
public interface Scanner {
    public static final String JAR_PRE = "jar:file:";

    public static final String JAR_SUF = "!/";

    /**
     * 扫描路径为当前classpath
     * @return
     */
    <T> Set<T> scan();

    /**
     * 扫描路径为当前classpath,并增加回调类型处理，用户对扫描结果进行处理，并返回。
     * @return
     */
    <T> Set<T> scan(ClassProcessCallback callback);

    Set<String> classFiles();

    Scanner acceptableClassName(String... classNames);

    Scanner acceptablePackagePath(String... packagePaths);

    Scanner acceptableJarName(String... jarNames);

    Scanner annotation(Class<? extends Annotation>... annotations);

    Scanner init();
}
