package com.doorstep.service.Repository;

import com.doorstep.service.Entiity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, String> {
}
