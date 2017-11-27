package com.xu.spider4j.mapper;

import com.xu.spider4j.entity.Comment;
import com.xu.spider4j.entity.Music;
import com.xu.spider4j.entity.Page;
import com.xu.spider4j.entity.PageRequest;
import com.xu.spider4j.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MusicMapperTest {
	@Autowired
	private MusicMapper musicMapper;
	@Autowired
	private CommentMapper commentMapper;
	@Test
//	@Transactional
	public void insertMusic(){
		//int songId, String name, String artistsId, int score, String album, String commentThreadId
		Music music = new Music();
		music.setId(3023);
		music.setName("测试名称");
		music.setAlbum("测试专辑");
		music.setCommentThreadId("R_SO_4_35599542");
		music.setScore(100);
		musicMapper.insert(music);
		Assert.assertEquals(1,musicMapper.findAll().size());
		System.out.println("测试成功");
	}

	@Test
	@Transactional
	@Rollback(true)// 事务自动回滚，默认是true。可以不写
	/**
	 * junit单元测试时,插入成功后会回滚
	 * 不过不影响数据库主键自增
	 * Rolled back transaction after test execution for test context......
	 */
	public void batchInsert(){
		Comment comment = new Comment();
		comment.setId(70001);
		comment.setContent("测试内容7");
		comment.setLinkedCount(7000);
		comment.setSongId(1);
		comment.setNickname("测试昵称7");
		comment.setTime(System.currentTimeMillis());
		Comment comment2 = new Comment();
		comment2.setId(80001);
		comment2.setContent("测试内容8");
		comment2.setLinkedCount(8000);
		comment2.setSongId(8);
		comment2.setNickname("测试昵称8");
		comment2.setTime(System.currentTimeMillis());
		List<Comment> list = new ArrayList<>();
		list.add(comment);
		list.add(comment2);
		System.out.println("list size:"+list.size());
//		commentMapper.insert(comment);
		commentMapper.batchInsert(list);
		Assert.assertEquals(7,commentMapper.findAll().size());
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
		comment.setId(20001);
		comment.setContent("2二次更新");

		Comment comment2 = new Comment();
		comment2.setId(30001);
		comment2.setContent("3二次更新");

		comments.add(comment);
		comments.add(comment2);
		commentMapper.batchUpdate(comments);
		System.out.println("测试成功");
	}

	@Test
	@Transactional
	public void batchSelect(){
		String[] strings = {"70001", "80001"};
		List<String> list = Arrays.asList(strings);
		String[] strings1 = list.toArray(new String[strings.length]);
		System.out.println("strings1:"+Arrays.toString(strings1));
		List<Comment> comments = commentMapper.batchSelect(list);
		System.out.println("comments:"+comments);
		commentMapper.batchDelete(list);
		Assert.assertEquals(5,commentMapper.findAll().size());
	}

	//分页测试
	@Test
	public void findByPage(){
		PageRequest request = new PageRequest();
		int pageSize = 20 ;//
		request.setStart(0);
		request.setSize(pageSize);
		request.setSorts(new PageRequest.Sort[]{new PageRequest.Sort("musicId","asc")});
		List<Music> list = musicMapper.findByPage(request);
		Page page = new Page();
		page.setRows(list);
		int sum = musicMapper.countSize();//总记录数
		int pageNumbers = sum/pageSize+1;//总页数
		page.setTotalPages(pageNumbers);
		page.setTotalRows(sum);
		

		System.out.println("page:\n"+page);
	}

}
