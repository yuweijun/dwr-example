package com.example.dwr.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * @author Weijun Yu
 * @since 2020-03-22
 */
public class CacheNullKeySerializer extends StdSerializer<Object>  {

  private static final long serialVersionUID = 1L;

  public static final String NULL_KEY = "null";

  public CacheNullKeySerializer() {
    this(null);
  }

  public CacheNullKeySerializer(Class<Object> t) {
    super(t);
  }

  @Override
  public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    gen.writeFieldName(NULL_KEY);
  }

}
