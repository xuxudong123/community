package com.xudong.community.mapper;

import com.xudong.community.model.Comment;
import com.xudong.community.model.CommentExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface CommentExtMapper {
    int inCommentCount(Comment comment);
}