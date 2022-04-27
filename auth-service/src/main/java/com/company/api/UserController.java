package com.company.api;

import com.company.common.dto.UserResponseDto;
import com.company.common.dto.UserUpdateDto;
import com.company.service.UserService;
import com.company.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.UserCtrl.CTRL)
@CrossOrigin
public class UserController {

    private final UserService userService;

    @GetMapping("/{uuid}")
    public ResponseEntity<UserResponseDto> getByUuid(@PathVariable("uuid") String uuid) {
        return ResponseEntity.ok(userService.findByUserUuid(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<HttpStatus> update(@PathVariable(name = "uuid") String uuid,
                                              @RequestBody UserUpdateDto updateDto) {
        userService.update(uuid,updateDto);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("uuid") String uuid) {
        userService.delete(uuid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
