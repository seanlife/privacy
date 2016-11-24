/*
 * www.yiji.com Inc.
 * Copyright (c) 2016 All Rights Reserved
 */
/*
 * 修订记录:
 * kshujun@yiji.com 2016/11/23 18:24 创建
 *
 */
package onem.lyb.utils.common.jvm;

import java.util.Set;

/**
 * @author kshujun(kshujun@yiji.com)
 * @version 1.0
 * @date 2016/11/23
 */
public class ClassLoaderTest {

    public static void main(String[] strings) throws Exception {

        Set<Class<?>> classes = new StandardScanner().acceptableJarName("ecj-4.2.2.jar").acceptablePackagePath("org.eclipse.jdt.internal.compiler.apt.dispatch").init().scan();

        print(classes.toString());
    }

    static void print(String s) {
        System.out.println(s);
    }

}
