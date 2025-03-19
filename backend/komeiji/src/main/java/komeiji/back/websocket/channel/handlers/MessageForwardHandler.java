package komeiji.back.websocket.channel.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import komeiji.back.websocket.channel.events.handler.EventHandler;
import komeiji.back.websocket.channel.events.handler.MessageForwardEventHandler;

public class MessageForwardHandler extends ChannelInboundHandlerAdapter  {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        EventHandler eventHandler = new MessageForwardEventHandler(evt);
        if (eventHandler.valid()) {
            eventHandler.handlerEvent(ctx);
        }
    }
}
