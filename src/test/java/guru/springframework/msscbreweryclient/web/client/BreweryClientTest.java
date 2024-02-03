package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient client;

    @Test
    void getBeerById() {
        BeerDto beerDto = client.getBeerById(UUID.randomUUID());

        assertThat(beerDto).isNotNull();
    }

    @Test
    void saveNewBeer() {
        URI uri = client.saveNewBeer(BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("ABC")
                .beerStyle("CDE")
                .upc(12)
                .build());

        assertThat(uri).isNotNull();
    }

    @Test
    void updateBeer() {
        BeerDto newBeer = BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("ABC")
                .beerStyle("CDE")
                .upc(12)
                .build();

        client.updateBeer(newBeer.getId(), newBeer);
    }

    @Test
    void deleteBeer() {
        client.deleteBeer(UUID.randomUUID());
    }

    @Test
    void getCustomerById() {
        CustomerDto customerDto = client.getCustomerById(UUID.randomUUID());

        assertThat(customerDto).isNotNull();
    }

    @Test
    void saveNewCustomer() {
        URI uri = client.saveNewCustomer(CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("ABCD")
                .build());

        assertThat(uri).isNotNull();
        System.out.println(uri);
    }

    @Test
    void updateCustomer() {
        client.updateCustomer(UUID.randomUUID(), CustomerDto.builder().name("New Customer").build());
    }

    @Test
    void deleteCustomer() {
        client.deleteCustomer(UUID.randomUUID());
    }
}