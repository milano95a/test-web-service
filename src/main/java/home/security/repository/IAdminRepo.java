package home.security.repository;

import home.security.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by AB on 06-Sep-17.
 */
public interface IAdminRepo extends JpaRepository<Admin,Integer>{
    Admin findAdminByUsername(String username);
}
