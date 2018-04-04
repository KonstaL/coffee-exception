package fi.tinsta.coffee_exception.data;

import org.springframework.data.repository.CrudRepository;

public interface BlogPostRepository extends CrudRepository<BlogPost, Integer> {
}
