package com.briar.visitor.methodvisitor;

import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;

public class FileOperatorMethodVisitor extends AdviceAdapter {
    private String checkClass;
    private String methodName;
    private String currentClass;
    public FileOperatorMethodVisitor(int i, MethodVisitor methodVisitor, int access, String name, String desc, String checkClass, String currentClass) {
        super(i, methodVisitor, access, name, desc);
        this.checkClass=checkClass;
        this.methodName=name;
        this.currentClass=currentClass;
    }

    @Override
    protected void onMethodEnter() {
        if ("java/io/FileInputStream".equals(currentClass)){
            mv.visitVarInsn(ALOAD,1);
        } else if ("java/io/FileOutputStream".equals(currentClass)) {
            mv.visitVarInsn(ALOAD,1);
        } else if ("java/io/File".equals(currentClass)) {
            if ("delete".equals(methodName)){
                mv.visitVarInsn(ALOAD,0);
            } else if ("renameTo".equals(methodName)) {
                mv.visitVarInsn(ALOAD,0);
            } else if ("list".equals(methodName)) {
                mv.visitVarInsn(ALOAD,0);
            }
        } else if ("java/io/RandomAccessFile".equals(currentClass)) {
            mv.visitVarInsn(ALOAD,1);
            mv.visitVarInsn(ALOAD,2);
        } else if ("java/nio/file/Files".equals(currentClass)) {
            if ("readAllBytes".equals(methodName)||"newInputStream".equals(methodName)){
                mv.visitVarInsn(ALOAD,0);
            } else if ("createFile".equals(methodName) || "newOutputStream".equals(methodName) || "copy".equals(methodName) || "move".equals(methodName)) {
                mv.visitVarInsn(ALOAD,0);
            } else if ("delete".equals(methodName) || "deleteIfExists".equals(methodName)) {
                mv.visitVarInsn(ALOAD,0);
            } else if ("newDirectoryStream".equals(methodName)) {
                mv.visitVarInsn(ALOAD,0);

            }
        }
    }
}
