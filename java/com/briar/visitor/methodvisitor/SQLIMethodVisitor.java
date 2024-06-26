package com.briar.visitor.methodvisitor;

import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;

public class SQLIMethodVisitor extends AdviceAdapter {
    private String checkClass;
    private String methodName;
    private String currentClass;
    public SQLIMethodVisitor(int i, MethodVisitor methodVisitor, int access, String name, String desc, String checkClass,String currentClass) {
        super(i, methodVisitor, access, name, desc);
        this.checkClass=checkClass;
        this.methodName=name;
        this.currentClass=currentClass;
    }

    @Override
    protected void onMethodEnter() {
        if (checkClass == null||"".equals(checkClass)){
            return;
        }
        if ("com/mysql/cj/jdbc/StatementImpl".equals(currentClass)||"com/mysql/jdbc/StatementImpl".equals(currentClass)){
            mv.visitVarInsn(ALOAD,1);
            //插入字节码

            // 调用 checkClass 类的构造方法
        } else if ("com/mysql/cj/jdbc/ClientPreparedStatement".equals(currentClass) || "com/mysql/jdbc/PreparedStatement".equals(currentClass)) {
            mv.visitVarInsn(ALOAD, 0);
            //插入字节码

        }
    }
}
