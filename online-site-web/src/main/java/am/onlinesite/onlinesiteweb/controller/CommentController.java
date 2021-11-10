package am.onlinesite.onlinesiteweb.controller;

import am.onlinesite.onlinesitecommon.model.Comment;
import am.onlinesite.onlinesitecommon.security.CurrentUser;
import am.onlinesite.onlinesitecommon.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/products{productId}/addComment")
    public String addComment(@ModelAttribute Comment comment,
                             @AuthenticationPrincipal CurrentUser currentUser) {
        comment.setUser(currentUser.getUser());
        comment.setCreatedDate(new Date());
        commentService.saveComment(comment);
        return "redirect:/products/" + comment.getProduct().getId();
    }
}




