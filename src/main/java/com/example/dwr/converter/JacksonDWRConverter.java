package com.example.dwr.converter;

import com.example.dwr.util.CacheNullKeySerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.directwebremoting.dwrp.SimpleOutboundVariable;
import org.directwebremoting.extend.Converter;
import org.directwebremoting.extend.ConverterManager;
import org.directwebremoting.extend.InboundContext;
import org.directwebremoting.extend.InboundVariable;
import org.directwebremoting.extend.MarshallException;
import org.directwebremoting.extend.OutboundContext;
import org.directwebremoting.extend.OutboundVariable;

/**
 * @author Weijun Yu
 * @since 2020-03-22
 */
public class JacksonDWRConverter implements Converter {

  private ConverterManager converterManager;

  @Override
  public void setConverterManager(ConverterManager converterManager) {
    this.converterManager = converterManager;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Object convertInbound(Class paramType, InboundVariable data, InboundContext inctx) throws MarshallException {
    String value = data.getValue();
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.readValue(value, paramType);
    } catch (JsonProcessingException e) {
      throw new MarshallException(this.getClass(), e);
    }
  }

  @Override
  public OutboundVariable convertOutbound(Object object, OutboundContext outctx) throws MarshallException {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.getSerializerProvider().setNullKeySerializer(new CacheNullKeySerializer());
      String jsonified = objectMapper.writeValueAsString(object);
      return new SimpleOutboundVariable(jsonified, outctx, true);
    } catch (JsonProcessingException e) {
      throw new MarshallException(this.getClass(), e);
    }
  }
}
