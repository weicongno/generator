package com.weicongno.generator.commons.util;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 类型转换类
 * @version 1.0 createTime:2019/5/8 15:46
 * @authro:weicong
 */
public class Convert {

    /**
     * 对象转换成字符串
     * @param value
     * @param defaultValue
     * @return
     */
    public static String toString(Object value, String defaultValue){
        if(null == value){
            return defaultValue;
        }
        if(value instanceof String){
            return (String) value;
        }
        return String.valueOf(value);
    }

    /**
     * 对象转字符串
     * @param value
     * @return
     */
    public static String toString(Object value){
        return toString(value, null);
    }

    /**
     * 对象转换成boolean
     * @param value
     * @param defaultValue
     * @return
     */
    public static Boolean toBoolean(Object value, Boolean defaultValue){
        if(null == value){
            return defaultValue;
        }
        if(value instanceof Boolean){
            return (Boolean) value;
        }
        return Boolean.parseBoolean(value.toString());
    }

    /**
     * 对象转boolean
     * @param value
     * @return
     */
    public static Boolean toBoolean(Object value){
        return toBoolean(value, null);
    }

    /**
     * 对象转换成integer
     * @param value
     * @param defaultValue
     * @return
     */
    public static Integer toInteger(Object value, Integer defaultValue){
        if(null == value){
            return defaultValue;
        }
        if(value instanceof Integer){
            return (Integer)value;
        }
        return Integer.parseInt(value.toString());
    }

    /**
     * 对象转integer
     * @param value
     * @return
     */
    public static Integer toInteger(Object value){
        return toInteger(value, null);
    }

    /**
     * 对象转Long
     * @param value
     * @param defaultValue
     * @return
     */
    public static Long toLong(Object value, Long defaultValue){
        if(null == value){
            return defaultValue;
        }
        if(value instanceof Long){
            return (Long) value;
        }
        return Long.parseLong(value.toString());
    }

    /**
     * 对象转Long
     * @param value
     * @return
     */
    public static Long toLong(Object value){
        return toLong(value, null);
    }

    /**
     * 对象转Byte
     * @param value
     * @param defaultValue
     * @return
     */
    public static Byte toByte(Object value, Byte defaultValue){
        if(null == value){
            return defaultValue;
        }
        if(value instanceof Byte){
            return (Byte) value;
        }
        return Byte.parseByte(value.toString());
    }

    /**
     * 对象转Byte
     * @param value
     * @return
     */
    public static Byte toByte(Object value){
        return toByte(value, null);
    }

    /**
     * 对象转Byte
     * @param value
     * @param defaultValue
     * @return
     */
    public static Short toShort(Object value, Short defaultValue){
        if(null == value){
            return defaultValue;
        }
        if(value instanceof Short){
            return (Short) value;
        }
        return Short.parseShort(value.toString());
    }

    /**
     * 对象转Short
     * @param value
     * @return
     */
    public static Short toShort(Object value){
        return toShort(value, null);
    }

    /**
     * 对象转double
     * @param value
     * @param defaultValue
     * @return
     */
    public static Double toDouble(Object value, Double defaultValue){
        if(null == value){
            return defaultValue;
        }
        if(value instanceof Double){
            return (Double)value;
        }
        return Double.parseDouble(value.toString());
    }

    /**
     * 对象转换成Double
     * @param value
     * @return
     */
    public static Double toDouble(Object value){
        return toDouble(value, null);
    }

    /**
     * 对象转换成Float
     * @param value
     * @param defaultValue
     * @return
     */
    public static Float toFloat(Object value, Float defaultValue){
        if(null == value){
            return defaultValue;
        }
        if(value instanceof Float){
            return (Float)value;
        }
        return Float.parseFloat(value.toString());
    }

    /**
     * 对象转成Float
     * @param value
     * @return
     */
    public static Float toFloat(Object value){
        return toFloat(value, null);
    }

    /**
     * 集合转字符串
     * @param list
     * @param symbol
     * @return
     */
    public static String listToString(List<String> list, String symbol){
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        StringBuilder append = new StringBuilder();
        for(int i = 0; i < list.size(); i++){
            append.append(list.get(i));
            if(i != list.size() - 1){
                append.append(symbol);
            }
        }
        return append.toString();
    }

}
