package com.weicongno.generator.db.alias.util;

import com.weicongno.generator.commons.config.YamlConfig;
import com.weicongno.generator.commons.config.bean.ConfigBean;
import com.weicongno.generator.commons.config.bean.FormatBean;

/**
 * @version 1.0 createTime:2019/6/24 11:37
 * @authro:weicong
 */
public class AliasConfig {

    /**
     * 是否进行下划线转换
     */
    private boolean underlineCast;

    /**
     * 是否将字母全部转换成小写
     */
    private boolean lowerCase;

    public AliasConfig(){
        ConfigBean configBean = YamlConfig.getConfig();
        FormatBean formatBean = configBean.getFormat();
        underlineCast = formatBean.getUnderlineCast();
        lowerCase = formatBean.getLowerCast();
    }

    @Override
    public String toString() {
        return "AliasConfig{" +
                "underlineCast=" + underlineCast +
                ", lowerCase=" + lowerCase +
                '}';
    }

    public boolean isUnderlineCast() {
        return underlineCast;
    }

    public void setUnderlineCast(boolean underlineCast) {
        this.underlineCast = underlineCast;
    }

    public boolean isLowerCase() {
        return lowerCase;
    }

    public void setLowerCase(boolean lowerCase) {
        this.lowerCase = lowerCase;
    }
}
