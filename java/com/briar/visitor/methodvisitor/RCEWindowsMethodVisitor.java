package com.briar.visitor.methodvisitor;

import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;
import jdk.internal.org.objectweb.asm.commons.JSRInlinerAdapter;

public class RCEWindowsMethodVisitor extends AdviceAdapter {
    private String checkClass;
    private String targetClass;
    private String name;
    private String desc;
    public RCEWindowsMethodVisitor(int i, MethodVisitor methodVisitor, int access, String name, String desc, String checkClass, String targetClass) {
        super(i, methodVisitor, access, name, desc);
        this.checkClass=checkClass;
        this.name=name;
        this.desc=desc;
        this.targetClass=targetClass;
    }

    //方法区末尾添加指令
    @Override
    public void visitEnd() {
        if (checkClass == null||"".equals(checkClass)){
            //执行带有前缀的native方法
            loadArgs();
            mv.visitMethodInsn(INVOKESTATIC, targetClass,"BYPASSRASP"+name, desc, false);
            returnValue();
            super.visitEnd();
        }else{
            //检查命令执行参数
            //加载方法的第一个参数（假设是命令字符串）

            //用于加载指定索引的局部变量（通常是this引用或第一个参数）到操作栈上
            mv.visitVarInsn(ALOAD,0); //命令+参数
            mv.visitMethodInsn(INVOKESTATIC, checkClass, "checkWindows", "(Ljava/lang/String;)V", false);
            //执行带有前缀的native方法

            //用于加载所有方法参数到操作栈上
            loadArgs();
            //加载native方法所需的参数到操作栈上，确保BYPASSRASP+name方法所需的所有参数都被正确加载
            mv.visitMethodInsn(INVOKESTATIC, targetClass,"BYPASSRASP"+name, desc, false);
            //处理方法调用后的返回值
            returnValue();
            super.visitEnd();
        }

    }
}
