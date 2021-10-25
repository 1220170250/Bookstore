package com.qinkai.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

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

    /**
     * 从Ioc容器中获取组件
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBeanFromIoc(Class<T> clazz){
        //获取ioc
        WebApplicationContext ioc = ContextLoader.getCurrentWebApplicationContext();
        return  ioc.getBean(clazz);
    }
}
