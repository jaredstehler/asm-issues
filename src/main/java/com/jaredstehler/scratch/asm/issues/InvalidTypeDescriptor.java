package com.jaredstehler.scratch.asm.issues;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class InvalidTypeDescriptor {
    public static void main(String[] args) throws Exception {
        ClassReader classReader = new ClassReader(MyRecord.class.getName());

        classReader.accept(new ClassVisitor(Opcodes.ASM9) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {

                MethodVisitor mv = new MethodVisitor(Opcodes.ASM9) {
                    @Override
                    public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
                        Type argument = (Type) bootstrapMethodArguments[0];
                        System.out.println("Type: " + argument.getDescriptor());
                        argument.getReturnType();
                    }
                };

                return mv;
            }
        }, 0);

    }
}
