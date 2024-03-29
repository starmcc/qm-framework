package com.starmcc.qmframework.tools.base;

import com.starmcc.qmframework.exception.QmFrameworkException;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author starmcc
 * @version 2019/7/17 17:33
 * Map and Bean Tools
 */
public class QmConvertUtil {
    /**
     * 将一个 Map 对象转化为一个 JavaBean
     *
     * @param map  包含属性值的 map
     * @param type 要转化的类型
     * @return 转化出来的 JavaBean 对象
     */
    public static <T> T mapToBean(Map map, Class<T> type) {
        try {
            // 获取类属性
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            // 创建 JavaBean 对象
            T obj = type.newInstance();
            // 给 JavaBean 对象的属性赋值
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (map.containsKey(propertyName)) {
                    // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                    Object value = map.get(propertyName);
                    Object[] args = new Object[1];
                    args[0] = value;
                    descriptor.getWriteMethod().invoke(obj, args);
                }
            }
            return obj;
        } catch (IntrospectionException e) {
            throw new QmFrameworkException("IntrospectionException Failed to analyze class attributes!", e);
        } catch (InstantiationException e) {
            throw new QmFrameworkException("IllegalAccessException Failed to instantiate the JavaBean!", e);
        } catch (IllegalAccessException e) {
            throw new QmFrameworkException("IllegalAccessException Failed to instantiate the JavaBean!", e);
        } catch (InvocationTargetException e) {
            throw new QmFrameworkException("InvocationTargetException Failed to call the setter method for the property!", e);
        } catch (Exception e) {
            throw new QmFrameworkException("Exception", e);
        }
    }

    /**
     * 将一个 List 中包含的 map 集合转化为一个 List 中包含的 JavaBean 集合
     *
     * @param maps 包含属性值的 mapList
     * @param type 要转化的类型
     * @param <T>  转化出来的 JavaBean 对象 List
     * @return Returns the specified data according to the method
     */
    public static <T> List<T> mapsToBeans(List<Map> maps, Class<T> type) {
        List<T> list = new ArrayList<>();
        for (Map map : maps) {
            T t = mapToBean(map, type);
            list.add(t);
        }
        return list;
    }


    /**
     * 将一个 JavaBean 对象转化为一个  Map
     *
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     */
    public static Map beanToMap(Object bean) {
        try {
            Class type = bean.getClass();
            Map returnMap = new HashMap();
            BeanInfo beanInfo = Introspector.getBeanInfo(type);

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(bean, new Object[0]);
                    if (result != null) {
                        returnMap.put(propertyName, result);
                    } else {
                        returnMap.put(propertyName, "");
                    }
                }
            }
            return returnMap;
        } catch (IntrospectionException e) {
            throw new QmFrameworkException("IntrospectionException Failed to analyze class attributes!", e);
        } catch (IllegalAccessException e) {
            throw new QmFrameworkException("IllegalAccessException Failed to instantiate the JavaBean!", e);
        } catch (InvocationTargetException e) {
            throw new QmFrameworkException("InvocationTargetException Failed to call the setter method for the property!", e);
        } catch (Exception e) {
            throw new QmFrameworkException("Exception", e);
        }
    }

    /**
     * 将一个 List 包含 JavaBean 的集合转化为一个 List 包含 map 的集合
     *
     * @param beans 要转化的 list<JavaBean>
     * @return Returns the specified data according to the method
     */
    public static <T> List<Map> beansToMaps(List<T> beans) {
        List<Map> list = new ArrayList<>();
        for (T bean : beans) {
            Map map = beanToMap(bean);
            list.add(map);
        }
        return list;
    }
}
