package com.example.crud.service;

import com.example.crud.model.Department;
import com.example.crud.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

// Annotation
@Service
// Class implementing DepartmentService class
public class DepartmentServiceImpl
        implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    // Save operation
    @Override
    public Department saveDepartment(Department department)
    {
        return departmentRepository.save(department);
    }

    // Read operation
    @Override public List<Department> fetchDepartmentList()
    {
        return (List<Department>)
                departmentRepository.findAll();
    }

    // Update operation
    @Override
    public Department updateDepartment(Department department, Long departmentId) {

        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);

        if (optionalDepartment.isPresent()) {
            Department depDB = optionalDepartment.get();

            if (Objects.nonNull(department.getDepartmentName())
                    && !"".equalsIgnoreCase(department.getDepartmentName())) {
                depDB.setDepartmentName(department.getDepartmentName());
            }

            if (Objects.nonNull(department.getDepartmentAddress())
                    && !"".equalsIgnoreCase(department.getDepartmentAddress())) {
                depDB.setDepartmentAddress(department.getDepartmentAddress());
            }

            if (Objects.nonNull(department.getDepartmentCode())
                    && !"".equalsIgnoreCase(department.getDepartmentCode())) {
                depDB.setDepartmentCode(department.getDepartmentCode());
            }

            return departmentRepository.save(depDB);
        }

        // Handle the case when the department with the given ID is not found
        throw new NoSuchElementException("Department not found for ID: " + departmentId);
    }


    // Delete operation
    @Override
    public void deleteDepartmentById(Long departmentId)
    {
        departmentRepository.deleteById(departmentId);
    }
}