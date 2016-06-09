package itstack.demo.netty.mq.server.socket.netty;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import itstack.demo.netty.mq.server.common.utils.GsonUtils;
import itstack.demo.netty.mq.server.domain.ChannelServerInfo;
import itstack.demo.netty.mq.server.socket.agreement.Body;
import itstack.demo.netty.mq.server.socket.agreement.Head;
import itstack.demo.netty.mq.server.socket.agreement.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class MyServerHandLer extends ChannelHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(MyServerHandLer.class);

    private ChannelServerInfo channelServerInfo;

    public MyServerHandLer(ChannelServerInfo channelServerInfo) {
        this.channelServerInfo = channelServerInfo;
    }

    /*
     * channelAction
     *
     * channel 通道
     * action  活跃的
     *
     * 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
     *
     */
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().localAddress().toString() + " channelActive");
        String str = "您已经开启与服务端链接" + " 您的channelId：" + ctx.channel().id() + " " + new Date() + " " + ctx.channel().localAddress();
        ctx.writeAndFlush(str);
    }

    /*
     * channelInactive
     *
     * channel 	通道
     * Inactive 不活跃的
     *
     * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
     *
     */
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //捕获约即可关闭
        ctx.close();
    }

    /*
     * channelRead
     *
     * channel 通道
     * Read    读
     *
     * 简而言之就是从通道中读取数据，也就是服务端接收客户端发来的数据
     * 但是这个数据在不进行解码时它是ByteBuf类型的后面例子我们在介绍
     *
     */
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        try {
            logger.info("服务端接-接收信息。res：{}", msg);
            channelServerInfo.getTopicSender().send(channelServerInfo.getTopic(), msg.toString());
            logger.info("服务端接-发送MQ完成。");
        } catch (Exception e) {
            logger.error("服务端接收到信息异常。req：{}", GsonUtils.toJson(msg), e);
        }
    }

    /*
     * channelReadComplete
     *
     * channel  通道
     * Read     读取
     * Complete 完成
     *
     * 在通道读取完成后会在这个方法里通知，对应可以做刷新操作
     * ctx.flush()
     *
     */
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /*
     * exceptionCaught
     *
     * exception	异常
     * Caught		抓住
     *
     * 抓住异常，当发生异常的时候，可以做一些相应的处理，比如打印日志、关闭链接
     *
     */
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        // 链接对象添加到缓存
        ctx.close();
    }

}
