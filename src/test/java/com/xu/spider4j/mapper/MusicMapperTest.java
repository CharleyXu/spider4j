package com.xu.spider4j.mapper;

import com.xu.spider4j.entity.Comment;
import com.xu.spider4j.entity.Music;

import org.apache.ibatis.annotations.Param;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MusicMapperTest {
	@Autowired
	private MusicMapper musicMapper;
	@Autowired
	private CommentMapper commentMapper;
	@Test
	public void selectMusic(){
		Music music = new Music();
		music.setSongId("1234");
		music.setName("测试");
		music.setAuthor("xu");
		music.setAlbum("测试专辑");
		musicMapper.insert(music);
		Assert.assertEquals(1,musicMapper.findAll().size());
	}

	@Test
	public void selectComment(){
		Comment comment = new Comment();
		comment.setCommentId("40001");
		comment.setContent("测试内容4");
		comment.setLinkedCount(4000);
		comment.setSongId("songId4");
		comment.setNickname("测试昵称4");
		comment.setTime("2017-11-17");
		Comment comment2 = new Comment();
		comment2.setCommentId("50001");
		comment2.setContent("测试内容5");
		comment2.setLinkedCount(5000);
		comment2.setSongId("songId5");
		comment2.setNickname("测试昵称5");
		comment2.setTime("2017-11-17");
		List<Comment> list = new ArrayList<>();
		list.add(comment);
		list.add(comment2);
		System.out.println("list size:"+list.size());
//		commentMapper.insert(comment);
		commentMapper.batchInsert(list);
		Assert.assertEquals(5,commentMapper.findAll().size());
		System.out.println("测试成功");
	}

	@Test
	public void updateComment(){
		String content = "测试内容更新";
		String commentId = "20001";
		commentMapper.updateOne(content,commentId);
		System.out.println("测试成功");
	}

	@Test
	public void batchUpdateComment(){
		List<Comment> comments = new ArrayList<>();
		Comment comment = new Comment();
		comment.setCommentId("20001");
		comment.setContent("2二次更新");

		Comment comment2 = new Comment();
		comment2.setCommentId("30001");
		comment2.setContent("3二次更新");
		commentMapper.batchUpdate(comments);
		System.out.println("测试成功");
	}

}
