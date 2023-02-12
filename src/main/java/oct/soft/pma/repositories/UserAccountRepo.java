package oct.soft.pma.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import oct.soft.pma.entities.UserAccount;

public interface UserAccountRepo extends JpaRepository<UserAccount, Long> {

}
