package komeiji.back.websocket.channel.handlers;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import komeiji.back.websocket.WebSocketServer;
import komeiji.back.websocket.channel.Attributes;
import komeiji.back.websocket.channel.events.handler.EventHandler;
import komeiji.back.websocket.channel.events.handler.HandShakeCompleteEventHandler;
import komeiji.back.websocket.protocol.ProtocolUtils;
import komeiji.back.websocket.protocol.WebSocketFrameProtocol;
import komeiji.back.websocket.session.Session;

public class WebSocketConnectHandler extends ChannelInboundHandlerAdapter {

    private Session session = null;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // disconnect
        Session session = ctx.channel().attr(Attributes.SESSION).get();
        System.out.println("channelDisconnect from: " + session.getId());
        WebSocketServer.getWebSocketSingleServer().getSessionManager().removeSession(session);
        // TODO: 关闭对应的另一个channel，并持久化聊天记录
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        assert session != null;
        WebSocketFrame frame = (WebSocketFrame) msg;
        WebSocketFrameProtocol<?> protocol = ProtocolUtils.getProtocols(frame.getClass());
        Object parsedMsg = protocol.transform(frame,session);
        ctx.fireChannelRead(parsedMsg);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // connect
        EventHandler eventHandler = new HandShakeCompleteEventHandler(evt);
        if(eventHandler.valid() && session == null) {
            eventHandler.handlerEvent(ctx);
            session = ctx.channel().attr(Attributes.SESSION).get();
        }
        super.userEventTriggered(ctx, evt);
    }



}
