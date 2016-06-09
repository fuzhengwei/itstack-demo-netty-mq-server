package itstack.demo.netty.mq.server.domain;

import itstack.demo.netty.mq.server.service.producer.TopicSender;

/**
 * Created by fuzhengwei on 2016/6/9.
 */
public class ChannelServerInfo {

    private int port;

    private String topic;
    private TopicSender topicSender;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public TopicSender getTopicSender() {
        return topicSender;
    }

    public void setTopicSender(TopicSender topicSender) {
        this.topicSender = topicSender;
    }
}
