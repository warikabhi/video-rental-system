
/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.services;

import com.crio.qeats.dto.Restaurant;
import com.crio.qeats.exchanges.GetRestaurantsRequest;
import com.crio.qeats.exchanges.GetRestaurantsResponse;
import com.crio.qeats.repositoryservices.RestaurantRepositoryService;
import com.crio.qeats.utils.GeoUtils;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RestaurantServiceImpl implements RestaurantService {

  private final Double peakHoursServingRadiusInKms = 3.0;
  private final Double normalHoursServingRadiusInKms = 5.0;
  
  @Autowired
  private RestaurantRepositoryService restaurantRepositoryService;


  // TODO: CRIO_TASK_MODULE_RESTAURANTSAPI - Implement findAllRestaurantsCloseby.
  // Check RestaurantService.java file for the interface contract.
  @Override
  public GetRestaurantsResponse findAllRestaurantsCloseBy(GetRestaurantsRequest getRestaurantsRequest, LocalTime currentTime) {
    List<Restaurant> restaurant;
    int h = currentTime.getHour();
    int m = currentTime.getMinute();
    Double latitude = getRestaurantsRequest.getLatitude();
    Double longitude = getRestaurantsRequest.getLongitude();

    if((h >= 8 && h<=9) || (h==10 && m ==0) || (h==13) || (h==14 && m==0) || (h>=19 && h<=20) || (h==21 && m ==0)){

      // The method identifies "peak hours" based on specific time slots:
      // Morning Peak: 8:00 AM to 10:00 AM (ending at 10:00 AM sharp).
      // Afternoon Peak: 1:00 PM to 2:00 PM (ending at 2:00 PM sharp).
      // Evening Peak: 7:00 PM to 9:00 PM (ending at 9:00 PM sharp).
      restaurant = restaurantRepositoryService.findAllRestaurantsCloseBy(latitude, longitude, currentTime, peakHoursServingRadiusInKms);
    }
    else{
      restaurant = restaurantRepositoryService.findAllRestaurantsCloseBy(latitude, longitude, currentTime, normalHoursServingRadiusInKms);
    }

    GetRestaurantsResponse getRestaurantsResponse = new GetRestaurantsResponse(restaurant);
    log.info(getRestaurantsResponse);
     return getRestaurantsResponse;
  }


}

