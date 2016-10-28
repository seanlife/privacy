/*
 * www.yiji.com Inc.
 * Copyright (c) 2016 All Rights Reserved
 */
/*
 * 修订记录:
 * kshujun@yiji.com 2016/10/27 16:55 创建
 *
 */
package onem.lyb.utils.common.tools;

import java.lang.reflect.Field;
import java.util.*;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @Version 1.0
 * @Auther kshujun(kshujun@yiji.com)
 * @date 2016/10/27
 */
public class XmlTool {

    /**
     * 将xml格式的字符串转换成Map对象
     *
     * @param xmlStr xml格式的字符串
     * @return Map对象
     * @throws Exception 异常
     */
    public static Map<String, Object> xmlStrToMap(String xmlStr) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        //将xml格式的字符串转换成Document对象
        Document doc = DocumentHelper.parseText(xmlStr);
        //获取根节点
        Element root = doc.getRootElement();
        //获取根节点下的所有元素
        List children = root.elements();
        //循环所有子元素
        if(children != null && children.size() > 0) {
            for(int i = 0; i < children.size(); i++) {
                Element child = (Element)children.get(i);
                map.put(child.getName(), child.getTextTrim());
            }
        }
        return map;
    }

    /**
     * json 数据转换对象
     *
     * @param rootElt
     *            要转换的Element数据
     * @param pojo
     *            要转换的目标对象类型
     * @return 转换的目标对象
     * @throws InstantiationException,IllegalAccessException
     *             转换失败
     */
    @SuppressWarnings("rawtypes")
    public static Object fromElementToBean(Element rootElt, Class pojo) throws InstantiationException, IllegalAccessException
    {
        // 首先得到pojo所定义的字段
        Field[] fields = pojo.getDeclaredFields();
        // 根据传入的Class动态生成pojo对象
        Object obj = pojo.newInstance();
        for (Field field : fields)
        {
            // 设置字段可访问（必须，否则报错）
            field.setAccessible(true);
            // 得到字段的属性名
            String name = field.getName();
            // 这一段的作用是如果字段在Element中不存在会抛出异常，如果出异常，则跳过。
            try
            {
                rootElt.elementTextTrim(name);
            }
            catch (Exception ex)
            {
                continue;
            }
            if (rootElt.elementTextTrim(name) != null && !"".equals(rootElt.elementTextTrim(name)))
            {
                // 根据字段的类型将值转化为相应的类型，并设置到生成的对象中。
                if (field.getType().equals(Long.class) || field.getType().equals(long.class))
                {
                    field.set(obj, Long.parseLong(rootElt.elementTextTrim(name)));
                }
                else if (field.getType().equals(String.class))
                {
                    field.set(obj, rootElt.elementTextTrim(name));
                }
                else if (field.getType().equals(Double.class) || field.getType().equals(double.class))
                {
                    field.set(obj, Double.parseDouble(rootElt.elementTextTrim(name)));
                }
                else if (field.getType().equals(Integer.class) || field.getType().equals(int.class))
                {
                    field.set(obj, Integer.parseInt(rootElt.elementTextTrim(name)));
                }
                else if (field.getType().equals(java.util.Date.class))
                {
                    field.set(obj, new Date(rootElt.elementText(name)));

                }
                else
                {
                    continue;
                }
            }
        }
        return obj;
    }

    /**
     * JAVA对象转换为xml字符串。可识别的类型包括(long,int,String,double,Date)
     * @param pojo JAVA实体对象
     * @return xml字符串
     * <ROOT><name>李玉波</name><age>23</age><bornDatel>Fri Oct 28 17:13:55 CST 2016</bornDatel><money>32.2</money><count>200000</count></ROOT>
     * @throws Exception
     */
    public static String fromBeanToXml(Object pojo) throws Exception{
        Document document=DocumentHelper.createDocument();
        Element rootElement=DocumentHelper.createElement("ROOT");
        document.setRootElement(rootElement);
        Field[] fields = pojo.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            Element element=DocumentHelper.createElement(name);
            // 根据字段的类型将值转化为相应的类型，并设置到生成的对象中。
            if (field.getType().equals(Long.class) || field.getType().equals(long.class))
            {
               element.addText(field.get(pojo)+"");
            }
            else if (field.getType().equals(String.class))
            {
                element.addText(field.get(pojo) + "");
            }
            else if (field.getType().equals(Double.class) || field.getType().equals(double.class))
            {
                element.addText(field.get(pojo) + "");
            }
            else if (field.getType().equals(Integer.class) || field.getType().equals(int.class))
            {
                element.addText(field.get(pojo) + "");
            }
            else if (field.getType().equals(java.util.Date.class))
            {
                element.addText((Date)field.get(pojo) + "");
            }else{
                continue;
            }
            rootElement.add(element);
        }


        return document.asXML();
    }

    /**
     * 对象中如果有list对象，使用此方法解析，依赖个人喜好改造
     * @param list
     * @return
     * @throws Exception
     */
    public static String fromListToXml(List<Object> list) throws Exception{
        StringBuffer result=new StringBuffer();
        for (Object pojo:list){
            StringBuffer xml=new StringBuffer("<entity>");
            Field[] fields=pojo.getClass().getDeclaredFields();
            for (Field field:fields){
                field.setAccessible(true);
                String name = field.getName();
                String value=field.get(pojo)+"";
                xml.append("<").append(name).append(">").append(String.valueOf(value)).append("</").append(name).append(">");
            }
            xml.append("</entity>");
            result.append(xml);
        }
        return result.toString();
    }


    public static void main(String...d) throws Exception {
        Person person=new Person();
        person.setName("李玉波");
        person.setAge(23);
        person.setBornDatel(new Date());
        person.setCount(200000);
        person.setMoney(32.2);

        List<Person> persons=new ArrayList<Person>();
        persons.add(person);
        persons.add(person);
        person.setPersons(persons);

        String xml=fromBeanToXml(person);
        System.out.println(xml);
        Document document=DocumentHelper.parseText(xml);
        Element root=document.getRootElement();
        Person httpResult1=(Person)fromElementToBean(root, Person.class);
        System.out.println(httpResult1);
        String xml2=fromBeanToXml(httpResult1);
        System.out.println(xml2);
    }

}
