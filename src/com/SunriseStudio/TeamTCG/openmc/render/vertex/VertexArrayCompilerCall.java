package com.SunriseStudio.TeamTCG.openmc.render.vertex;

import java.util.concurrent.Callable;

public class VertexArrayCompilerCall<T extends VertexArrayCompileCallable> implements Callable<T> {
    public T compileCallable;

    public VertexArrayCompilerCall(T compileCallable){
        this.compileCallable=compileCallable;
    }

    @Override
    public T call() throws Exception {
        this.compileCallable.compile();
        return this.compileCallable;
    }
}
