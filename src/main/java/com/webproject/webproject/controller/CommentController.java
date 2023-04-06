package com.webproject.webproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webproject.webproject.model.Comment;
import com.webproject.webproject.requests.CommentCreateRequest;
import com.webproject.webproject.requests.CommentUpdateRequest;
import com.webproject.webproject.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {
	
	private CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	/*
	 /comments
	 /comments?postId={postId}
	 /comments?userId={userId}
	 /comments?postId={postId}&userId={userId}
	 /comments/{commentId}
	 */
	
	@GetMapping
	public List<Comment> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
		return commentService.getAllCommentsWithParam(userId,postId);
	}
	
	@PostMapping
	public Comment createOneComment(@RequestBody CommentCreateRequest commentCreateRequest) {
		return commentService.createOneComment(commentCreateRequest);
	}
	
	@GetMapping("/commentId")
	public Comment getOneComment(@PathVariable Long commentId) {
		return commentService.getOneCommentById(commentId);
	}
	
	@PutMapping("/{commentId}")
	public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest) {
		return commentService.updateOneCommentById(commentId, commentUpdateRequest);
	}
	
	@DeleteMapping("/{commentId}")
	public void deleteOneComment(@PathVariable Long commentId) {
		commentService.deleteOneCommentById(commentId);
	}

}
