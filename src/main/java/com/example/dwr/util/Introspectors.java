package com.example.dwr.util;

import org.apache.log4j.Logger;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Weijun Yu
 * @since 2020-03-23
 */
public class Introspectors {

  private static final Logger LOGGER = Logger.getLogger(Introspectors.class);

  public static Map<String, Object> introspect(Object obj) throws Exception {
    Map<String, Object> result = new HashMap<>();
    BeanInfo info = Introspector.getBeanInfo(obj.getClass());
    for (PropertyDescriptor descriptor : info.getPropertyDescriptors()) {
      Method reader = descriptor.getReadMethod();
      String name = descriptor.getName();
      LOGGER.info("name is : " + name);

      if (name.equals("class")) {
        continue;
      }

      if (reader != null) {
        result.put(descriptor.getName(), reader.invoke(obj));
      }
    }

    return result;
  }

}
