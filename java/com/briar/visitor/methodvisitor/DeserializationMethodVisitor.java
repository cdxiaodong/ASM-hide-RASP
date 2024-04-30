package com.briar.visitor.methodvisitor;

import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;

public class DeserializationMethodVisitor extends AdviceAdapter {
    private String checkClass;
    private String targetClass;
    private String methodName;

    public DeserializationMethodVisitor(int i, MethodVisitor methodVisitor, int access, String name, String desc,String checkClass,String targetClass) {
        super(i, methodVisitor, access, name, desc);
        this.checkClass=checkClass;
        this.targetClass=targetClass;
        this.methodName=name;
    }

    @Override
    protected void onMethodEnter() {
        if (checkClass == null||"".equals(checkClass)){
            return;
        }
        if ("java/io/ObjectInputStream".equals(targetClass)&&"resolveClass".equals(methodName)){
            System.out.println(targetClass);
            mv.visitVarInsn(ALOAD,1);
            System.out.println(targetClass);
        } else if ("com/alibaba/fastjson/util/TypeUtils".equals(targetClass)&&("loadClass".equals(methodName)||"getClassFromMapping".equals(methodName))) {
            mv.visitVarInsn(ALOAD,0);
            //插入字节码

        } else if ("com/fasterxml/jackson/databind/deser/BeanDeserializer".equals(targetClass)&&"deserialize".equals(methodName)) {
            mv.visitVarInsn(ALOAD,0);
            //插入字节码

        } else if ("com/thoughtworks/xstream/core/AbstractReferenceUnmarshaller".equals(targetClass)&&"convert".equals(methodName)) {
            mv.visitVarInsn(ALOAD,1);
            //插入字节码

        } else if ("org/yaml/snakeyaml/constructor/BaseConstructor".equals(targetClass) && "constructObject".equals(methodName)) {
            mv.visitVarInsn(ALOAD,1);
            //插入字节码
        }

    }
}
