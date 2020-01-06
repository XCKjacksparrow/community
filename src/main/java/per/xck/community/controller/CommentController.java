package per.xck.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import per.xck.community.dto.CommentDTO;
import per.xck.community.dto.ResultDTO;
import per.xck.community.mapper.CommentMapper;
import per.xck.community.model.Comment;
import per.xck.community.model.CommentExample;
import per.xck.community.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @RequestMapping(name = "/comment",method = RequestMethod.POST)
    @ResponseBody
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return ResultDTO.errorOf(2002,"未登录不能进行评论，请先登录");
        }

        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setContent(commentDTO.getContent());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(1);
        comment.setLikeCount(0L);
        commentMapper.insert(comment);

        Map<Object,Object> map = new HashMap<>();
        map.put("message","成功");
        return map;
    }

    @RequestMapping("/getComments/{QuestionId}")
    @ResponseBody
    public List<Comment> getComments(@PathVariable("QuestionId") Integer QuestionId){
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(Integer.toUnsignedLong(QuestionId));
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        return comments;
    }

    @RequestMapping("/comment/insert")
    @ResponseBody
    public String insertComment(@RequestParam("text")String text){
        Comment comment = new Comment();
        comment.setContent(text);
        comment.setType(1);
        comment.setLikeCount(Integer.toUnsignedLong(0));
        comment.setCommentator(1);
        comment.setParentId(1L);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        commentMapper.insert(comment);
        return "success";
    }
}
