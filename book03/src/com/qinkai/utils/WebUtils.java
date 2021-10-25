package com.qinkai.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;


public class WebUtils {
    /**
     * 把Map中值注入到对应javaBean中
     *
     * @param map
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> T copyParamToBean(Map map, T bean) {
        try {
            //通过属性名和参数名对应注入，要求两者一致
            BeanUtils.populate(bean, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 字符串转int
     *
     * @param str          字符串
     * @param defaultValue 默认值
     * @return
     */
    public static int parseInt(String str, int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
