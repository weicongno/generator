package com.weicongno.generator.commons.util;

import com.weicongno.generator.commons.config.YamlConfig;
import com.weicongno.generator.commons.config.bean.ConfigBean;
import com.weicongno.generator.commons.config.bean.FormatBean;
import com.weicongno.generator.commons.config.bean.JavaCodeBean;
import com.weicongno.generator.commons.ognl.Ognl;
import com.weicongno.generator.commons.parsing.GenericTokenParser;
import com.weicongno.generator.commons.parsing.TokenHandler;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 从yaml中获取CRUD的名称
 * @version 1.0 createTime:2019/9/27 11:18
 * @author:weicong
 */
public class YamlCRUDNameCreateor {

    private String name;
    private String moduleName;

    public YamlCRUDNameCreateor(String name, String moduleName) {
        this.name = name;
        this.moduleName = moduleName;
    }

    /**
     * 创建CRUD的名称
     * @return
     */
    public CRUDNameBean createCRUDNameBean(){
        ConfigBean configBean = YamlConfig.getConfig();
        FormatBean formatBean = configBean.getFormat();
        JavaCodeBean javaCodeBean = formatBean.getJavaCode();
        CRUDNameBean crudNameBean = new CRUDNameBean();
        crudNameBean.setObjName(firstLowerCase(name));

        String beanName = parse(javaCodeBean.getBean().get("className"));
        String _package = parse(javaCodeBean.getBean().get("package"));
        crudNameBean.setBeanPackage(_package);
        crudNameBean.setBeanName(beanName);
        crudNameBean.setBeanClassName(_package + "." + beanName);

        Map<String, String> methodMap = javaCodeBean.getMethod();
        crudNameBean.setInsertMethodName(parse(methodMap.get("insert")));
        crudNameBean.setSelectMethodName(parse(methodMap.get("select")));
        crudNameBean.setUpdateMethodName(parse(methodMap.get("update")));
        crudNameBean.setDeleteMethodName(parse(methodMap.get("delete")));
        crudNameBean.setInsertBatchMethodName(parse(methodMap.get("insertBatch")));

        Map<String, String> mapperMap = javaCodeBean.getMapper();
        crudNameBean.setDaoInterfacePakcage(parse(mapperMap.get("package")));
        crudNameBean.setDaoInterfaceName(parse(mapperMap.get("className")));
        return crudNameBean;
    }

    /**
     * 开头小写
     * @param word
     * @return
     */
    public String firstLowerCase(String word){
        if(StringUtils.isEmpty(word)){
            return null;
        }
        return word.substring(0, 1).toLowerCase() + word.substring(1, word.length());
    }

    public Map<String, Object> getContext(){
        Map<String, Object> context = new HashMap<String, Object>();
        context.put("name", getName());
        context.put("moduleName", getModuleName());
        return context;
    }

    /**
     * 解析表达式中的字符串
     * @param text
     * @return
     */
    public String parse(String text){
        GenericTokenParser genericTokenParser = getGenericTokenParser();
        return genericTokenParser.parse(text);
    }

    public GenericTokenParser getGenericTokenParser(){
        return new GenericTokenParser("{", "}", getTokenHanlder());
    }

    public TokenHandler getTokenHanlder(){
        return new TokenHandler(){

            @Override
            public String handleToken(String var1) {
                return Convert.toString(Ognl.getValue(var1, getContext()));
            }
        };
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
