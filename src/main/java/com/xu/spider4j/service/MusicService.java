package com.xu.spider4j.service;

import com.xu.spider4j.entity.Comment;
import com.xu.spider4j.entity.Music;
import com.xu.spider4j.mapper.CommentMapper;
import com.xu.spider4j.mapper.MusicMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService {
	@Autowired
	private MusicMapper musicMapper;
	@Autowired
	private CommentMapper commentMapper;

	public void addMusic(Music music) {
		//判断数据是否存在
		if (musicMapper.countBySongId(music.getSongId()) == 0) {
			musicMapper.insert(music);
		}

	}

	public void addComment(Comment comment) {
		//判断数据是否存在
		if (commentMapper.countByCommentId(comment.getCommentId()) == 0) {
			commentMapper.insert(comment);
		}
	}


	public void addComments(List<Comment> comments) {
		commentMapper.batchInsert(comments);
	}
}
