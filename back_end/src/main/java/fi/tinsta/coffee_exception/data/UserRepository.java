package fi.tinsta.coffee_exception.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


@RepositoryRestResource(collectionResourceRel = "author", path = "authors")
public interface UserRepository extends JpaRepository<User, Long> {
}
