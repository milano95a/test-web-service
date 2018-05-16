package home.security.repository;

import home.security.domain.ConfirmationCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by AB on 11-Aug-17.
 */

public interface IConfirmationCodeRepo extends JpaRepository<ConfirmationCode,String>{
    ConfirmationCode findConfirmationCodeByUserId(String userId);
}
