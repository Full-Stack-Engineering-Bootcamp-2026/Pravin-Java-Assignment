// package com.cdac.service;

// import com.cdac.dto.AuthResponse;
// import com.cdac.dto.ChangePasswordDTo;
// import com.cdac.dto.EmployeeInDto;
// import com.cdac.dto.EmployeeOutDto;
// import com.cdac.dto.LoginInDto;
// import com.cdac.entities.Department;
// import com.cdac.entities.Employee;
// import com.cdac.entities.Project;
// import com.cdac.entities.Role;
// import com.cdac.enums.EmployeeStatus;
// import com.cdac.enums.Status;
// import com.cdac.exception.ResourceNotFound;
// import com.cdac.repository.DepartmentRepository;
// import com.cdac.repository.EmployeeRepository;
// import com.cdac.repository.ProjectRepository;
// import com.cdac.repository.RoleRepository;


// import java.util.List;
// import java.util.Optional;
// import lombok.RequiredArgsConstructor;
// import org.modelmapper.ModelMapper;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;



// @Service
// @Transactional
// @RequiredArgsConstructor
// public class EmployeeServiceImpl implements EmployeeSevice {
//   private final EmployeeRepository employeeRepository;
//   private final DepartmentRepository departmentRepository;
//   private final ModelMapper mapper;
//   private final ProjectRepository projectRepository;
//   private final RoleRepository roleRepository;
//   private final AuthenticationManager  authenticationManager;
//   private final PasswordEncoder encoder;
//   // private final JwtService jwtService;



// public List<EmployeeOutDto> findAllEmployees() {
//   List<Employee> emps = employeeRepository.findAll();

//     return emps
//       .stream()
//       .map(
//         e -> {
//           return mapper.map(e, EmployeeOutDto.class);
//         }
//       )
//       .toList();
//   }

//   public Employee findEmployeeById(Long id) {
//     Employee emp = null;

//     emp =
//       employeeRepository
//         .findById(id)
//         .orElseThrow(() -> new ResourceNotFound("User doesn't exist!"));

//     return emp;
//   }

//   public EmployeeOutDto saveEmployeeByAdmin(EmployeeInDto emp) {

//       if (employeeRepository.existsByEmail(emp.getEmail())) {
//             throw new RuntimeException("Email already registered");
//         }
//     Optional<Department> dept = departmentRepository.findById(emp.getDepartment_id());
//     Optional<Role> role = roleRepository.findById(emp.getRole());
//     if (dept.isEmpty() || role.isEmpty()) {
//       throw new ResourceNotFound("dept or role  not exist");
//     }

//     Employee empNew = new Employee();
//     empNew.setName(emp.getName());
//     empNew.setDepartment(dept.get());
//     empNew.setEmail(emp.getEmail());
//     empNew.setPassword(encoder.encode(emp.getPassword()));
//     empNew.setStatus( EmployeeStatus.valueOf(emp.getStatus().toString()) );
//     empNew.setRole(role.get());
//     empNew.setSalary(emp.getSalary());
  

//     Employee res = employeeRepository.save(empNew);
    
//     System.out.println(res.toString());

//     return mapper.map(res, EmployeeOutDto.class);
//   }

//   public String saveProjectInEmployee(Long userId, Long projecId) {
//     Employee emp = employeeRepository
//       .findById(userId)
//       .orElseThrow(() -> new ResourceNotFound("user doesn't exist!"));
//     Project project = projectRepository
//       .findById(projecId)
//       .orElseThrow(() -> new ResourceNotFound("project doesn't exist"));

//     emp.getProject().add(project);
//     employeeRepository.save(emp);

//     return "Project Saved Successfully!";
//   }

//   public List<Project> findProjectsByEmployeeId(Long id) {
//     Employee emp = employeeRepository
//       .findById(id)
//       .orElseThrow(() -> new ResourceNotFound("user doesn't exist!"));

//     return emp.getProject();
//   }

//   public String deleteEmployeeById(Long empId) {
//     Employee empNew = employeeRepository
//       .findById(empId)
//       .orElseThrow(() -> new ResourceNotFound("user doesn't exist!"));

//     employeeRepository.delete(empNew);
//     return "User Deleted Successfully!";
//   }

//   public String countEmployees() {
//     return "Total count is : " + employeeRepository.count();
//   }

//   public Employee updateEmployee(EmployeeInDto emp, Long empId) {
//     Employee empNew = employeeRepository
//       .findById(empId)
//       .orElseThrow(() -> new ResourceNotFound("user doesn't exist!"));
//     Optional<Department> dept = departmentRepository.findById(emp.getDepartment_id());
//     Optional<Role> role = roleRepository.findById(emp.getRole());
//     if (dept.isEmpty() || role.isEmpty()) {
//       throw new ResourceNotFound("dept or role  not exist");
//     }

//     empNew.setName(emp.getName());
//     empNew.setDepartment(dept.get());
//     empNew.setEmail(emp.getEmail());
//     empNew.setStatus(emp.getStatus());
//     empNew.setRole(role.get());
//     empNew.setSalary(emp.getSalary());

//     return employeeRepository.save(empNew);
//   }
  
  


//   // @Override
//   // public AuthResponse changePassword(ChangePasswordDTo dto) {
         
   

//   //     Employee emp = employeeRepository.findByEmail(dto.getEmail()).orElseThrow(()-> new ResourceNotFound("Not found "));
    
       
//   //       emp.setPassword(encoder.encode(dto.getNewPassword())); 
       

//   //      Employee user =    employeeRepository.save(emp);

     
//   //       String token = jwtService.generateToken(user);

//   //       return new AuthResponse(token, user.getEmail(), user.getRole().getTitle());
//   // }

//   // @Override
//   // public AuthResponse loginEmployee(LoginInDto dto) {
//   //   authenticationManager.authenticate(
//   //           new UsernamePasswordAuthenticationToken(
//   //               dto.getEmail(),
//   //               dto.getPassword()
//   //           )
//   //       );

      
//   //       Employee user = employeeRepository.findByEmail(dto.getEmail())
//   //           .orElseThrow(() -> new RuntimeException("User not found"));

//   //       String token = jwtService.generateToken(user);


//   //         return new AuthResponse(token,user.getEmail(),user.getRole().getTitle());

//   // }

// }
