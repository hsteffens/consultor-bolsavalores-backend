package br.furb.consultor.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.map.ext.JodaDeserializers.LocalDateDeserializer;
import org.codehaus.jackson.map.ext.JodaSerializers.LocalDateSerializer;
import org.joda.time.LocalDate;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

	final ObjectMapper mapper = new ObjectMapper();

	public ObjectMapperContextResolver() {

		mapper.configure(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		final SimpleModule module = new SimpleModule("GPJacksonModule", new Version(0, 0, 0, null));
		module.addSerializer(LocalDate.class, new LocalDateSerializer());
		LocalDateDeserializer deser = new LocalDateDeserializer();
		module.addDeserializer(LocalDate.class, deser);

		mapper.registerModule(module);
	}

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return mapper;
	}  
}