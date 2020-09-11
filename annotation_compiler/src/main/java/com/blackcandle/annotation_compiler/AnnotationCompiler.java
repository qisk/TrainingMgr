package com.blackcandle.annotation_compiler;

import com.google.auto.service.AutoService;
import com.blackcandle.annotation.BRouter;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/**
 * 注解处理器
 * 1.找到代码中用到了某些注解的<东西>
 * 2.找到之后 获取注解中的key  以及获取到它所标记的东西   生成代码
 */
@AutoService(Processor.class)   //注册注解处理器
public class AnnotationCompiler extends AbstractProcessor {
    private final String PACKAGENAME ="com.blackcandle";

    //获取到一个生成文件的对象
    Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
    }


    /**
     * 声明当前注解处理器支持的java版本
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }

    /**
     * 声明注解处理器要处理的注解有哪些
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new HashSet<>();
        types.add(BRouter.class.getCanonicalName());
        return types;
    }

    /**
     * 就是整个注解处理器的核心方法  在程序编译的时候  就会去执行这个方法里面的业务逻辑
     * @param set
     * @param roundEnvironment
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> set,
                           RoundEnvironment roundEnvironment) {
        //方法   = 方法节点ExecutableElement
        //类   = 类节点TypeElement
        //成员变量  =成员变量节点VariableElement
        //获取到当前模块中 被bindPath标记的节点信息类
        Set<? extends Element> elementsAnnotatedWith = roundEnvironment.
                getElementsAnnotatedWith(BRouter.class);
        Map<String,String> map = new HashMap<>();
        for (Element element : elementsAnnotatedWith) {
            TypeElement typeElement = (TypeElement) element;
            String activityName = typeElement.getQualifiedName().toString();
            String key = typeElement.getAnnotation(BRouter.class).value();
            map.put(key,activityName+".class");
        }
        if(map.size() > 0){
            Writer writer = null;
            //生成文件
            try {
                String utilName = "ActivityUtil"+System.currentTimeMillis();
                //去生成java文件
                JavaFileObject sourceFile = filer.createSourceFile(
                        "com.maniu.util." + utilName);
                writer = sourceFile.openWriter();
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("package " + PACKAGENAME + ".util;\n");
                stringBuffer.append("import " + PACKAGENAME + ".baselib.router.IRouter;\n");
                stringBuffer.append("import " + PACKAGENAME + ".baselib.router.Router;\n");
                stringBuffer.append("public class "+utilName+" implements IRouter {\n");
                stringBuffer.append("@Override\n");
                stringBuffer.append("public void putActivity() {\n");
                Iterator<String> iterator = map.keySet().iterator();
                while (iterator.hasNext()){
                    String key = iterator.next();
                    String activityName = map.get(key);
                    stringBuffer.append("Router.getInstance().addActivity(\""+key+"\","+activityName+");");
                }
                stringBuffer.append("}\n}");
                writer.write(stringBuffer.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(writer!=null){
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }
}
