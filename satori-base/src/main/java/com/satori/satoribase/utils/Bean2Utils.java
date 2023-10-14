package com.satori.satoribase.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * @author ChenHao
 * @date 2023/5/31 18:15
 */
@UtilityClass
public class Bean2Utils extends BeanUtils {

    /**
     * 复制对象到指定类（深度拷贝）
     * @param object
     * @param destclas 指定类
     * @param <T>
     * @return
     */
    public static <T> T clone(final Object object, Class<T> destclas){
        if (object == null) {
            return null;
        }
        String json = JSON.toJSONString(object);
        return JSON.parseObject(json, destclas);
    }

    public static <T,R> R clone(final T source, TypeReference<R> typeReference){
        if (source == null) {
            return null;
        }
        return JSON.parseObject(JSON.toJSONString(source), typeReference);
    }

    public static <T,R> R copyProperties(T t, Supplier<R> supplier){
        return copyProperties2(t, supplier, null);
    }

    public static <T,R> R copyProperties2(T t, Supplier<R> supplier, BiConsumer<T,R> biConsumer){
        if(null == t){
            return null;
        }
        R r = supplier.get();
        copyProperties(t, r);
        if(biConsumer != null){
            biConsumer.accept(t, r);
        }
        return r;
    }

    public static <T,R> List<R> copyProperties(Collection<T> inputList,
                                               Supplier<R> supplier){
        return copyProperties3(inputList, supplier, null);
    }

    public static <T,R> List<R> copyProperties3(Collection<T> inputList,
                                               Supplier<R> supplier,
                                               BiConsumer<T,R> biConsumer){
        if(CollectionUtils.isEmpty(inputList)){
            return new ArrayList<>();
        }
        List<R> list = Lists.newArrayListWithExpectedSize(inputList.size());
        for (T t : inputList) {
            R r = supplier.get();
            copyProperties(t, r);
            if(null != biConsumer){
                biConsumer.accept(t, r);
            }
            list.add(r);
        }
        return list;
    }

}
