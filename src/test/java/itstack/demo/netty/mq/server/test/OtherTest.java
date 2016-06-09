package itstack.demo.netty.mq.server.test;

import itstack.demo.netty.mq.server.common.utils.GsonUtils;
import itstack.demo.netty.mq.server.socket.agreement.Body;
import itstack.demo.netty.mq.server.socket.agreement.Head;
import itstack.demo.netty.mq.server.socket.agreement.Message;
import org.junit.Test;

/**
 * Created by fuzhengwei on 2016/6/9.
 */
public class OtherTest {

    @Test
    public void test_getAgreementJson(){

        // 测试消息类
        Head head = new Head();
        head.setSendType(2);

        Body body = new Body();
        body.setContent(GsonUtils.toJson("data:image/png;base64"));

        Message message = new Message();
        message.setBody(body);
        message.setHead(head);

        System.out.println("测试结果："+ GsonUtils.toJson(message));
        //
    }

}
