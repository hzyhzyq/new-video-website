package com.yunqi.video.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tony
 */
@Configuration
public class JsonHttpMessageConverterConfig {
    /**
     * 对HTTP请求的数据进行转换的工具类
     * @return
     */
    @Bean
    @Primary
    public HttpMessageConverters fastJsonHttpMessageConverters(){
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        //配置返回数据的时间类型
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        //配置返回数据的序列化信息
        config.setSerializerFeatures(
                //格式化输出（标准输出，包括缩进和换行）
                SerializerFeature.PrettyFormat,
                //value为null的数据转化为空字符串
                SerializerFeature.WriteNullStringAsEmpty,
                //同上，空LIST,MAP转化为空字符串
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteMapNullValue,
                //升序排列MAP
                SerializerFeature.MapSortField,
                //禁用循环引用
                SerializerFeature.DisableCircularReferenceDetect
        );
        converter.setFastJsonConfig(config);
        return new HttpMessageConverters(converter);
    }
    //循环引用：
    //  在一个LIST中添加了多个相同的元素，
    //  第二个（之后）元素不显示自己的信息，而是显示第一个元素的地址
    //  循环引用:[{"a"},{"$ref":"$[0]"}]
    //  不循环引用：[{"a"},{"a"}]
}
