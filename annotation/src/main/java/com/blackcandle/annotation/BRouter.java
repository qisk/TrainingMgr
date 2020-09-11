package com.blackcandle.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//元注解
@Target(ElementType.TYPE)   //声明注解的作用域  放在什么上面
@Retention(RetentionPolicy.CLASS)   //声明注解的生命周期  就是它的存在周期  源码期 < 编译期 < 运行期
public @interface BRouter {
    //路由表中的key
    String value();
}