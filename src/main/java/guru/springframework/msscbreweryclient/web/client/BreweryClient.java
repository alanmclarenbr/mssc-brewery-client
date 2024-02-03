package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
public class BreweryClient {

    private static final String SEPARATOR = "/";
    public final String BEER_PATH_V1 = "/api/v1/beer";
    public final String CUSTOMER_PATH_V1 = "/api/v1/customer";
    @Value("${sfg.brewery.apihost}")
    private String apiHost;
    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID uuid) {
        return restTemplate.getForObject(buildBeerApiPath(uuid), BeerDto.class);
    }

    public URI saveNewBeer(BeerDto beerDto) {
        return restTemplate.postForLocation(buildBeerApiPath(), beerDto);
    }

    public void updateBeer(UUID uuid, BeerDto beerDto) {
        restTemplate.put(buildBeerApiPath(uuid), beerDto);
    }

    public void deleteBeer(UUID uuid) {
        restTemplate.delete(buildBeerApiPath(uuid));
    }

    public CustomerDto getCustomerById(UUID uuid) {
        return restTemplate.getForObject(buildCustomerApiPath(uuid), CustomerDto.class);
    }

    public URI saveNewCustomer(CustomerDto customerDto) {
        return restTemplate.postForLocation(buildCustomerApiPath(), customerDto);
    }

    public void updateCustomer(UUID uuid, CustomerDto customerDto) {
        restTemplate.put(buildCustomerApiPath(uuid), customerDto);
    }

    public void deleteCustomer(UUID uuid) {
        restTemplate.delete(buildCustomerApiPath(uuid));
    }

    private String buildBeerApiPath() {
        return apiHost + BEER_PATH_V1;
    }

    private String buildBeerApiPath(UUID uuid) {
        return buildBeerApiPath() + SEPARATOR + uuid;
    }

    private String buildCustomerApiPath(UUID uuid) {
        return buildCustomerApiPath() + SEPARATOR + uuid;
    }

    private String buildCustomerApiPath() {
        return apiHost + CUSTOMER_PATH_V1;
    }

}
