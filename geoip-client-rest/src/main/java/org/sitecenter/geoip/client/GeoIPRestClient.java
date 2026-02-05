package org.sitecenter.geoip.client;

import org.sitecenter.geoip.model.GeoIP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

/**
 * REST client implementation for GeoIP service using Spring RestClient.
 * This implementation is fully synchronous and works well with virtual threads.
 *
 * <p>Example usage with Spring:</p>
 * <pre>
 * {@code
 * @Bean
 * public GeoIPClient geoIPClient(RestClient.Builder restClientBuilder) {
 *     RestClient restClient = restClientBuilder
 *         .baseUrl("https://geoip.example.com")
 *         .build();
 *     return new GeoIPRestClient(restClient);
 * }
 * }
 * </pre>
 *
 * <p>Example usage standalone:</p>
 * <pre>
 * {@code
 * RestClient restClient = RestClient.builder()
 *     .baseUrl("https://geoip.example.com")
 *     .build();
 * GeoIPClient client = new GeoIPRestClient(restClient);
 * GeoIP geoIP = client.findByIp("8.8.8.8");
 * }
 * </pre>
 */
public class GeoIPRestClient implements GeoIPClient {

    private static final Logger log = LoggerFactory.getLogger(GeoIPRestClient.class);

    private final RestClient restClient;

    /**
     * Create a new GeoIPRestClient with the given RestClient.
     * The RestClient should have the base URL configured.
     *
     * @param restClient the RestClient to use for HTTP requests
     */
    public GeoIPRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public GeoIP findByIp(String ip) {
        if (ip == null || ip.isBlank()) {
            log.warn("findByIp called with null or blank IP");
            return null;
        }

        try {
            log.debug("Looking up GeoIP for: {}", ip);
            return restClient.get()
                    .uri("/api/geo/ip/{ip}", ip)
                    .retrieve()
                    .body(GeoIP.class);
        } catch (RestClientException ex) {
            log.error("Failed to lookup GeoIP for {}: {}", ip, ex.getMessage());
            return null;
        }
    }
}
