package com.customertest.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.assignment.springboot.mongo.controller.Customercontroller;
import com.assignment.springboot.mongo.dao.Customerdao;
import com.assignment.springboot.mongo.model.Customer;

import junit.framework.Assert;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//(classes = Myapplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class CustomerControllerTest {
     @Autowired
     private TestRestTemplate restTemplate;
     @InjectMocks
    Customercontroller customercontroller;
      
     @Mock
     Customerdao customerdao;
     @LocalServerPort
     private int port;

     private String getRootUrl() {
      //   return "http://localhost:" + port;
    	 return "http://localhost:8080";
     }

     @Test
     public void contextLoads() {

     }

     @SuppressWarnings("deprecation")
	@Test
     public void testGetAllCustomer() throws URISyntaxException {
          
        RestTemplate restTemplate = new RestTemplate();
        
        final String baseUrl = "http://localhost:8080/customers/";
        URI uri = new URI(baseUrl);
     
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
         
        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        assertNotNull(result.getBody());
    }

    @Test
    public void testGetCustomerById() throws URISyntaxException {
   RestTemplate restTemplate = new RestTemplate();
        
        final String baseUrl = "http://localhost:8080/customers/getbyid/1";
        URI uri = new URI(baseUrl);
     
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
         
        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        assertNotNull(result.getBody());
    }

    @Test
    public void testCreateCustomer() {
        Customer cust = new Customer();
        cust.setId(1);
        cust.setName("Sandesh");
        cust.setAddress("Bangalore");
        ResponseEntity<Customer> postResponse = restTemplate.postForEntity(getRootUrl() + "/create", cust, Customer.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateCustomer() {
        int id = 1;
        Customer cust = restTemplate.getForObject(getRootUrl() + "/update/" + id, Customer.class);
        cust.setName("Sandesh");
        restTemplate.put(getRootUrl() + "/update/" + id, cust);
        Customer updatedCustomer = restTemplate.getForObject(getRootUrl() + "/update/" + id, Customer.class);
        assertNotNull(updatedCustomer);
        
        
    }

    @Test
    public void testDeleteCustomer() {
         int id = 2;
         Customer cust = restTemplate.getForObject(getRootUrl() + "/delete/" + id, Customer.class);
         assertNotNull(cust);
         restTemplate.delete(getRootUrl() + "/delete/" + id);
         try {
              cust = restTemplate.getForObject(getRootUrl() + "/delete/" + id, Customer.class);
         } catch (final HttpClientErrorException e) {
              assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
         }
    }
}