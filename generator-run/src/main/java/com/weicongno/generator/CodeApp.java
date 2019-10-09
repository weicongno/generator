package com.weicongno.generator;

import com.weicongno.generator.commons.util.CRUDNameBean;
import com.weicongno.generator.commons.util.YamlCRUDNameCreateor;
import com.weicongno.generator.db.DbTableReader;
import com.weicongno.generator.db.bean.TableBean;
import com.weicongno.generator.db.enums.DbTypeEnum;
import com.weicongno.generator.java.JavaBeanGenerator;
import com.weicongno.generator.java.JavaDaoGenerator;
import com.weicongno.generator.java.SimpleJavaBeanGenerator;
import com.weicongno.generator.java.SimpleJavaDaoGenerator;
import com.weicongno.generator.mybatis.MybatisMapperGenerator;
import com.weicongno.generator.mybatis.SimpleMybatisMapperGenerator;

import java.io.Writer;

/**
 * @version 1.0 createTime:2019/6/26 15:50
 * @authro:weicong
 */
public class CodeApp {

    /**
     * 表名;
     */
    public static final String TABLE_NAME = "lb_matlists";


    /**
     * bean的名称
     */
    public static final String BEAN_NAME = "MatLists";

    /**
     * 业务模块名称
     */
    public static final String SERVICE_NAME = "db";

    /**
     * 数据库类型
     */
    public static final DbTypeEnum DB_TYPE_ENUM = DbTypeEnum.MYSQL_5_5;

    public static void main(String[] args) throws Exception {
        TableBean tableBean = DbTableReader.readDbTable(TABLE_NAME, DB_TYPE_ENUM);

        Writer dao = getDaoResource(tableBean);
        Writer javaBean = getBeanResource(tableBean);
        Writer mapper = getMybatisMapperResource(tableBean);

        System.out.println(dao);
        System.out.println(javaBean);
        System.out.println(mapper);
    }

    /**
     * 获取生成CRUD的名称信息
     * @return
     */
    public static CRUDNameBean getCrudNameBean(){
        return new YamlCRUDNameCreateor(BEAN_NAME, SERVICE_NAME).createCRUDNameBean();
    }

    /**
     * 获取java dao层的代码
     * @param tableBean
     * @return
     */
    public static Writer getDaoResource(TableBean tableBean){
        JavaDaoGenerator javaDaoGenerator = new SimpleJavaDaoGenerator();
        return javaDaoGenerator.getJavaDaoResource(tableBean, getCrudNameBean());
    }

    /**
     * 获取java bean的代码
     * @param tableBean
     * @return
     */
    public static Writer getBeanResource(TableBean tableBean){
        JavaBeanGenerator javaBeanGenerator = new SimpleJavaBeanGenerator();
        return javaBeanGenerator.getJavaBeanResource(tableBean, getCrudNameBean());
    }

    /**
     * 获取mybatis mapper文件的代码
     * @param tableBean
     * @return
     */
    public static Writer getMybatisMapperResource(TableBean tableBean){
        MybatisMapperGenerator mybatisMapperGenerator = new SimpleMybatisMapperGenerator();
        return mybatisMapperGenerator.getMapperResource(tableBean, getCrudNameBean());
    }
}
