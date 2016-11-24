/*
 * www.yiji.com Inc.
 * Copyright (c) 2016 All Rights Reserved
 */
/*
 * 修订记录:
 * kshujun@yiji.com 2016/11/24 15:14 创建
 *
 */
package onem.lyb.utils.common.jvm;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author kshujun(kshujun@yiji.com)
 * @version 1.0
 * @date 2016/11/24
 */
public class StandardScanner extends AbstractScanner {
    public <T> Set<T> scan(ClassProcessCallback callback) {
        //- 加载类、分析注解、处理回调
        Set<T> targets = new HashSet<T>();

        for(Iterator<String> it = classFiles().iterator(); it.hasNext() ;){
            String className = it.next();
            try {
                Class<?> type = Thread.currentThread().getContextClassLoader().loadClass(className);

                Set<Class<? extends Annotation>> annotationClasses = getAnnotationClasses();

                if(annotationClasses != null && annotationClasses.size() != 0){
                    for(Class<? extends Annotation> annotationClass : annotationClasses){
                        if(type.isAnnotationPresent(annotationClass)){
                            targets.add(callback != null ? (T) callback.execute(type) : (T) type);
                        }
                    }
                    continue;
                }

                targets.add(callback != null ? (T) callback.execute(type) : (T) type);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return targets;
    }
}
