package org.sitecenter.geoip.client.autoconfigure;

import org.sitecenter.geoip.client.GeoIPClient;
import org.sitecenter.geoip.client.GeoIPClientBuilder;
import org.sitecenter.geoip.client.GeoIPRestClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

/**
 * Auto-configuration for GeoIP client.
 *
 * <p>This auto-configuration is enabled when:</p>
 * <ul>
 *   <li>RestClient class is on the classpath</li>
 *   <li>geoip.client.enabled is true (default)</li>
 *   <li>geoip.client.base-url is configured</li>
 *   <li>No other GeoIPClient bean is defined</li>
 * </ul>
 *
 * <p>Example configuration:</p>
 * <pre>
 * geoip.client.base-url=https://geoip.sitecenter.org
 * geoip.client.connect-timeout=5s
 * geoip.client.read-timeout=10s
 * </pre>
 */
@AutoConfiguration
@ConditionalOnClass(RestClient.class)
@ConditionalOnProperty(prefix = "geoip.client", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(GeoIPClientProperties.class)
public class GeoIPClientAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "geoip.client", name = "base-url")
    public GeoIPClient geoIPClient(GeoIPClientProperties properties) {
        return GeoIPClientBuilder.create()
                .baseUrl(properties.getBaseUrl())
                .connectTimeout(properties.getConnectTimeout())
                .readTimeout(properties.getReadTimeout())
                .build();
    }
}
