package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.dto.request.UserSubscriptionRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.UserSubscriptionResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.UserSubscriptionUpdate;
import com.cvetkov.fedor.laboratoryWork.service.UserSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/user-subscription")
public class UserSubscriptionController {

    private final UserSubscriptionService userSubscriptionService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Page<UserSubscriptionResponse> getAllUserSubscriptions(@PageableDefault(size = 5) Pageable pageable) {
        return userSubscriptionService.getAllPage(pageable);
    }

    @GetMapping("/all-user-subscription")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<UserSubscriptionResponse> getAllUserSubscriptions() {
        return userSubscriptionService.getAllList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public UserSubscriptionResponse getUserSubscriptionById(@PathVariable Long id) {
        return userSubscriptionService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public void addUserSubscription(@Valid @RequestBody UserSubscriptionRequest userSubscriptionRequest) {
        userSubscriptionService.save(userSubscriptionRequest);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public void updateUserSubscription(@Valid @RequestBody UserSubscriptionUpdate userSubscriptionUpdate) {
        userSubscriptionService.update(userSubscriptionUpdate);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public void deleteUserSubscription(@PathVariable Long id) {
        userSubscriptionService.deleteById(id);
    }
}
