package itstack.demo.netty.mq.server.test;

import itstack.demo.netty.mq.server.domain.ChannelServerInfo;
import itstack.demo.netty.mq.server.service.producer.TopicSender;
import itstack.demo.netty.mq.server.socket.netty.ServerSocket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by fuzhengwei on 2016/6/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class StartTest {

    private Logger logger = LoggerFactory.getLogger(StartTest.class);

    private ExecutorService executorService;
    @Resource
    private TopicSender topicSender;
    @Value("${mq.producer.topic.picbase64}")
    private String topic;
    @Value("${netty.port}")
    private int port;
    @Value("${netty.ip}")
    private String ip;


    @Test
    public void test_startNetty() throws Exception{
        logger.info("启动Netty服务开始");
        ChannelServerInfo channelServerInfo = new ChannelServerInfo();
        channelServerInfo.setTopic(topic);
        channelServerInfo.setTopicSender(topicSender);
        channelServerInfo.setPort(port);

        //实例化socket服务
        ServerSocket serverSocket = new ServerSocket(channelServerInfo);
        // 开启线程池
        executorService = Executors.newCachedThreadPool();
        //启动
        executorService.execute(serverSocket);
        logger.info("启动Netty服务完成");
        //定
        System.in.read();
    }

}
