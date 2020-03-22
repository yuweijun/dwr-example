package com.example.dwr.servlet;

import com.example.dwr.controller.UserController;
import org.directwebremoting.Container;
import org.directwebremoting.convert.EnumConverter;
import org.directwebremoting.create.NewCreator;
import org.directwebremoting.extend.AccessControl;
import org.directwebremoting.extend.ConverterManager;
import org.directwebremoting.extend.CreatorManager;
import org.directwebremoting.servlet.DwrServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Weijun Yu
 * @since 2020-03-22
 */
public class UserServlet extends DwrServlet {

  private static final Map<Class<?>, Map<Class<? extends Annotation>, List<Method>>> METHOD_CACHE = new HashMap<>();

  public void init(ServletConfig servletConfig) throws ServletException {
    super.init(servletConfig);

    Container container = this.getContainer();
    CreatorManager creatorManager = (CreatorManager) container.getBean(CreatorManager.class.getName());
    AccessControl accessControl = (AccessControl) container.getBean(AccessControl.class.getName());
    ConverterManager converterManager = (ConverterManager) container.getBean(ConverterManager.class.getName());

    System.out.println(servletConfig.getClass().getName());
    System.out.println(creatorManager.getClass().getName());
    System.out.println(accessControl.getClass().getName());
    System.out.println(converterManager.getClass().getName());

    NewCreator userCreator = new NewCreator();
    Class<UserController> userControllerClass = UserController.class;
    userCreator.setClassName(userControllerClass.getName());
    String userController = "userController";
    creatorManager.addCreator(userController, userCreator);

    List<Method> methods = getMethods(userControllerClass, IncludeRule.class);
    Iterator<Method> miter = methods.iterator();
    while (miter.hasNext()) {
      accessControl.addIncludeRule(userController, miter.next().getName());
    }

    EnumConverter enumConverter = new EnumConverter();
    converterManager.addConverter("com.example.dwr.enums.*", enumConverter);
  }

  static synchronized List<Method> getMethods(final Class<?> srcClass,
      final Class<? extends Annotation> annotationClass) {
    Map<Class<? extends Annotation>, List<Method>> cachedResult = METHOD_CACHE.get(srcClass);
    List<Method> result = cachedResult == null ? null : cachedResult.get(annotationClass);
    if (result == null) {
      result = new ArrayList<>();
      final Method[] methods = srcClass.getMethods();
      for (final Method m : methods) {
        if (m.isAnnotationPresent(annotationClass)) {
          result.add(m);
        }
      }
      if (cachedResult == null) {
        cachedResult = new HashMap<>();
        cachedResult.put(annotationClass, result);
        METHOD_CACHE.put(srcClass, cachedResult);
      } else {
        cachedResult.put(annotationClass, result);
      }
    }
    return result;
  }
}
