package com.example.dwr.controller;

import com.example.dwr.vo.UserVO;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.convert.ArrayConverter;
import org.directwebremoting.convert.BeanConverter;
import org.directwebremoting.convert.CollectionConverter;
import org.directwebremoting.convert.DateConverter;
import org.directwebremoting.convert.ExceptionConverter;
import org.directwebremoting.convert.MapConverter;
import org.directwebremoting.convert.MinimalistExceptionConverter;
import org.directwebremoting.convert.PrimitiveConverter;
import org.directwebremoting.convert.StringConverter;
import org.directwebremoting.convert.URLConverter;
import org.directwebremoting.dwrp.DefaultConverterManager;
import org.directwebremoting.extend.Converter;
import org.directwebremoting.extend.EnginePrivate;
import org.directwebremoting.extend.MarshallException;
import org.directwebremoting.extend.Replies;
import org.directwebremoting.extend.Reply;
import org.directwebremoting.extend.ScriptBufferUtil;
import org.directwebremoting.extend.ScriptConduit;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

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
        DefaultConverterManager converterManager = getDefaultConverterManager();

        String script = ScriptBufferUtil.createOutput(scriptBuffer, converterManager);
        System.out.println(script);

        return true;
      }
    };

    Replies replies = new Replies("batchId:0");
    Reply reply1 = getReply(1, "name1");
    Reply reply2 = getReply(2, "name2");
    replies.addReply(reply1);
    replies.addReply(reply2);

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

  private Reply getReply(int id, String name) {
    UserVO userVO = new UserVO();
    userVO.setId(id);
    userVO.setName(name);
    userVO.setCreatedAt(new Date());

    return new Reply("" + id, userVO);
  }

  private DefaultConverterManager getDefaultConverterManager() {
    DefaultConverterManager converterManager = new DefaultConverterManager();
    BeanConverter converter = new BeanConverter();
    converter.setConverterManager(converterManager);
    converterManager.addConverter(UserVO.class.getName(), converter);

    Converter dateConverter = new DateConverter();
    converterManager.addConverter("java.sql.Time", dateConverter);
    converterManager.addConverter("java.sql.Timestamp", dateConverter);
    converterManager.addConverter("java.sql.Date", dateConverter);
    converterManager.addConverter("java.util.Calendar", dateConverter);
    converterManager.addConverter("java.util.Date", dateConverter);

    Converter primitiveConverter = new PrimitiveConverter();
    converterManager.addConverter("char", primitiveConverter);
    converterManager.addConverter("java.lang.Character", primitiveConverter);
    converterManager.addConverter("short", primitiveConverter);
    converterManager.addConverter("java.lang.Boolean", primitiveConverter);
    converterManager.addConverter("java.lang.Long", primitiveConverter);
    converterManager.addConverter("java.lang.Float", primitiveConverter);
    converterManager.addConverter("long", primitiveConverter);
    converterManager.addConverter("float", primitiveConverter);
    converterManager.addConverter("double", primitiveConverter);
    converterManager.addConverter("java.lang.Double", primitiveConverter);
    converterManager.addConverter("boolean", primitiveConverter);
    converterManager.addConverter("int", primitiveConverter);
    converterManager.addConverter("java.lang.Integer", primitiveConverter);
    converterManager.addConverter("java.lang.Short", primitiveConverter);
    converterManager.addConverter("byte", primitiveConverter);
    converterManager.addConverter("java.lang.Byte", primitiveConverter);

    Converter arrayConverter = new ArrayConverter();
    converterManager.addConverter("[L*", arrayConverter);
    converterManager.addConverter("[F", arrayConverter);
    converterManager.addConverter("[C", arrayConverter);
    converterManager.addConverter("[D", arrayConverter);
    converterManager.addConverter("[I", arrayConverter);
    converterManager.addConverter("[J", arrayConverter);
    converterManager.addConverter("[S", arrayConverter);
    converterManager.addConverter("[Z", arrayConverter);

    converterManager.addConverter("java.lang.Throwable", new MinimalistExceptionConverter());
    converterManager.addConverter("org.directwebremoting.io.DwrConvertedException", new ExceptionConverter());
    converterManager.addConverter("java.util.Map", new MapConverter());
    converterManager.addConverter("java.util.Collection", new CollectionConverter());
    converterManager.addConverter("java.lang.String", new StringConverter());
    converterManager.addConverter("java.net.URL", new URLConverter());
    return converterManager;
  }

}