package fi.tinsta.coffee_exception.data;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Seed implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BlogPostRepository blogPostRepository;

    //Seed the data
    @Override
    public void run(String... args) throws Exception {
        Lorem lorem = new LoremIpsum();

        //makes 10 users
        for (int i = 0; i < 10; i++) {
            User user = new User(lorem.getName());
            user.setImageUrl("https://randomuser.me/api/portraits/men/" + i + ".jpg");
            userRepository.save(user);

            //Make 1-4 blogposts
            for (int j = 0; j < (int) (Math.random() * 4 + 1); j++) {
                ArrayList<String> items = new ArrayList<>();

                //Make 1-5 paragraphs
                for (int c = 0; c < (int) (Math.random() * 4 + 1); c++) {
                    items.add(lorem.getHtmlParagraphs(1, 1));
                }

                BlogPost blogPost = new BlogPost(lorem.getTitle(1), user, "https://picsum.photos/800/500/?random",
                        items);

                blogPostRepository.save(blogPost);
            }
        }

        List<BlogPost> posts = blogPostRepository.findAll();
        List<User> users = userRepository.findAll();

        for (BlogPost blogPost : posts) {
            Comment[] comments = { new Comment(users.get(0), "This was a great post"),
                    new Comment(users.get(1), "I agree. So much useful information"),
                    new Comment(users.get(2), "Nah. It would've been better to use Go for this") };

            blogPost.addComments(comments);
            blogPostRepository.save(blogPost);
        }
    }
}
