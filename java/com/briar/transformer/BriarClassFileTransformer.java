package com.briar.transformer;

import com.briar.hook.*;
import com.briar.info.WebInformation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.List;

public class BriarClassFileTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer)   {
        //className格式为：java/lang/ObjectInputStream，不用转换
        //System.out.println("\033[32mJVM新增className:"+className+" \033[32m");
        if (className == null || "".equals(className)||className.startsWith("com/briar")){
            return classfileBuffer;
        }
        WebInformation instance = WebInformation.getInstance();
        if (instance.hookContext == null && instance.hookContext.size()<=0){
            return classfileBuffer;
        }
        for (CommonHook commonHook : instance.hookContext) {
            //System.out.println("commonHook为------------------------------------"+commonHook);
            byte[] newClassfileBuffer = matchClass(className, commonHook, classfileBuffer);
            if (!Arrays.equals(classfileBuffer,newClassfileBuffer)){
                return newClassfileBuffer;
            }

            //className为当前加载的类
        }

        return classfileBuffer;
    }



    private byte[] matchClass(String className, CommonHook commonHook, byte[] classfileBuffer) {
        byte[] newClassfileBuffer = new byte[classfileBuffer.length];
        System.arraycopy(classfileBuffer,0,newClassfileBuffer,0,newClassfileBuffer.length);


        List<HookClassAndMethod> hookClassAndMethodList = commonHook.getHookClassAndMethodList();
        /*
        for (HookClassAndMethod hook : hookClassAndMethodList) {
            System.out.println("hookClassAndMethodList为----------------------------------------:"+hook.getHookClass()+"==="+hook.getHookMethod()+"+++");
        }
        */
        if (hookClassAndMethodList==null||hookClassAndMethodList.size()<=0){
            return classfileBuffer;
        }
        for (HookClassAndMethod hookClassAndMethod : hookClassAndMethodList) {
            String hookClass = hookClassAndMethod.getHookClass();
            //如果当前加载类在hook类列表中
            if (hookClass==null||"".equals(hookClass)||!className.equals(hookClass)){
                continue;
            }
            System.out.println("hookClassAndMethodList为---------------------:"+hookClassAndMethod.getHookClass()+"==="+hookClassAndMethod.getHookMethod()+"+++");

            //className为当前加载的类

            System.out.println("hook的class为:"+hookClass+"  当前加载className为:"+className+" ");
            List<String> hookMethods = hookClassAndMethod.getHookMethod();
            if (hookMethods==null||hookMethods.size()<=0){
                System.out.println("hookcalss为:"+hookClass+" method为:null ");
                continue;
            }
            //遍历该hook类里面设定的hook方法
//            for (String hookMethod : hookMethods) {
//                if (hookMethod!=null&&!"".equals(hookMethod)){
//                    //if hookClass对应的hookmethod有值
//                    System.out.println("hook的class为:"+hookClass+" 当前加载hookmethod为:"+hookMethod+" ");
//
//                    //传入hook的方法，和当前加载类的字节码
//                    newClassfileBuffer = commonHook.insertBefore(hookMethod, newClassfileBuffer);
//
//                }
//            }
        }


        return newClassfileBuffer;

    }


}