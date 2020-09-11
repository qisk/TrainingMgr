package com.blackcandle.baselib.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dalvik.system.DexFile;

public class Router {
    private final String PACKAGENAME ="com.blackcandle";

    private static Router router = new Router();
    //需要一个容器  一个装载所有模块中的Activity的class对象的容器 路由表
    private Map<String,Class<? extends Activity>> map;
    //上下文
    private Context context;

    private Router(){
        map = new HashMap<>();
    }

    public static Router getInstance(){
        return router;
    }

    public void init(Context context){
        this.context = context;
        List<String> className = getClassName(PACKAGENAME + ".util");
        for (String s : className) {
            try {
                Class<?> aClass = Class.forName(s);
                //进行第二步验证   这个类是否是IROUTER接口的实现类
                if(IRouter.class.isAssignableFrom(aClass)){
                    IRouter iRouter = (IRouter) aClass.newInstance();
                    iRouter.putActivity();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将Activity的类对象加入到map中
     * @param key
     * @param clazz
     */
    public void addActivity(String key,Class<? extends Activity> clazz){
        if(key!=null && clazz!=null && !map.containsKey(key)){
            map.put(key,clazz);
        }
    }

    /**
     * 跳转窗体的方法
     * @param key
     * @param bundle
     */
    public void jumpActivity(String key, Bundle bundle){
        Class<? extends Activity> aClass = map.get(key);
        if(aClass ==null){
            return;
        }
        Intent intent = new Intent(context, aClass);
        if(bundle!=null){
            intent.putExtras(bundle);
        }

        // 这里可能会有问题：只用FLAG_ACTIVITY_NEW_TASK，对于已经打开的Activity，就会创建一个新的实例。
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        context.startActivity(intent);
    }

    /**
     * 通过包名获取这个包下面的所有的类名
     * @param packageName
     * @return
     */
    private List<String> getClassName(String packageName) {
        //创建一个class对象的集合
        List<String> classList = new ArrayList<>();
        try {
            //把当前应有的apk存储路径给dexFile
            DexFile df = new DexFile(context.getPackageCodePath());
            Enumeration<String> entries = df.entries();
            while (entries.hasMoreElements()) {
                String className = (String) entries.nextElement();
                if (className.contains(packageName)) {
                    classList.add(className);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classList;
    }
}
