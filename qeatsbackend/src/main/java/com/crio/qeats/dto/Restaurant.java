
/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: CRIO_TASK_MODULE_SERIALIZATION
//  Implement Restaurant class.
// Complete the class such that it produces the following JSON during serialization.
// {
//  "restaurantId": "10",
//  "name": "A2B",
//  "city": "Hsr Layout",
//  "imageUrl": "www.google.com",
//  "latitude": 20.027,
//  "longitude": 30.0,
//  "opensAt": "18:00",
//  "closesAt": "23:00",
//  "attributes": [
//    "Tamil",
//    "South Indian"
//  ]
// }

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Restaurant {
    @NotNull String restaurantId;
    @NotNull String name;
    @NotNull String city;
    @NotNull String imageUrl;
    
    @NotNull Double latitude;
    @NotNull Double longitude;
    @NotNull String opensAt;
    @NotNull String closesAt;
    @NotNull List<String> attributes = new ArrayList<>();
}

