package com.example.PersonalProject;

import com.example.PersonalProject.Board.CommentEntity;
import com.example.PersonalProject.Board.CommentRepository;
import com.example.PersonalProject.User.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AutoConfigureMockMvc
@SpringBootTest
class PersonalProjectApplicationTests {
	private MockMvc mockMvc;
	private final UserRepository userRepository;
	private final CommentRepository commentRepository;

	@Autowired
	PersonalProjectApplicationTests(UserRepository userRepository, CommentRepository commentRepository, MockMvc mockMvc) {
		this.userRepository = userRepository;
		this.commentRepository = commentRepository;
		this.mockMvc = mockMvc;
	}

	@Test
	void contextLoads() {
	}

	@Test
	void findCommentTest() {
		Long board_id = 2L;
		List<CommentEntity> commentEntity = commentRepository.findByBoardEntity_Id(board_id);

		for (CommentEntity comment : commentEntity) {
			System.out.println(comment.getComment());
			System.out.println(comment.getWriter());
			System.out.println(comment.getCreatedDate());
		}
	}

	@Test
	void updateCommentTest() {
		Map<String, Object> map = new HashMap<>();
		Long comment_id = 5L;
		map.put("comment_id",comment_id);
		map.put("comment", "너무나 재밌는 댓글");

		Optional<CommentEntity> commentEntity = commentRepository.findById(comment_id);
		commentEntity.get().setComment(String.valueOf(map.get("comment")));
		commentRepository.save(commentEntity.get());
	}
}