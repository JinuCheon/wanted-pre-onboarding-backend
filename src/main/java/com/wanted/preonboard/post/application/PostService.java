package com.wanted.preonboard.post.application;

import com.wanted.preonboard.member.domain.Member;
import com.wanted.preonboard.member.domain.MemberRepository;
import com.wanted.preonboard.post.domain.Post;
import com.wanted.preonboard.post.domain.PostRepository;
import com.wanted.preonboard.post.dto.request.CreatePostRequest;
import com.wanted.preonboard.post.dto.response.PostContentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public List<PostContentResponse> getFeedByPage(final int page, final int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findAll(pageable)
                .map(PostContentResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public PostContentResponse getSinglePost(final Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        return PostContentResponse.from(post);
    }

    @Transactional
    public void updatePost(final Long memberId, final Long postId, final PostUpdateRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        if (!post.isAuthor(member)) throw new IllegalArgumentException("작성자가 아닙니다.");

        post.update(request.updatedTitle(), request.updatedContent());
    }

    @Transactional
    public void deletePost(final Long memberId, final Long postId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        if (!post.isAuthor(member)) throw new IllegalArgumentException("작성자가 아닙니다.");
        postRepository.deleteById(postId);
    }
}
