package com.suryams.restaurantlisting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.suryams.restaurantlisting.dto.RestaurantDTO;
import com.suryams.restaurantlisting.entity.Restaurant;
import com.suryams.restaurantlisting.mapper.RestaurantMapper;
import com.suryams.restaurantlisting.repo.RestaurantRepo;

@Service
public class RestaurantService {

	@Autowired
	RestaurantRepo restaurantRepo;

	public List<RestaurantDTO> findallRestaurants() {
		List<Restaurant> restaurantList = restaurantRepo.findAll();
		List<RestaurantDTO> restDTOList = restaurantList.stream()
				.map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant)).toList();

		return restDTOList;

	}

	public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO) {
		Restaurant restaurant = restaurantRepo
				.save(RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO));
		return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant);
	}
	
	public ResponseEntity<RestaurantDTO> fetchRestaurantById(Integer id){
		java.util.Optional<Restaurant> restaurant=restaurantRepo.findById(id);
		if(restaurant.isPresent()) {
			return new ResponseEntity<>(RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant.get()),HttpStatus.OK);
		}
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		
	}

}
