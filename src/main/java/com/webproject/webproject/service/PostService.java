package com.webproject.webproject.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webproject.webproject.dao.PostRepository;
import com.webproject.webproject.model.Post;
import com.webproject.webproject.model.User;
import com.webproject.webproject.requests.PostCreateRequest;
import com.webproject.webproject.requests.PostUpdateRequest;
import com.webproject.webproject.responses.LikeResponse;
import com.webproject.webproject.responses.PostResponse;

@Service
public class PostService {

	private PostRepository postRepository;
	private UserService userService;
	private LikeService likeService;

	public PostService(PostRepository postRepository, UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;
	}
	
	@Autowired
	public void setLikeService(LikeService likeService) {
		this.likeService = likeService;
	}

	public List<PostResponse> getAllPosts(Optional<Long> userId) {
		List<Post> list;

		if (userId.isPresent()) {
			list = postRepository.findByUserId(userId.get());
		}
		list = postRepository.findAll();
		return list.stream().map(p -> {
			List<LikeResponse> likes = likeService.getAllLikesWithParam(Optional.ofNullable(null),
					Optional.of(p.getId()));
			return new PostResponse(p, likes);
		}).collect(Collectors.toList());
	}

	public Post getOnePostById(Long postId) {
		return postRepository.findById(postId).orElse(null);
	}

	public PostResponse getOnePostByIdWithLikes(Long postId) {
		Post post = postRepository.findById(postId).orElse(null);
		List<LikeResponse> likes = likeService.getAllLikesWithParam(Optional.ofNullable(null), Optional.of(postId));
		return new PostResponse(post, likes);
	}

	public Post createOnePost(PostCreateRequest newPostRequest) {
		User user = userService.getOneUserById(newPostRequest.getUserId());
		if (user == null) {
			return null;
		} else {
			Post toSave = new Post();
			toSave.setId(newPostRequest.getId());
			toSave.setText(newPostRequest.getText());
			toSave.setTitle(newPostRequest.getTitle());
			toSave.setUser(user);
			toSave.setCreateDate(new Date());
			return postRepository.save(toSave);
		}
		
	
	}


	public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
		Optional<Post> post = postRepository.findById(postId);
		if (post.isPresent()) {
			Post toUpdate = post.get();
			toUpdate.setText(updatePost.getText());
			toUpdate.setTitle(updatePost.getTitle());
			postRepository.save(toUpdate);
			return toUpdate;
		}
		return null;
	}

	public void deleteOnePostById(Long postId) {
		postRepository.deleteById(postId);
	}

}
