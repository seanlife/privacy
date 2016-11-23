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

/**
 * @author kshujun(kshujun@yiji.com)
 * @version 1.0
 * @date 2016/11/23
 */
public class ClassLoaderTest {

    public static void  main(String[]strings ){
        String path=System.getProperty("java.class.path");
        print("java.class.path "+path);

        String sepe=System.getProperty("path.separator");
        print("path.separator "+sepe);

    }

    static void print(String s){
        System.out.println(s);
    }

}
