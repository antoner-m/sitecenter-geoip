package org.sitecenter.geoip.config;

import com.maxmind.db.CHMCache;
import com.maxmind.db.Reader;
import com.maxmind.geoip2.DatabaseReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * <h2>GeoLocationConfig</h2>
 */
@Slf4j
@Configuration
public class GeoLocationConfig {

    private final ResourceLoader resourceLoader;

    public GeoLocationConfig(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    @Scope("singleton")
    public DatabaseReader cityDatabaseReader() {
        try {
            log.info("GeoLocationConfig: Trying to load GeoLite2-City database...");

            Resource resource = resourceLoader.getResource("classpath:maxmind/GeoLite2-City.mmdb");
            InputStream dbAsStream = resource.getInputStream();

            log.info("GeoLocationConfig: Database was loaded successfully.");

            // Initialize the reader
           return  new DatabaseReader
                    .Builder(dbAsStream)
                    .fileMode(Reader.FileMode.MEMORY)
                    .withCache(new CHMCache())
                    .build();

        } catch (IOException | NullPointerException e) {
            log.error("Database reader cound not be initialized. ", e);
            return null;
        }
    }

    @Bean
    @Scope("singleton")
    public DatabaseReader countryDatabaseReader() {
        try {
            log.info("GeoLocationConfig: Trying to load GeoLite2-Country database...");

            Resource resource = resourceLoader.getResource("classpath:maxmind/GeoLite2-Country.mmdb");
            InputStream dbAsStream = resource.getInputStream();

            log.info("GeoLocationConfig: Database was loaded successfully.");

            // Initialize the reader
            return new DatabaseReader
                    .Builder(dbAsStream)
                    .fileMode(Reader.FileMode.MEMORY)
                    .withCache(new CHMCache())
                    .build();

        } catch (IOException | NullPointerException e) {
            log.error("Database reader cound not be initialized. ", e);
            return null;
        }
    }
}
