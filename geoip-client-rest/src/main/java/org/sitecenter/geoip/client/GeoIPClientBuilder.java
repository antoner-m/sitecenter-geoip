package org.sitecenter.geoip.client;

import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.time.Duration;

/**
 * Builder for creating GeoIPClient instances with sensible defaults.
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * GeoIPClient client = GeoIPClientBuilder.create()
 *     .baseUrl("https://geoip.example.com")
 *     .connectTimeout(Duration.ofSeconds(5))
 *     .readTimeout(Duration.ofSeconds(10))
 *     .build();
 * }
 * </pre>
 */
public class GeoIPClientBuilder {

    private String baseUrl;
    private Duration connectTimeout = Duration.ofSeconds(5);
    private Duration readTimeout = Duration.ofSeconds(10);
    private RestClient restClient;

    private GeoIPClientBuilder() {
    }

    /**
     * Create a new builder instance.
     */
    public static GeoIPClientBuilder create() {
        return new GeoIPClientBuilder();
    }

    /**
     * Set the base URL for the GeoIP service.
     *
     * @param baseUrl the base URL (e.g., "https://geoip.example.com")
     * @return this builder
     */
    public GeoIPClientBuilder baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    /**
     * Set the connection timeout.
     *
     * @param connectTimeout the connection timeout
     * @return this builder
     */
    public GeoIPClientBuilder connectTimeout(Duration connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    /**
     * Set the read timeout.
     *
     * @param readTimeout the read timeout
     * @return this builder
     */
    public GeoIPClientBuilder readTimeout(Duration readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    /**
     * Use an existing RestClient instead of creating a new one.
     * If set, baseUrl, connectTimeout, and readTimeout are ignored.
     *
     * @param restClient the RestClient to use
     * @return this builder
     */
    public GeoIPClientBuilder restClient(RestClient restClient) {
        this.restClient = restClient;
        return this;
    }

    /**
     * Build the GeoIPClient instance.
     *
     * @return a new GeoIPClient
     * @throws IllegalStateException if baseUrl is not set and no RestClient is provided
     */
    public GeoIPClient build() {
        if (restClient != null) {
            return new GeoIPRestClient(restClient);
        }

        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalStateException("baseUrl must be set or provide a RestClient");
        }

        ClientHttpRequestFactorySettings settings = ClientHttpRequestFactorySettings.DEFAULTS
                .withConnectTimeout(connectTimeout)
                .withReadTimeout(readTimeout);
        ClientHttpRequestFactory requestFactory = ClientHttpRequestFactories.get(settings);

        RestClient client = RestClient.builder()
                .baseUrl(baseUrl)
                .requestFactory(requestFactory)
                .build();

        return new GeoIPRestClient(client);
    }
}
