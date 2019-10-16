package com.weicongno.generator.doclever;

import com.weicongno.generator.commons.util.Convert;
import com.weicongno.generator.db.bean.FieldBean;
import com.weicongno.generator.db.bean.TableBean;
import com.weicongno.generator.doclever.bean.InterfaceBean;
import com.weicongno.generator.doclever.bean.OutParamBean;
import com.weicongno.generator.doclever.constant.DOCleverConstants;
import com.weicongno.generator.java.util.DataTypeEnum;
import com.weicongno.generator.java.util.FieldUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * @version 1.0 createTime:2019/10/16 09:47
 * @author:weicong
 */
public class DOCleverBeanGenerator {

    public InterfaceBean geenratorBean(TableBean tableBean){
        InterfaceBean interfaceBean = new InterfaceBean();
        interfaceBean.setFlag("SBDoc");
        interfaceBean.setParam(getParam(tableBean));
        interfaceBean.setFinish(1);
        interfaceBean.setSort(0);
        interfaceBean.setName("实体类");
        interfaceBean.setUrl("实体类");
        interfaceBean.setRemark("实体类");
        interfaceBean.setMethod("PATCH");
        return interfaceBean;
    }

    public List<Object> getParam(TableBean tableBean){
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("before", getBefore());
        param.put("after", getAfter());
        param.put("name", getName());
        param.put("id", getId());
        param.put("remark", getRemark());
        param.put("header", getHeader());
        param.put("queryParam", new ArrayList<Object>(0));
        param.put("bodyInfo", getBodyInfo());
        param.put("bodyParam", new ArrayList<Object>(0));
        param.put("outParam", getOutParam(tableBean));
        param.put("outInfo", getOutInfo());
        param.put("restParam", new ArrayList<Object>(0));

        List<Object> list = new ArrayList<Object>(1);
        list.add(param);
        return list;
    }

    public Map<String, Object> getBefore(){
        Map<String, Object> before = new HashMap<String, Object>();
        before.put("mode", 0);
        before.put("code", "");
        return before;
    }

    public Map<String, Object> getAfter(){
        Map<String, Object> after = new HashMap<String, Object>();
        after.put("mode", 0);
        after.put("code", "");
        return after;
    }

    public String getId(){
        return UUID.randomUUID().toString();
    }

    public String getName(){
        return "参数";
    }

    private String getRemark(){
        return "";
    }

    public List<Map<String, Object>> getHeader(){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(1);
        Map<String, Object> header = new HashMap<String, Object>();
        header.put("name", "Content-Type");
        header.put("value", "application/x-www-form-urlencoded");
        header.put("remark", "");

        header.put("bodyInfo", getBodyInfo());
        return list;
    }

    public Map<String, Object> getBodyInfo(){
        Map<String, Object> bodyInfo = new HashMap<String, Object>();
        bodyInfo.put("type", 0);
        bodyInfo.put("rawType", 0);
        bodyInfo.put("rawTextRemark", "");
        bodyInfo.put("rawFileRemark", "");
        bodyInfo.put("rawText", "");
        bodyInfo.put("rawJSON", new ArrayList<Object>(0));
        bodyInfo.put("rawJSONType", 0);
        return bodyInfo;
    }

    public List<Object> bodyParam(){
        return new ArrayList<Object>(0);
    }

    public List<OutParamBean> getOutParam(TableBean tableBean){
        if(null == tableBean){
            return new ArrayList<OutParamBean>(0);
        }

        if(CollectionUtils.isEmpty(tableBean.getFields())){
            return new ArrayList<OutParamBean>(0);
        }

        List<OutParamBean> list = new ArrayList<OutParamBean>(tableBean.getFields().size());
        OutParamBean outParamBean;
        for(FieldBean fieldBean : tableBean.getFields()){
            outParamBean = new OutParamBean();
            outParamBean.setName(fieldBean.getAlias());
            outParamBean.setType(castType(fieldBean));
            outParamBean.setRemark(fieldBean.getCommonet());
            outParamBean.setMock(castDataType(fieldBean));
            if(fieldBean.isPrimaryKey()){
                outParamBean.setMust(DOCleverConstants.MUST_YES);
            }else{
                outParamBean.setMust(fieldBean.isNullAble() ? DOCleverConstants.MUST_YES : DOCleverConstants.MUST_NO);
            }
            list.add(outParamBean);
        }
        return list;
    }

    /**
     * 转换数据类型
     * @param fieldBean
     * @return
     */
    public String castDataType(FieldBean fieldBean){
        StringBuilder append = new StringBuilder(fieldBean.getDataType());
        if(null == fieldBean.getLength()){
            return append.toString();
        }
        append.append("(");
        append.append(fieldBean.getLength());
        if(null != fieldBean.getScale()){
            append.append(", ").append(fieldBean.getScale());
        }
        append.append(")");
        return append.toString();
    }

    public Integer castType(FieldBean fieldBean){
        if(null == fieldBean){
            return null;
        }
        String dataType = FieldUtils.castDataType(fieldBean);
        if(DataTypeEnum.STRING.getDataType().equals(dataType)){
            return com.weicongno.generator.doclever.enums.DataTypeEnum.STRING.getType();
        }
        if(DataTypeEnum.LONG.getDataType().equals(dataType)){
            return com.weicongno.generator.doclever.enums.DataTypeEnum.STRING.getType();
        }
        if(DataTypeEnum.DECIMAL.getDataType().equals(dataType)){
            return com.weicongno.generator.doclever.enums.DataTypeEnum.NUMBER.getType();
        }
        if(DataTypeEnum.INTEGER.getDataType().equals(dataType)){
            return com.weicongno.generator.doclever.enums.DataTypeEnum.STRING.getType();
        }
        if(DataTypeEnum.DATE.getDataType().equals(dataType)){
            return com.weicongno.generator.doclever.enums.DataTypeEnum.STRING.getType();
        }
        return null;
    }

    public Map<String, Object> getOutInfo(){
        Map<String, Object> outInfo = new HashMap<String, Object>();
        outInfo.put("type", 0);
        outInfo.put("rawRemark", "");
        outInfo.put("rawMock", "");
        outInfo.put("jsonType", 0);
        return outInfo;
    }
}
