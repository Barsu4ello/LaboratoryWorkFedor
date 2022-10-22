package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.dto.request.CountryRequest;
import com.cvetkov.fedor.laboratoryWork.dto.request.SubscriptionRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.CountryResponse;
import com.cvetkov.fedor.laboratoryWork.dto.response.SubscriptionResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.CountryUpdate;
import com.cvetkov.fedor.laboratoryWork.dto.update.SubscriptionUpdate;
import com.cvetkov.fedor.laboratoryWork.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping
    public Page<SubscriptionResponse> getAllSubscriptions(@PageableDefault(size = 5) Pageable pageable) {
        return subscriptionService.getAllPage(pageable);
    }

    @GetMapping("/all-subscription")
    public List<SubscriptionResponse> getAllSubscriptions() {
        return subscriptionService.getAllList();
    }

    @GetMapping("/{id}")
    public SubscriptionResponse getSubscriptionById(@PathVariable Long id) {
        return subscriptionService.findById(id);
    }

    @PostMapping
    public void addSubscription(@Valid @RequestBody SubscriptionRequest subscriptionRequest) {
        subscriptionService.save(subscriptionRequest);
    }

    @PutMapping
    public void updateSubscription(@Valid @RequestBody SubscriptionUpdate subscriptionUpdate) {
        subscriptionService.update(subscriptionUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteById(id);
    }
}
