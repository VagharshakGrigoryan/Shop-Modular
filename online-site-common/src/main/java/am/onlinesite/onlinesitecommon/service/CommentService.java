package am.onlinesite.onlinesitecommon.service;



import am.onlinesite.onlinesitecommon.model.Comment;
import am.onlinesite.onlinesitecommon.repasitory.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<Comment> getAllCommentsByPostId(long id) {
        return commentRepository.findCommentByProductId(id);
    }

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }
}