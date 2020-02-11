/**
 *
 */
package com.assignment.demo.configs;

import com.assignment.demo.beans.Carrier;
import com.assignment.demo.tools.RestClientTool;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author dgaram
 *
 */
@Configuration
public class AppConfig {
    //    @Autowired
    //    private ApplicationContext cntxt;

    @Bean
    public ObjectMapper setObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setConfig(mapper.getDeserializationConfig()
                .with(JsonParser.Feature.STRICT_DUPLICATE_DETECTION)
                .with(JsonReadFeature.ALLOW_LEADING_ZEROS_FOR_NUMBERS)
                .with(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .with(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY));
        return mapper;
    }

    @Bean
    public RestClientTool<Carrier> setCarrierRestClientTool() {
        return new RestClientTool<>("https://apidev.meep.me/tripplan/api/v1/routers/lisboa/", null);
    }
    //    /**
    //     * Switch between different ways to storage assignments
    //     * @return
    //     */
    //    @Bean
    //    public TareasHelper setTareasHelper() {
    //        cntxt.getBean(Void.class);
    //        return null;
    //    }
}
