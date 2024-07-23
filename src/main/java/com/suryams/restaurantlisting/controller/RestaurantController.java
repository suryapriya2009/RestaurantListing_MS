package com.suryams.restaurantlisting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suryams.restaurantlisting.dto.RestaurantDTO;
import com.suryams.restaurantlisting.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin
public class RestaurantController {
	@Autowired
	RestaurantService restaurantService;
	
	@GetMapping("/fetchAllRestaurants")
	public ResponseEntity<List<RestaurantDTO>> fetchAllRestaurants(){
		List<RestaurantDTO> restaurantDTOs = restaurantService.findallRestaurants();
		return new ResponseEntity<>(restaurantDTOs, HttpStatus.OK);
	}
	
	@PostMapping("/addRestaurant")
	public ResponseEntity<RestaurantDTO> saveRestaurant(@RequestBody RestaurantDTO restaurantDTO){
		RestaurantDTO restDTOAdded = restaurantService.addRestaurant(restaurantDTO);
		return new ResponseEntity<>(restDTOAdded,HttpStatus.CREATED);
	}
	
	@GetMapping("/fetchRestaurantById/{id}")
	public ResponseEntity<RestaurantDTO> fetchRestaurantById(@PathVariable Integer id){
		return restaurantService.fetchRestaurantById(id);
	}
}
