package com.godMorning_backend.controller;

import com.godMorning_backend.domain.Heart;
import com.godMorning_backend.domain.Routine;
import com.godMorning_backend.dto.HeartRank;
import com.godMorning_backend.repository.JDBCHeartRepository;
import com.godMorning_backend.service.HeartServiceImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
public class HeartController {

    private HeartServiceImpl heartServiceImpl;
    private final JdbcTemplate jdbcTemplate;
    private JDBCHeartRepository jdbcHeartRepository;
    private Heart heart;

    public HeartController(JdbcTemplate jdbcTemplate, HeartServiceImpl heartServiceImpl, Heart heart, JDBCHeartRepository jdbcHeartRepository, List<Heart> HeartSet, Heart heart1) {
        this.jdbcTemplate = jdbcTemplate;
        this.heartServiceImpl = heartServiceImpl;
        this.jdbcHeartRepository = jdbcHeartRepository;
        this.heart = heart;
    }

    @ResponseBody

    //좋아요 진행
    @PostMapping(value = "heart/insert", produces = "application/json; charset=UTF-8")
    public Heart insert(@RequestBody Heart ht) {
        Long id = 0L;
        Long post_no = 0L;

        id = ht.getId();
        post_no = ht.getPost_no();

        ht.setId(id);
        ht.setPost_no(post_no);

        heartServiceImpl.insertHeart(ht);

        return ht;
    }

    //좋아요 삭제
    @RequestMapping(value = "heart/delete")
    public String deleteHeart(HttpServletRequest request, Model model) {
        Long id = Long.parseLong(request.getParameter("id"));
        Long post_no = Long.parseLong(request.getParameter("post_no"));
        model.addAttribute("id", id);
        model.addAttribute("post_no", post_no);
        return heartServiceImpl.deleteHeart(id, post_no);

    }

    //좋아요 순위 게시판
    @RequestMapping(value = "heart/rank")
    public List<Routine> heartRank() {
        return heartServiceImpl.heartRank();
    }

    //좋아요 순위 게시판 상세보기
    @RequestMapping(value = "heart/rank/{post_no}")
    public Routine heartRankDetail(@PathVariable("post_no")Long post_no){
        return heartServiceImpl.heartRankDetail(post_no);
    }

}
