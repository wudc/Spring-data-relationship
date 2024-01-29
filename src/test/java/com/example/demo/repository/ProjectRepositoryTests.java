package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.demo.model.Manager;
import com.example.demo.model.Project;
import com.example.demo.model.Staff;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjectRepositoryTests {

	@Autowired
	private ProjectRepository projectsRepository;
	
	@Autowired
	private ManagerRepository managersRepository;
	
	@Autowired
	private StaffRepository staffsRepository;
	
	@Test
	@Order(1)
	@Rollback(value = false)
	public void saveProjectTest() {
		List<Staff> staffs = new ArrayList<>();
		Staff staff = new Staff();
		staff.setName("David Lee");
		staff.setJob("Engineer");
		staffs.add ( staff );
		
		Manager manager = new Manager();
		manager.setName("Betty");
		manager.setResponsibility("Planning");
		
		Project proj = Project.builder().name("Demo").manager(manager).staffList(staffs).build();
		try {
		
			projectsRepository.save(proj);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		Assertions.assertThat(proj.getId()).isGreaterThan(0);
		Assertions.assertThat(proj.getName().equalsIgnoreCase("Demo"));
		Assertions.assertThat(proj.getManager().getName().equalsIgnoreCase("Betty"));
		Staff sf = proj.getStaffList().get(0);
		Assertions.assertThat(sf.getJob().equalsIgnoreCase("Engineer"));
	}
	
	@Test
	@Order(2)
	public void getManagerTest() {
		Manager manager = managersRepository.findById(1l).get();
		Assertions.assertThat(manager.getId()).isEqualTo(1l);
		Assertions.assertThat(manager.getName().equalsIgnoreCase("Betty"));
	}
	
	@Test
	@Order(3)
	public void getStaffTest() {
		//Staff staff = staffsRepository.findById(1l).get();
		Optional<Staff> staff = Optional.of(staffsRepository.findById(1l).get());
		Assertions.assertThat(staff.isPresent() == true);
		Assertions.assertThat(staff.get().getId()).isEqualTo(1l);
		Assertions.assertThat(staff.get().getName().equalsIgnoreCase("David Lee"));
	}
}
