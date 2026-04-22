


// package com.cdac.controller;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.cdac.dto.ApiResponse;
// import com.cdac.dto.AuthResponse;
// import com.cdac.dto.ChangePasswordDTo;
// import com.cdac.dto.EmployeeInDto;
// import com.cdac.dto.LoginInDto;
// import com.cdac.service.EmployeeSevice;

// import jakarta.validation.Valid;
// import lombok.RequiredArgsConstructor;

// @RestController
// @RequestMapping("/auth")
// @RequiredArgsConstructor
// public class AuthController {
       
//     private final EmployeeSevice employeeSevice;

//     @PostMapping("/password")
//     public ResponseEntity<ApiResponse<AuthResponse>> createPassword( @RequestBody @Valid ChangePasswordDTo dto ){
//        System.out.println(dto.toString());
//         return  ResponseEntity.status( HttpStatus.CREATED).body( new ApiResponse<AuthResponse>(true, null, employeeSevice.changePassword(dto)));
//     }

//       @PostMapping("/signin")
//     public ResponseEntity<ApiResponse<AuthResponse>> loginUser( @RequestBody @Valid LoginInDto dto){

// return  ResponseEntity.status( HttpStatus.OK).body( new ApiResponse<AuthResponse>(true, null, employeeSevice.loginEmployee(dto)));
//     }
    
  
// }