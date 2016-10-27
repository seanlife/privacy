/*
 * www.yiji.com Inc.
 * Copyright (c) 2016 All Rights Reserved
 */
/*
 * 修订记录:
 * kshujun@yiji.com 2016/9/19 11:05 创建
 *
 */
package onem.lyb.utils.common.tools;

import java.util.Map;

/**
 * @Version 1.0
 * @Auther kshujun(kshujun@yiji.com)
 * @date 2016/9/19
 */
public class MapBeanTools {

    public static Object mapToObject(Map<String, String> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        org.apache.commons.beanutils.BeanUtils.populate(obj, map);

        return obj;
    }

    public static Map<?, ?> objectToMap(Object obj) {
        if(obj == null)
            return null;

        return new org.apache.commons.beanutils.BeanMap(obj);
    }
}
