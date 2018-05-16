package home.security.repository;

import home.security.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by AB on 10-Aug-17.
 */

public interface ITokenRepo extends JpaRepository<Token,String> {

    Token findTokenByUserId(String userId);

    void deleteTokenByUserId(String userId);
}
