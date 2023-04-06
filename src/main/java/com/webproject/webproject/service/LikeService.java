package com.webproject.webproject.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.webproject.webproject.dao.LikeRepository;
import com.webproject.webproject.model.Like;
import com.webproject.webproject.model.Post;
import com.webproject.webproject.model.User;
import com.webproject.webproject.requests.LikeCreateRequest;
import com.webproject.webproject.responses.LikeResponse;

@Service
public class LikeService {

	private LikeRepository likeRepository;
	private UserService userService;
	private PostService postService;
	
	public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
		this.likeRepository = likeRepository;
		this.userService = userService;
		this.postService = postService;
	}

	public List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
		List<Like> list;
		if(userId.isPresent() && postId.isPresent()) {
		   list	= likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
		}else if(userId.isPresent()) {
		   list = likeRepository.findByUserId(userId.get());
		}else if(postId.isPresent()) {
		   list = likeRepository.findByPostId(postId.get());
		}else
		   list = likeRepository.findAll();
		return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
	}
	
	public Like getOneLikeById(Long LikeId) {
		return likeRepository.findById(LikeId).orElse(null);
	}
	
	public Like createOneLike(LikeCreateRequest likeCreateRequest) {
		User user = userService.getOneUserById(likeCreateRequest.getUserId());
		Post post = postService.getOnePostById(likeCreateRequest.getPostId());
		if(user != null && post !=null) {
			Like likeToSave = new Like();
			likeToSave.setId(likeCreateRequest.getId());
			likeToSave.setPost(post);
			likeToSave.setUser(user);
			return likeRepository.save(likeToSave);
		}else
			return null;
	}

	public void deleteOneLikeById(Long likeId) {
		likeRepository.deleteById(likeId);
		
	}
	
}
