package am.onlinesite.onlinesitecommon.repasitory;

import am.onlinesite.onlinesitecommon.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository <Comment,Integer> {
    List<Comment> findCommentByProductId(long productId);
}


