// package com.cdac.controller;

// import com.cdac.dto.ApiResponse;
// import com.cdac.dto.EmployeeInDto;
// import com.cdac.dto.EmployeeOutDto;
// import com.cdac.entities.Employee;
// import com.cdac.entities.Project;
// import com.cdac.service.EmployeeServiceImpl;
// import jakarta.validation.Valid;
// import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Positive;
// import java.util.List;
// import lombok.RequiredArgsConstructor;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/employees")
// @Validated
// @RequiredArgsConstructor

// public class EmployeeController {
//   private final EmployeeServiceImpl empService;

//   @GetMapping
//   public ResponseEntity<ApiResponse<List<EmployeeOutDto>>> getAllEmployees() {
//     return ResponseEntity.ok(
//       new ApiResponse<List<EmployeeOutDto>>(true, null, empService.findAllEmployees())
//     );
//   }

//   @PostMapping(
//     consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
//     produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
//   )
//   public ResponseEntity<ApiResponse<EmployeeOutDto>> createEmployee(
//     @Valid @RequestBody EmployeeInDto emp
//   ) {
//     return ResponseEntity
//       .status(HttpStatus.CREATED)
//       .body(
//         new ApiResponse<EmployeeOutDto>(
//           true,
//           "Employee Created Succesfully!",
//           empService.saveEmployeeByAdmin(emp)
//         )
//       );
//   }

//   @PutMapping("/{empId}")
//   @PreAuthorize("hasRole('USER')")
//   public ResponseEntity<ApiResponse<Employee>> updateEmployee(
//     @Valid @RequestBody EmployeeInDto emp,
//     @PathVariable @NotNull Long empId
//   ) {
//     return ResponseEntity
//       .status(HttpStatus.CREATED)
//       .body(
//         new ApiResponse<Employee>(
//           true,
//           "Employee updated successfully!",
//           empService.updateEmployee(emp, empId)
//         )
//       );
//   }

//   @PutMapping("/projects/{employeeId}/{projectId}")
//     @PreAuthorize("hasRole('USER')")
//   public ResponseEntity<ApiResponse<String>> assignProjectsToEmployee(
//     @PathVariable @NotNull Long employeeId,
//     @PathVariable @NotNull Long projectId
//   ) {
//     return ResponseEntity
//       .status(HttpStatus.OK)
//       .body(
//         new ApiResponse<String>(
//           true,
//           null,
//           empService.saveProjectInEmployee(employeeId, projectId)
//         )
//       );
//   }

//   @GetMapping("/projects/{userId}")
//     @PreAuthorize("hasRole('USER')")
//   public ResponseEntity<ApiResponse<List<Project>>> getProjects(
//     @PathVariable @NotNull Long userId
//   ) {
//     return ResponseEntity
//       .status(HttpStatus.OK)
//       .body(
//         new ApiResponse<List<Project>>(
//           true,
//           null,
//           empService.findProjectsByEmployeeId(userId)
//         )
//       );
//   }

//   @GetMapping("/{id}")
//   public ResponseEntity<ApiResponse<Employee>> findEmployeeById(
//     @PathVariable @Positive Long id
//   ) {
//     return ResponseEntity
//       .status(HttpStatus.OK)
//       .body(new ApiResponse<Employee>(true, null, empService.findEmployeeById(id)));
//   }

//   @DeleteMapping("/{id}")
//   public ResponseEntity<ApiResponse<String>> deleteEmployeeById(
//     @PathVariable @Positive Long id
//   ) {
//     return ResponseEntity
//       .status(HttpStatus.OK)
//       .body(new ApiResponse<String>(true, null, empService.deleteEmployeeById(id)));
//   }

//   @GetMapping("/count")
//   public ResponseEntity<ApiResponse<String>> countEmployee() {
//     return ResponseEntity
//       .status(HttpStatus.OK)
//       .body(new ApiResponse<String>(true, null, empService.countEmployees()));
//   }
// }
