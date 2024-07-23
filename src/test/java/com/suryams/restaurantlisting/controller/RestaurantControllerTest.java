package com.suryams.restaurantlisting.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.suryams.restaurantlisting.dto.RestaurantDTO;
import com.suryams.restaurantlisting.service.RestaurantService;

public class RestaurantControllerTest {
//Arrange -Act-Assert
	@InjectMocks
	RestaurantController restaurantController;

	@Mock
	RestaurantService restaurantService;

	@BeforeEach
	// in order for Mock and InjectMocks annotations to take effect,
	// you need to call MockitoAnnotations.openMocks(this);
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testFetchAllRestaurants() {
		//Arranging the test data
		List<RestaurantDTO> mockRestaurants = Arrays.asList(
				new RestaurantDTO(1, "Restaurant1", "Address1", "city1", "desc1"),
				new RestaurantDTO(2, "Restaurant2", "Address2", "city2", "desc2"));
		
		//Act
		when(restaurantService.findallRestaurants()).thenReturn(mockRestaurants);

		// call the controller method
		ResponseEntity<List<RestaurantDTO>> response = restaurantController.fetchAllRestaurants();
		// verify the response
		
		//Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(mockRestaurants, response.getBody());
		// Verify that the service method was called
		verify(restaurantService, times(1)).findallRestaurants();
	}
	
	@Test
	public void testSaveRestaurant() {
		// Create a mock restaurant to be saved
        RestaurantDTO mockRestaurant =  new RestaurantDTO(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");
        when(restaurantService.addRestaurant(mockRestaurant)).thenReturn(mockRestaurant);
        // Call the controller method
        ResponseEntity<RestaurantDTO> response = restaurantController.saveRestaurant(mockRestaurant);

        // Verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockRestaurant, response.getBody());

        // Verify that the service method was called
        verify(restaurantService, times(1)).addRestaurant(mockRestaurant);
	}
	
	 @Test
	    public void testFindRestaurantById() {
	        // Create a mock restaurant ID
	        Integer mockRestaurantId = 1;

	        // Create a mock restaurant to be returned by the service
	        RestaurantDTO mockRestaurant = new RestaurantDTO(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");

	        // Mock the service behavior
	        when(restaurantService.fetchRestaurantById(mockRestaurantId)).thenReturn(new ResponseEntity<>(mockRestaurant, HttpStatus.OK));

	        // Call the controller method
	        ResponseEntity<RestaurantDTO> response = restaurantController.fetchRestaurantById(mockRestaurantId);

	        // Verify the response
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(mockRestaurant, response.getBody());

	        // Verify that the service method was called
	        verify(restaurantService, times(1)).fetchRestaurantById(mockRestaurantId);
	    }

}
