package com.weicongno.generator.commons.yaml;

import com.weicongno.generator.commons.ognl.Ognl;
import com.weicongno.generator.commons.parsing.GenericTokenParser;
import com.weicongno.generator.commons.parsing.TokenHandler;
import com.weicongno.generator.commons.util.Convert;
import com.weicongno.generator.commons.util.reflect.ReflectUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.MethodProperty;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeId;

import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * yaml的工具类
 * @version 1.0 createTime:2019/9/17 17:26
 * @author:weicong
 */
public class YamlUtils {

    private static String OPEN_TOKEN = "${";

    private static String CLOSE_TOKEN = "}";

    private final boolean KEY_LINE_CAST;

    public YamlUtils(){
        this(true);
    }

    /**
     *
     * @param keyLineCast 是否转换下划线
     */
    public YamlUtils(boolean keyLineCast){
        KEY_LINE_CAST = keyLineCast;
    }

    /**
     * 解析yaml文件
     * @param inputStream
     * @return
     */
    public Map parseYaml(InputStream inputStream) {
        Yaml yaml = getYaml();
        return yaml.load(inputStream);
    }

    /**
     * 获取当前启动jar包的yaml文件信息
     * @param path
     * @return
     */
    public Map parseYaml(String path){
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        return parseYaml(inputStream);
    }

    /**
     * 解析yaml文件并且转换成bean
     * @param path
     * @param clazz
     * @param <T>
     * @return
     */
    public <T>T parseYamlAs(String path, Class<T> clazz){
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        Map map = parseYaml(path);
        Yaml yaml = getYaml(map);
        return yaml.loadAs(inputStream, clazz);
    }

    /**
     * 读取该类所在jar中的yaml文件
     * @param path
     * @param clazz
     * @return
     */
    public Map parseYaml(String path, Class clazz){
        InputStream inputStream = clazz.getClassLoader().getResourceAsStream(path);
        return parseYaml(inputStream);
    }

    /**
     * 获得一个yaml对象
     * @return
     */
    public Yaml getYaml(){
        return getYaml(null);
    }

    /**
     * 获得一个Yaml对象
     * @param map
     * @return
     */
    public Yaml getYaml(Map map){
        if(KEY_LINE_CAST){
            return new Yaml(new ExtendConstructor(map));
        }
        return new Yaml();
    }

    /**
     * 格式化yaml中的数据
     * @param map
     * @return
     */
    public static Map formatData(Map map){
        TokenHandler tokenHandler = new OgnlTokenHandler(map);
        GenericTokenParser genericTokenParser = new GenericTokenParser("${", "}", tokenHandler);
        return formatMapData(map, map, genericTokenParser);
    }

    /**
     * 格式化map中数据的一些表达式
     * @param root
     * @param current
     * @param genericTokenParser
     * @return
     */
    public static Map formatMapData(Map root, Map current, GenericTokenParser genericTokenParser){
        String key;
        Object value;
        for(Map.Entry entry : (Set<Map.Entry>)current.entrySet()){
            key = Convert.toString(entry.getKey());
            value = entry.getValue();
            if(value instanceof List){
                List list = (List)value;
                formatListData(root, list, genericTokenParser);
            }
            if(value instanceof String){
                current.put(key, formatValue(root, value, genericTokenParser));
            }
            if(value instanceof Map){
                formatMapData(root, (Map)value, genericTokenParser);
            }
        }
        return root;
    }

    /**
     * 格式化list的中的数据
     * @param root
     * @param current
     * @param genericTokenParser
     */
    public static void formatListData(Map root, List current, GenericTokenParser genericTokenParser){
        if(CollectionUtils.isEmpty(current)){
            return;
        }
        for(Object item : current){
            if(item instanceof Map){
                formatMapData(root, (Map)item, genericTokenParser);
            }
            if(item instanceof List){
                formatListData(root, (List)item, genericTokenParser);
            }
            formatValue(root, item, genericTokenParser);
        }
    }

    /**
     * 格式化值
     * @param root
     * @param value
     * @param genericTokenParser
     * @return
     */
    public static Object formatValue(Map root, Object value, GenericTokenParser genericTokenParser){
        if(null == value){
            return null;
        }
        if(value instanceof String){
            String v = Convert.toString(value);
            return genericTokenParser.parse(v);
        }
        return value;
    }
    static class MethodPropertyWarpper extends MethodProperty {

        private MethodProperty property;

        private Map map;

        public MethodPropertyWarpper(PropertyDescriptor property, MethodProperty methodProperty, Map map) {
            super(property);
            this.map = map;
            this.property = methodProperty;
        }

        @Override
        public void set(Object object, Object value) throws Exception {
            if(null != value){
                value = handlerToken(object, value);
            }
            property.set(object, value);
        }

        @Override
        public Object get(Object object) {
            return property.get(object);
        }

        @Override
        public List<Annotation> getAnnotations() {
            return property.getAnnotations();
        }

        @Override
        public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
            return property.getAnnotation(annotationType);
        }

        @Override
        public boolean isWritable() {
            return property.isWritable();
        }

        @Override
        public boolean isReadable() {
            return property.isReadable();
        }

        public Object handlerToken(Object object, Object value){
            GenericTokenParser genericTokenParser = getGenericTokenParser();
            if(value instanceof String){
                String valueStr = Convert.toString(value);
                return genericTokenParser.parse(valueStr);
            }
            if(value instanceof Map){
                YamlUtils.formatMapData(map, (Map) value, genericTokenParser);
            }
            return value;
        }

        public GenericTokenParser getGenericTokenParser(){
            return new GenericTokenParser("${", "}", getTokenHanlder(map));
        }

        public TokenHandler getTokenHanlder(Object root){
            return new OgnlTokenHandler(root);
        }
    }
    /**
     * OGNL的token处理
     */
    static class OgnlTokenHandler implements TokenHandler {

        private Object root;

        public OgnlTokenHandler(Object root){
            this.root = root;
        }

        @Override
        public String handleToken(String var1) {
            return Convert.toString(Ognl.getValue(var1, root));
        }
    }

    static class ExtendConstructor extends Constructor {

        private Map map;

        public ExtendConstructor(){
            this(null);
        }

        public ExtendConstructor(Map map){
            super();
            this.map = map;
            this.yamlClassConstructors.put(NodeId.mapping, new ExtendConstructMapping());
        }

        class ExtendConstructMapping extends Constructor.ConstructMapping{
            @Override
            protected Property getProperty(Class<?> type, String name) {
                name = lineFormat(name);
                Property property = super.getProperty(type, name);
                String className = property.getClass().getName();
                if(property instanceof MethodProperty){
                    MethodProperty methodProperty = (MethodProperty) property;
                    PropertyDescriptor p = null;
                    try {
                        p = ReflectUtils.getProperty(property, "property");
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return new MethodPropertyWarpper(p, methodProperty, map);
                }
                return property;
                //return new ExtendPropertyWarpper(name, type, property, null);
            }

            protected String lineFormat(String name){
                final String LINE = "-";
                if(StringUtils.isBlank(name)){
                    return null;
                }
                StringBuilder result = new StringBuilder(name);
                for(int start = result.indexOf(LINE); start > -1; start = result.indexOf(LINE)){
                    result = result.delete(start, start + 1);
                    if(result.length() == start){
                        continue;
                    }
                    result = result.replace(start, start + 1, result.substring(start, start + 1).toUpperCase());
                }
                return result.toString();
            }
        }
    }

}
