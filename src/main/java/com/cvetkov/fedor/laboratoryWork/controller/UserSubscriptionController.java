package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.dto.request.UserSubscriptionRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.UserSubscriptionResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.UserSubscriptionUpdate;
import com.cvetkov.fedor.laboratoryWork.service.UserSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/user-subscription")
public class UserSubscriptionController {

    private final UserSubscriptionService userSubscriptionService;

    @GetMapping
    public Page<UserSubscriptionResponse> getAllUserSubscriptions(@PageableDefault(size = 5) Pageable pageable) {
        return userSubscriptionService.getAllPage(pageable);
    }

    @GetMapping("/all-user-subscription")
    public List<UserSubscriptionResponse> getAllUserSubscriptions() {
        return userSubscriptionService.getAllList();
    }

    @GetMapping("/{id}")
    public UserSubscriptionResponse getUserPlaylistById(@PathVariable Long id) {
        return userSubscriptionService.findById(id);
    }

    @PostMapping
    public void addUserSubscription(@Valid @RequestBody UserSubscriptionRequest userSubscriptionRequest) {
        userSubscriptionService.save(userSubscriptionRequest);
    }

    @PutMapping
    public void updateUserSubscription(@Valid @RequestBody UserSubscriptionUpdate userSubscriptionUpdate) {
        userSubscriptionService.update(userSubscriptionUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteUserSubscription(@PathVariable Long id) {
        userSubscriptionService.deleteById(id);
    }
}
