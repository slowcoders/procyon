/*
 * CommandLineOptions.java
 *
 * Copyright (c) 2013 Mike Strobel
 *
 * This source code is based Mono.Cecil from Jb Evain, Copyright (c) Jb Evain;
 * and ILSpy/ICSharpCode from SharpDevelop, Copyright (c) AlphaSierraPapa.
 *
 * This source code is subject to terms and conditions of the Apache License, Version 2.0.
 * A copy of the license can be found in the License.html file at the root of this distribution.
 * By using this source code in any fashion, you are agreeing to be bound by the terms of the
 * Apache License, Version 2.0.
 *
 * You must not remove this notice, or any other, from this software.
 */

package com.strobel.decompiler;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class CommandLineOptions {
    @Parameter(description = "<class names>")
    private final List<String> _classNames = new ArrayList<>();

    @Parameter(names = { "-?", "--help" }, help = true, description = "Display this usage information and exit.")
    private boolean _printUsage;

    @Parameter(names = { "-s", "--show-synthetic" }, description = "Show synthetic (compiler-generated) members.")
    private boolean _showSyntheticMembers;

    @Parameter(names = { "-ev", "--exception-variables" }, description = "Always generate exception variables for catch blocks.")
    private boolean _alwaysGenerateExceptionVariableForCatchBlocks;

    @Parameter(names = { "-b", "--bytecode-ast" }, description = "Output Bytecode AST instead of Java.")
    private boolean _bytecodeAst;

    @Parameter(names = { "-u", "--unoptimized" }, description = "Show unoptimized code (only in combination with -b).")
    private boolean _unoptimized;

    public final List<String> getClassNames() {
        return _classNames;
    }

    public final boolean isBytecodeAst() {
        return _bytecodeAst;
    }

    public final void setBytecodeAst(final boolean bytecodeAst) {
        _bytecodeAst = bytecodeAst;
    }

    public final boolean isUnoptimized() {
        return _unoptimized;
    }

    public final void setUnoptimized(final boolean unoptimized) {
        _unoptimized = unoptimized;
    }

    public final boolean getShowSyntheticMembers() {
        return _showSyntheticMembers;
    }

    public final void setShowSyntheticMembers(final boolean showSyntheticMembers) {
        _showSyntheticMembers = showSyntheticMembers;
    }

    public final boolean getPrintUsage() {
        return _printUsage;
    }

    public final void setPrintUsage(final boolean printUsage) {
        _printUsage = printUsage;
    }

    public final boolean getAlwaysGenerateExceptionVariableForCatchBlocks() {
        return _alwaysGenerateExceptionVariableForCatchBlocks;
    }

    public final void setAlwaysGenerateExceptionVariableForCatchBlocks(final boolean names) {
        _alwaysGenerateExceptionVariableForCatchBlocks = names;
    }
}