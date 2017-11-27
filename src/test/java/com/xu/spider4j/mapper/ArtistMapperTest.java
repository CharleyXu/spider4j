package com.xu.spider4j.mapper;

import com.xu.spider4j.entity.Artist;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArtistMapperTest {
	@Autowired
	private ArtistMapper artistMapper;

	@Test
//	@Transactional
	public void insertArtist(){
		Artist artist = new Artist(123,"xu","1004");
		artistMapper.insert(artist);
		Assert.assertEquals(1,artistMapper.findAll().size());
		System.out.println("测试通过");
	}

	@Test
	public void selectAll(){
		System.out.println("size:"+artistMapper.countAllSize());
	}
}
