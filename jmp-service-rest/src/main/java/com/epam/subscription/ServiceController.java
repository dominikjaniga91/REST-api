package com.epam.subscription;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Dominik_Janiga
 */
@RestController
@RequestMapping("/subscription")
@Api(value = "Subscription controller")
class ServiceController {

    private final SubscriptionService subscriptionService;

    ServiceController(@Qualifier("subscriptionServiceImpl") SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @ApiOperation(response = SubscriptionResponseDto.class, produces = "application/json", value="Creates a instance of a Subscription")
    @ApiResponse(code = 201, message = "Subscription created", response = SubscriptionResponseDto.class)
    @PostMapping
    SubscriptionResponseDto createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        return this.subscriptionService.createSubscription(subscriptionRequestDto);
    }

    @ApiOperation(response = SubscriptionResponseDto.class, produces = "application/json", value="Update a instance of a Subscription")
    @ApiResponse(code = 200, message = "Subscription updated", response = SubscriptionResponseDto.class)
    @PutMapping
    SubscriptionResponseDto updateSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        return this.subscriptionService.updateSubscription(subscriptionRequestDto);
    }

    @ApiOperation(value="Update a instance of a Subscription")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Subscription deleted"),
            @ApiResponse(code = 404, message = "Not found - The subscription was not found")
    })
    @DeleteMapping("/{id}")
    void deleteSubscription(@PathVariable(name = "id") Long id) {
        this.subscriptionService.deleteSubscription(id);
    }

    @ApiOperation(value="Get a instance of a Subscription")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Found a Subscription"),
            @ApiResponse(code = 404, message = "Not found - The subscription was not found")
    })
    @GetMapping("/{id}")
    SubscriptionResponseDto getSubscription(@PathVariable(name = "id") Long id) {
        return this.subscriptionService.getSubscription(id);
    }

    @ApiOperation(value="Get list of subscriptions")
    @ApiResponse(code = 200, message = "Got list of users")
    @GetMapping
    List<SubscriptionResponseDto> getAllSubscription() {
        return this.subscriptionService.getAllSubscription();
    }
}
