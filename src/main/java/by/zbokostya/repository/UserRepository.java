//package by.zbokostya.repository;
//
//import by.zbokostya.entity.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Repository
//public interface UserRepository extends JpaRepository<User, UUID> {
//    Optional<User> findByLogin(String login);
//    Optional<User> findOneWithAuthoritiesByLogin(String login);
//}
