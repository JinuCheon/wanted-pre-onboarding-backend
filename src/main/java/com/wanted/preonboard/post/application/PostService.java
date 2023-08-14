package com.wanted.preonboard.post.application;

import com.wanted.preonboard.member.domain.Member;
import com.wanted.preonboard.member.domain.MemberRepository;
import com.wanted.preonboard.post.domain.Post;
import com.wanted.preonboard.post.domain.PostRepository;
import com.wanted.preonboard.post.dto.request.CreatePostRequest;
import com.wanted.preonboard.post.dto.response.PostContentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public void createPost(final Long memberId, final CreatePostRequest request) {
        Member member = memberRepository.getReferenceById(memberId);
        final Post post = new Post(
                member,
                request.title(),
                request.content()
        );
        postRepository.save(post);
    }

    public List<PostContentResponse> getFeedByPage(final int page, final int size) {
        return null;
    }
}
