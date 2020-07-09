package com.maindish.restapisample.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long create(@RequestBody PostsCreateRequestDto requestDto) {
        return postsService.create(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostsResponseDto> selectById(@PathVariable Long id) {
        PostsResponseDto responseDto = postsService.findById(id);

        return ResponseEntity.ok(responseDto);
    }
}
