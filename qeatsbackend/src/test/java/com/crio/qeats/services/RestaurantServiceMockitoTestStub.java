
package com.crio.qeats.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.crio.qeats.QEatsApplication;
import com.crio.qeats.dto.Restaurant;
import com.crio.qeats.exchanges.GetRestaurantsRequest;
import com.crio.qeats.exchanges.GetRestaurantsResponse;
import com.crio.qeats.repositoryservices.RestaurantRepositoryService;
import com.crio.qeats.utils.FixtureHelpers;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.mockito.quality.Strictness;

@SpringBootTest(classes = {QEatsApplication.class}) 
// This annotation tells Spring Boot to load the complete application context for integration tests.
// It bootstraps the application context, including all the beans, configurations, and dependencies.
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
// No unnecessary stubs: Any stubbing that isn't actually invoked during the test will throw an exception, ensuring cleaner test code.
// Helps avoid unintentional mistakes by catching unused stubs early.
@DirtiesContext
// Ensures that any changes made to the application context (e.g., overriding beans, modifying properties) during a test do not affect subsequent tests.
@ActiveProfiles("test")
// Use this to ensure your tests run with a specific environment configuration, isolating them from production settings.
@ExtendWith(MockitoExtension.class)
// Allows you to use Mockito annotations such as @Mock, @InjectMocks, and @Spy without needing to manually initialize mocks.
// The @Mock and @InjectMocks annotations work without requiring manual initialization (like MockitoAnnotations.openMocks()).


public class RestaurantServiceMockitoTestStub {

  protected static final String FIXTURES = "fixtures/exchanges";

  protected ObjectMapper objectMapper = new ObjectMapper();

  protected Restaurant restaurant1;
  protected Restaurant restaurant2;
  protected Restaurant restaurant3;
  protected Restaurant restaurant4;
  protected Restaurant restaurant5;

  @InjectMocks
  protected RestaurantServiceImpl restaurantService;
  @Mock
  protected RestaurantRepositoryService restaurantRepositoryServiceMock;

  @BeforeEach
  public void initializeRestaurantObjects() throws IOException {
    String fixture =
        FixtureHelpers.fixture(FIXTURES + "/mocking_list_of_restaurants.json");
    Restaurant[] restaurants = objectMapper.readValue(fixture, Restaurant[].class);
    // TODO CRIO_TASK_MODULE_MOCKITO
    //  What to do with this Restaurant[] ? Looks unused?
    //  Look for the "assert" statements in the tests
    //  following and find out what to do with the array.
    restaurant1 = restaurants[0];
    restaurant2 = restaurants[1];
    restaurant3 = restaurants[2];
    restaurant4 = restaurants[3];
    restaurant5 = restaurants[4];
  }


 // Code Under Test: allRestaurantsCloseBy.getRestaurants()
  // Test case: Check if allRestaurantsCloseBy.getRestaurants() is working correctly,
  //        	when finding restaurants within 5 km radius.
  // Dependency Layer/Function: restaurantRepositoryService.findAllRestaurantsCloseBy()
  // Mock Setup:
  //   - Function to mock: restaurantRepositoryService.findAllRestaurantsCloseBy()
  //   - Input to mock: (some latitude, some longitude, some time, 5)
  //   - Pre-set output from mock: <xyz>
  // Test Input: restaurantService.findAllRestaurantsCloseBy(should be inline with your mock setup)
  // Expected Test Output: Specific restaurants returned by the mock object.
  @Test
  public void  testFindNearbyWithin5km() throws IOException {
    //TODO: CRIO_TASK_MODULE_MOCKITO
    // Following test case is failing, you have to
    // debug it, find out whats going wrong and fix it.
    // Notes - You can create additional mocks, setup the same and try out.

    // - this invocation of 'findAllRestaurantsCloseBy' method:
    // restaurantRepositoryServiceMock.findAllRestaurantsCloseBy(
    // 20.0d,
    // 30.0d,
    // 03:00,
    // 5.0d

    Mockito.when(restaurantRepositoryServiceMock.findAllRestaurantsCloseBy(eq(20.0), eq(30.0), eq(LocalTime.of(3, 0)), eq(5.0)))
            .thenReturn(Arrays.asList(restaurant1, restaurant2));

    // when(restaurantRepositoryServiceMock
    //         .findAllRestaurantsCloseBy(any(Double.class), any(Double.class), eq(LocalTime.of(3, 0)), eq(1.0)))
    //         .thenReturn(Arrays.asList(restaurant1, restaurant2));

    GetRestaurantsResponse allRestaurantsCloseBy = restaurantService
        .findAllRestaurantsCloseBy(new GetRestaurantsRequest(20.0, 30.0), LocalTime.of(3, 0));

    assertEquals(2, allRestaurantsCloseBy.getRestaurants().size());
    assertEquals("11", allRestaurantsCloseBy.getRestaurants().get(0).getRestaurantId());
    assertEquals("12", allRestaurantsCloseBy.getRestaurants().get(1).getRestaurantId());

    ArgumentCaptor<Double> servingRadiusInKms = ArgumentCaptor.forClass(Double.class);
    
    verify(restaurantRepositoryServiceMock, times(1))
        .findAllRestaurantsCloseBy(any(Double.class), any(Double.class), any(LocalTime.class),
            servingRadiusInKms.capture());

  }

  // Code Under Test:  
  //  allRestaurantsCloseByOffPeakHours.getRestaurants()
  //  allRestaurantsCloseByPeakHours.getRestaurants()
  // Test case: Check if allRestaurantsCloseByOffPeakHours.getRestaurants() and allRestaurantsCloseByPeakHours.getRestaurants() is working correctly,
  //        	when finding restaurants within 3 km radius.
  // Dependency Layer/Function: RestaurantRepositoryService.findAllRestaurantsCloseBy()
  // Mock Setup:
  //   - Function to mock: RestaurantRepositoryService.findAllRestaurantsCloseBy()
  //   - Input to mock: (some latitude, some longitude, some time, 3)
  //   - Pre-set output from mock: <xyz>
  // Test Input: restaurantService.findAllRestaurantsCloseBy(should be inline with your mock setup)
  // Expected Test Output: Test should run with success.
  @Test
  public void  testFindNearbyWithin3km() throws IOException {

    List<Restaurant> restaurantList1 = Arrays.asList(restaurant1, restaurant4);
    List<Restaurant> restaurantList2 = Arrays.asList(restaurant2, restaurant4);

    // TODO: CRIO_TASK_MODULE_MOCKITO
    //  Initialize these two lists above such that I will match with the assert statements
    //  defined below.


    lenient().doReturn(restaurantList1)
        .when(restaurantRepositoryServiceMock)
        .findAllRestaurantsCloseBy(eq(20.0), eq(30.2), eq(LocalTime.of(3, 0)), eq(5.0));

    lenient().doReturn(restaurantList2)
        .when(restaurantRepositoryServiceMock)
        .findAllRestaurantsCloseBy(eq(21.0), eq(31.1), eq(LocalTime.of(19, 0)), eq(3.0));

            
    GetRestaurantsResponse allRestaurantsCloseByOffPeakHours;
    GetRestaurantsResponse allRestaurantsCloseByPeakHours;

    // TODO: CRIO_TASK_MODULE_MOCKITO
    //  Call restaurantService.findAllRestaurantsCloseBy with appropriate parameters such that
    //  Both of the mocks created above are called.
    //  Our assessment will verify whether these mocks are called as per the definition.
    //  Refer to the assertions below in order to understand the requirements better.

    allRestaurantsCloseByOffPeakHours = restaurantService
        .findAllRestaurantsCloseBy(new GetRestaurantsRequest(20.0, 30.2),
            LocalTime.of(3, 0));

    allRestaurantsCloseByPeakHours = restaurantService
        .findAllRestaurantsCloseBy(new GetRestaurantsRequest(21.0, 31.1),
            LocalTime.of(19, 0));

    assertEquals(2, allRestaurantsCloseByOffPeakHours.getRestaurants().size());
    assertEquals("11", allRestaurantsCloseByOffPeakHours.getRestaurants().get(0).getRestaurantId());
    assertEquals("14", allRestaurantsCloseByOffPeakHours.getRestaurants().get(1).getRestaurantId());


    assertEquals(2, allRestaurantsCloseByPeakHours.getRestaurants().size());
    assertEquals("12", allRestaurantsCloseByPeakHours.getRestaurants().get(0).getRestaurantId());
    assertEquals("14", allRestaurantsCloseByPeakHours.getRestaurants().get(1).getRestaurantId());
  }

}

