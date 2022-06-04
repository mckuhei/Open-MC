package com.SunriseStudio.TeamTCG.openmc.render.vertex;

import com.SunriseStudio.TeamTCG.openmc.util.collections.ArrayQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiRenderCompileService<V extends VertexArrayCompileCallable> {
    public ArrayQueue<Future<V>> multiDrawResult=new ArrayQueue<>();
    public ExecutorService multiDrawService;

    public MultiRenderCompileService(int max){
         this.multiDrawService = Executors.newFixedThreadPool(4);
    }

    public void startDrawing(V v){
        this.multiDrawResult.add((Future<V>) this.multiDrawService.submit(new VertexArrayCompilerCall(v)));
    }

    public int getResultSize() {
        return this.multiDrawResult.size();
    }
}
