package com.weicongno.generator.commons.template.velocity.util;

import com.weicongno.generator.commons.template.enums.LoaderMode;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Map.Entry;

/**
 * veloctiy模板引擎的工具类
 * @author weicong
 * @version 1.0 crateTime 2018-10-17 21:59:57
 */
public class VelocityUtils {
	private final String filePath;
	
	private final VelocityEngine velocityEngine;
	
	private final VelocityContext content = new VelocityContext();
	
	private final Template template;
	
	private final String rootPath;
	
	/**
	 * 默认使用classpath下的vm文件
	 * @param filePath
	 */
	public VelocityUtils(String filePath){
		this(LoaderMode.CLASS_PATH, "classpath", filePath);
	}

	/**
	 *
	 * @param mode
	 * @param rootPath
	 * @param filePath
	 */
	public VelocityUtils(LoaderMode mode, String rootPath , String filePath){
		this.rootPath = rootPath;
		this.filePath = filePath;
		velocityEngine = new VelocityEngine();
		if(LoaderMode.CLASS_PATH == mode){//如果选择使用classpath的加载
			velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, rootPath);
			velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		}else if(LoaderMode.ABSOLUTE_PATH == mode){//如果选择使用绝对路径加载
			velocityEngine.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, rootPath);
		}
		velocityEngine.init();
		template = velocityEngine.getTemplate(filePath);
	}
	
	public String getFilePath(){
		return rootPath + filePath;
	}
	
	/**
	 * 添加vm文件需要的参数
	 * @param key
	 * @param value
	 * @return
	 */
	public Object put(String key, Object value){
		return content.put(key, value);
	}
	
	/**
	 * 添加vm文件需要的参数
	 * @param map
	 */
	public void put(Map<String, Object> map){
		for(Entry<String, Object> i : map.entrySet()){
			put(i.getKey(), i.getValue());
		}
	}
	
	/**
	 * 删除vm文件需要的参数
	 * @param key
	 * @return
	 */
	public Object removenProperty(String key){
		return content.remove(key);
	}
	
	/**
	 * 通过参数给定的流将结果输出
	 * @param writer
	 */
	public void writerTemplate(Writer writer){
		template.merge(content, writer);
	}
	
	/**
	 * 获得结果的字符串
	 * @return
	 */
	public String getString(){
		StringWriter sw = new StringWriter();
		writerTemplate(sw);
		return sw.toString();
	}
}
