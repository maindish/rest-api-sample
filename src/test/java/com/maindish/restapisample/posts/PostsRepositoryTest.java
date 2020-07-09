package com.maindish.restapisample.posts;

import org.apache.tomcat.jni.Local;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanUp() {
        this.postsRepository.deleteAll();
    }

    @Test
    public void posts_repository_findAll_success() {
        // given
        final String title = "test post title";
        final String content = "test post content";

        postsRepository.save(
                Posts.builder()
                .title(title)
                .content(content)
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void posts_repository_save_with_basetime_success() {
        // given
        LocalDateTime now = LocalDateTime.now();

        postsRepository.save(
                Posts.builder()
                .title("test posts title")
                .content("test posts content")
                .author("test posts author")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>> createdDate=" + posts.getCreatedDate() + ", modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
