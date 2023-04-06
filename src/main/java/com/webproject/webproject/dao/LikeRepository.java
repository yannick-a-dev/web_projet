package com.webproject.webproject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproject.webproject.model.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

	List<Like> findByUserIdAndPostId(Long userId, Long postId);

	List<Like> findByUserId(Long userId);

	List<Like> findByPostId(Long postId);

}
