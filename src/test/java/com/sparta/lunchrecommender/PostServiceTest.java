//package com.sparta.lunchrecommender;
//
//import com.sparta.lunchrecommender.domain.post.dto.PostCreateRequestDto;
//import com.sparta.lunchrecommender.domain.post.dto.PostResponseDto;
//import com.sparta.lunchrecommender.domain.post.dto.PostUpdateRequestDto;
//import com.sparta.lunchrecommender.domain.post.entity.Post;
//import com.sparta.lunchrecommender.domain.post.repository.PostRepository;
//import com.sparta.lunchrecommender.domain.post.service.PostService;
//import com.sparta.lunchrecommender.domain.user.entity.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.BDDMockito.given;
//
//class PostServiceTest {
//
//    @Mock
//    private PostRepository postRepository;
//
//    @InjectMocks
//    private PostService postService;
//
//    private User testUser;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
////        testUser = new User(1L, "testUser", "password", "ROLE_USER");
//    }
//
//    @Test
//    void testGetPosts() {
////        Post post1 = new Post("Post1", "Content1", LocalDateTime.now());
////        Post post2 = new Post("Post2", "Content2", LocalDateTime.now());
////
////        Page<Post> posts = new PageImpl<>(Arrays.asList(post1, post2));
//
//        given(postRepository.findAll()).willReturn(null);
//
//        Page<PostResponseDto> result = postService.getPosts(0, "createdAt", null, null);
//
//        assertEquals(2, result.getSize());
//        assertEquals("Post1", "Post1");
//        assertEquals("Post2", "Post2");
//    }
////
////    @Test
////    void testCreatePost() {
////        PostCreateRequestDto requestDto = new PostCreateRequestDto();
////        requestDto.setTitle("New Post");
////        requestDto.setContent("New Content");
////
////        Post post = new Post(requestDto);
////        post.setUser(testUser);
////
////        given(postRepository.save(any(Post.class))).willReturn(post);
////
////        postService.createPost(requestDto, testUser);
////
////        verify(postRepository, times(1)).save(any(Post.class));
////    }
////
////    @Test
////    void testUpdatePost() {
////        Post post = new Post("Post1", "Content1", LocalDateTime.now());
////        post.setUser(testUser);
////
////        PostUpdateRequestDto updateDto = new PostUpdateRequestDto();
////        updateDto.setTitle("Updated Title");
////        updateDto.setContent("Updated Content");
////
////        given(postRepository.findById(anyLong())).willReturn(Optional.of(post));
////
////        postService.updatePost(1L, updateDto, testUser);
////
////        assertEquals("Updated Title", post.getTitle());
////        assertEquals("Updated Content", post.getContent());
////    }
////
////    @Test
////    void testUpdatePost_Unauthorized() {
////        Post post = new Post("Post1", "Content1", LocalDateTime.now());
////        post.setUser(testUser);
////
////        User anotherUser = new User(2L, "anotherUser", "password", "ROLE_USER");
////
////        PostUpdateRequestDto updateDto = new PostUpdateRequestDto();
////        updateDto.setTitle("Updated Title");
////        updateDto.setContent("Updated Content");
////
////        given(postRepository.findById(anyLong())).willReturn(Optional.of(post));
////
////        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
////            postService.updatePost(1L, updateDto, anotherUser);
////        });
////
////        String expectedMessage = "현재 게시물 작성자만 수정이 가능합니다.";
////        String actualMessage = exception.getMessage();
////
////        assertTrue(actualMessage.contains(expectedMessage));
////    }
////
////    @Test
////    void testDeletePost() {
////        Post post = new Post("Post1", "Content1", LocalDateTime.now());
////        post.setUser(testUser);
////
////        given(postRepository.findById(anyLong())).willReturn(Optional.of(post));
////
////        postService.deletePost(1L, testUser);
////
////        verify(postRepository, times(1)).delete(post);
////    }
////
////    @Test
////    void testDeletePost_Unauthorized() {
////        Post post = new Post("Post1", "Content1", LocalDateTime.now());
////        post.setUser(testUser);
////
////        User anotherUser = new User(2L, "anotherUser", "password", "ROLE_USER");
////
////        given(postRepository.findById(anyLong())).willReturn(Optional.of(post));
////
////        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
////            postService.deletePost(1L, anotherUser);
////        });
////
////        String expectedMessage = "현재 게시물 작성자만 삭제가 가능합니다.";
////        String actualMessage = exception.getMessage();
////
////        assertTrue(actualMessage.contains(expectedMessage));
////    }
//}