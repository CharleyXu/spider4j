package com.xu.spider4j.mapper;

import com.xu.spider4j.entity.Music;
import com.xu.spider4j.entity.Page;
import com.xu.spider4j.entity.PageRequest;
import com.xu.spider4j.entity.Sort;
import java.util.List;
import java.util.Random;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MusicMapperTest {
	@Autowired
	private MusicMapper musicMapper;
	@Autowired
	private CommentMapper commentMapper;

	@Test
	@Transactional
	public void insertMusic(){
		//int songId, String name, String artistsId, int score, String album, String commentThreadId
//		Music music = new Music();
//		music.setId(3023);
//		music.setName("测试名称");
//		music.setAlbum("测试专辑");
//		music.setCommentThreadId("R_SO_4_35599542");
//		music.setScore(100);
//		musicMapper.insert(music);
//		Assert.assertEquals(1,musicMapper.findAll().size());
//		System.out.println("测试成功");
	}
//	@Test
//	@Transactional
//	@Rollback(true)// 事务自动回滚，默认是true。可以不写
	/**
	 * Comment 暂时不用
	 */
//	public void batchInsert(){
//		Comment comment = new Comment();
//		comment.setId(70001);
//		comment.setContent("测试内容7");
//		comment.setLinkedCount(7000);
//		comment.setSongId(1);
//		comment.setNickname("测试昵称7");
//		comment.setTime(System.currentTimeMillis());
//		Comment comment2 = new Comment();
//		comment2.setId(80001);
//		comment2.setContent("测试内容8");
//		comment2.setLinkedCount(8000);
//		comment2.setSongId(8);
//		comment2.setNickname("测试昵称8");
//		comment2.setTime(System.currentTimeMillis());
//		List<Comment> list = new ArrayList<>();
//		list.add(comment);
//		list.add(comment2);
//		System.out.println("list size:"+list.size());
////		commentMapper.insert(comment);
//		commentMapper.batchInsert(list);
//		Assert.assertEquals(7,commentMapper.findAll().size());
//		System.out.println("测试成功");
//	}


	//分页测试
	@Test
	public void findByPage(){
		PageRequest request = new PageRequest();
		int pageSize = 20 ;//
		request.setStart(0);
		request.setSize(pageSize);
		request.setSorts(new Sort[]{new Sort("musicId","asc")});
		List<Music> list = musicMapper.findByPage(request);
		Page page = new Page();
		page.setRows(list);
		int sum = musicMapper.countSize();//总记录数
		int pageNumbers = sum/pageSize+1;//总页数
		page.setTotalPages(pageNumbers);
		page.setTotalRows(sum);
		System.out.println("page:\n"+page);
	}

	@Test
	public void findMusicByCondition(){
		String artist = "Eason";
		String name = "预感";
		Music musicByCondition = musicMapper.findMusicByCondition(artist, name);
		System.out.println(musicByCondition);

		List<String> musicBySongName = musicMapper.findMusicBySongName(name);
		Random random = new Random();
		String songName = musicBySongName.get(random.nextInt(musicBySongName.size()));
		System.out.println("songName:"+songName);
		List<Music> listByArtist = musicMapper.findListByArtist(artist);
		System.out.println("listByArtist:"+listByArtist);
	}

}
