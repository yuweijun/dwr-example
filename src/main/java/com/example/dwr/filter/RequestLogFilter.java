package com.example.dwr.filter;

import org.directwebremoting.AjaxFilter;
import org.directwebremoting.AjaxFilterChain;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Weijun Yu
 * @since 2020-03-22
 */
public class RequestLogFilter implements AjaxFilter {

  public Object doFilter(Object obj, Method method, Object[] params, AjaxFilterChain chain) throws Exception {
    System.out.println("object is : " + obj);
    System.out.println("method is : " + method.getName());
    System.out.println("params is : " + Arrays.toString(params));

    return chain.doFilter(obj, method, params);
  }
}

