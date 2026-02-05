package org.sitecenter.geoip.client.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * Configuration properties for GeoIP client.
 *
 * <p>Example application.properties:</p>
 * <pre>
 * geoip.client.base-url=https://geoip.sitecenter.org
 * geoip.client.connect-timeout=5s
 * geoip.client.read-timeout=10s
 * </pre>
 */
@ConfigurationProperties(prefix = "geoip.client")
public class GeoIPClientProperties {

    /**
     * Base URL of the GeoIP service.
     */
    private String baseUrl;

    /**
     * Connection timeout. Default is 5 seconds.
     */
    private Duration connectTimeout = Duration.ofSeconds(5);

    /**
     * Read timeout. Default is 10 seconds.
     */
    private Duration readTimeout = Duration.ofSeconds(10);

    /**
     * Whether to enable the GeoIP client auto-configuration.
     */
    private boolean enabled = true;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Duration getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Duration connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Duration getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Duration readTimeout) {
        this.readTimeout = readTimeout;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
