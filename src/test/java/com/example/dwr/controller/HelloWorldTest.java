package com.example.dwr.controller;

import com.example.dwr.vo.UserVO;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.convert.BeanConverter;
import org.directwebremoting.dwrp.DefaultConverterManager;
import org.directwebremoting.extend.EnginePrivate;
import org.directwebremoting.extend.MarshallException;
import org.directwebremoting.extend.Replies;
import org.directwebremoting.extend.Reply;
import org.directwebremoting.extend.ScriptBufferUtil;
import org.directwebremoting.extend.ScriptConduit;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Weijun Yu
 * @since 2020-03-21
 */
public class HelloWorldTest {

  @Test
  public void test() throws IOException, MarshallException {
    ScriptConduit conduit = new ScriptConduit(1) {
      @Override
      public boolean addScript(ScriptBuffer scriptBuffer) throws MarshallException {
        DefaultConverterManager converterManager = new DefaultConverterManager();
        converterManager.addConverter(UserVO.class.getName(), new BeanConverter());
        String script = ScriptBufferUtil.createOutput(scriptBuffer, converterManager);
        System.out.println(script);

        return true;
      }
    };

    Replies replies = new Replies("1");
    UserVO user = new UserVO();
    user.setId(1);
    user.setName("userName");
    Reply reply1 = new Reply("0", user);
    replies.addReply(reply1);
    String batchId = replies.getBatchId();

    for (int i = 0; i < replies.getReplyCount(); i++) {
      Reply reply = replies.getReply(i);
      String callId = reply.getCallId();

      if (reply.getThrowable() != null) {
        Throwable ex = reply.getThrowable();
        EnginePrivate.remoteHandleException(conduit, batchId, callId, ex);
      } else {
        Object data = reply.getReply();
        EnginePrivate.remoteHandleCallback(conduit, batchId, callId, data);
      }
    }
  }

}