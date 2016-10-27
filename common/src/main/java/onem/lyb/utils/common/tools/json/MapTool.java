/*
 * www.yiji.com Inc.
 * Copyright (c) 2016 All Rights Reserved
 */
/*
 * 修订记录:
 * kshujun@yiji.com 2016/9/1 10:21 创建
 *
 */
package onem.lyb.utils.common.tools.json;


import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @Version 1.0
 * @Auther kshujun(kshujun@yiji.com)
 * @date 2016/9/1
 */
public class MapTool {
    public static List<Map<String, String>> parseJSON2List(String jsonStr) {
        JSONArray jsonArr = JSONArray.parseArray(jsonStr);
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Iterator<Object> it = jsonArr.iterator();
        while (it.hasNext()) {
            Object json2 = it.next();
            list.add(parseJSON2Map(json2.toString()));
        }
        return list;
    }


    public static Map<String, String> parseJSON2Map(String jsonStr) {
        Map<String, String> map = new HashMap<String, String>();
        //最外层解析
        if (jsonStr!=null) {
            JSONObject json = JSONObject.parseObject(jsonStr);
            Set<Map.Entry<String, Object>> entryKeyIterator =json.entrySet();
            for (Map.Entry entry : entryKeyIterator) {
                map.put((String)entry.getKey(),(String)entry.getValue());
            }
        }
        return map;
    }


    public static void main(String[] s){
        Map<String,String> hash=parseJSON2Map("{\"lastName\":\"4545454\",\"name\":\"Li\"}");
        System.out.println(hash.toString());
    }
}


