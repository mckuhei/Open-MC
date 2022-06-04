package com.SunriseStudio.TeamTCG.openmc.net.handler.handlers;

import com.SunriseStudio.TeamTCG.openmc.net.handler.ChannelHandlerServer;
import com.SunriseStudio.TeamTCG.openmc.net.handler.IServerHandler;
import com.SunriseStudio.TeamTCG.openmc.util.collections.ArrayUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.StandardCharsets;

@ChannelHandlerServer
public class IChunkSender extends IServerHandler {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buffer=(ByteBuf) msg;
        if(ArrayUtil.startWith("chunk_alloc".getBytes(StandardCharsets.UTF_8),buffer.array())){

        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush();
    }
}
