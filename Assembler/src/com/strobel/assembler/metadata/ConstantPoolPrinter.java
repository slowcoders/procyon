package com.strobel.assembler.metadata;

import com.strobel.assembler.CodePrinter;
import com.strobel.assembler.ir.ConstantPool;
import com.strobel.core.VerifyArgument;
import com.strobel.decompiler.java.JavaOutputVisitor;

public class ConstantPoolPrinter implements ConstantPool.Visitor {
    private final static int MAX_TAG_LENGTH;

    static {
        int maxTagLength = 0;

        for (final ConstantPool.Tag tag : ConstantPool.Tag.values()) {
            final int length = tag.name().length();

            if (length > maxTagLength) {
                maxTagLength = length;
            }
        }

        MAX_TAG_LENGTH = maxTagLength;
    }

    private final CodePrinter _printer;
    private boolean _isHeaderPrinted;

    public ConstantPoolPrinter(final CodePrinter printer) {
        _printer = VerifyArgument.notNull(printer, "printer");
    }

    protected void printTag(final ConstantPool.Tag tag) {
        _printer.printf("%1$-" + MAX_TAG_LENGTH + "s  ", tag);
    }

    @Override
    public void visit(final ConstantPool.Entry entry) {
        VerifyArgument.notNull(entry, "entry");

        if (!_isHeaderPrinted) {
            _printer.print("Constant Pool:");
            _printer.println();
            _isHeaderPrinted = true;
        }

        _printer.printf("  %1$5d: ", entry.index);
        printTag(entry.getTag());
        entry.accept(this);
        _printer.println();
    }

    @Override
    public void visitTypeInfo(final ConstantPool.TypeInfoEntry info) {
        _printer.printf("#%1$d => %2$s", info.nameIndex, info.getName());
    }

    @Override
    public void visitDoubleConstant(final ConstantPool.DoubleConstantEntry info) {
        _printer.printf("%1$s", info.getConstantValue());
    }

    @Override
    public void visitFieldReference(final ConstantPool.FieldReferenceEntry info) {
        final ConstantPool.NameAndTypeDescriptorEntry nameAndTypeInfo = info.getNameAndTypeInfo();

        _printer.printf(
            "#%1$d.#%2$d => %3$s.%4$s:%5$s",
            info.typeInfoIndex,
            info.nameAndTypeDescriptorIndex,
            info.getClassName(),
            nameAndTypeInfo.getName(),
            nameAndTypeInfo.getType()
        );
    }

    @Override
    public void visitFloatConstant(final ConstantPool.FloatConstantEntry info) {
        _printer.printf("%1$s", info.getConstantValue());
    }

    @Override
    public void visitIntegerConstant(final ConstantPool.IntegerConstantEntry info) {
        _printer.printf("%1$s", info.getConstantValue());
    }

    @Override
    public void visitInterfaceMethodReference(final ConstantPool.InterfaceMethodReferenceEntry info) {
        final ConstantPool.NameAndTypeDescriptorEntry nameAndTypeInfo = info.getNameAndTypeInfo();

        _printer.printf(
            "#%1$d.#%2$d => %3$s.%4$s:%5$s",
            info.typeInfoIndex,
            info.nameAndTypeDescriptorIndex,
            info.getClassName(),
            nameAndTypeInfo.getName(),
            nameAndTypeInfo.getType()
        );
    }

    @Override
    public void visitInvokeDynamicInfo(final ConstantPool.InvokeDynamicInfoEntry info) {
        _printer.printf("#%1$d ", info.bootstrapMethodAttributeIndex);
        info.getNameAndTypeDescriptor().accept(this);
    }

    @Override
    public void visitLongConstant(final ConstantPool.LongConstantEntry info) {
        _printer.printf("%1$s", info.getConstantValue());
    }

    @Override
    public void visitNameAndTypeDescriptor(final ConstantPool.NameAndTypeDescriptorEntry info) {
        _printer.printf(
            "#%1$d:#%2$d => %3$s:%4$s",
            info.nameIndex,
            info.typeDescriptorIndex,
            info.getName(),
            info.getType()
        );
    }

    @Override
    public void visitMethodReference(final ConstantPool.MethodReferenceEntry info) {
        final ConstantPool.NameAndTypeDescriptorEntry nameAndTypeInfo = info.getNameAndTypeInfo();

        _printer.printf(
            "#%1$d.#%2$d => %3$s.%4$s:%5$s",
            info.typeInfoIndex,
            info.nameAndTypeDescriptorIndex,
            info.getClassName(),
            nameAndTypeInfo.getName(),
            nameAndTypeInfo.getType()
        );
    }

    @Override
    public void visitMethodHandle(final ConstantPool.MethodHandleEntry info) {
        _printer.printf("%1$s ", info.referenceKind);
        info.getReference().accept(this);
    }

    @Override
    public void visitMethodType(final ConstantPool.MethodTypeEntry info) {
        _printer.printf("%1$s", info.getType());
    }

    @Override
    public void visitStringConstant(final ConstantPool.StringConstantEntry info) {
        _printer.printf(
            "#%1$s => \"%2$s\"",
            info.stringIndex,
            JavaOutputVisitor.convertString(info.getValue())
        );
    }

    @Override
    public void visitUtf8StringConstant(final ConstantPool.Utf8StringConstantEntry info) {
        _printer.printf(
            "\"%1$s\"",
            JavaOutputVisitor.convertString((String) info.getConstantValue())
        );
    }

    @Override
    public void visitEnd() {
    }
}