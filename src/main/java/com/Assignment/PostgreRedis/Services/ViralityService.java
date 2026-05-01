package com.Assignment.PostgreRedis.Services;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;



@Service
@RequiredArgsConstructor
public class ViralityService {
    
    private final RedisTemplate<String, String> redisTemplate;
    
    // Keys
    private String viralityKey(Long postId) {
        return "post:" + postId + ":virality_score";
    }

    private String botCountKey(Long postId) {
        return "post:" + postId + ":bot_count";
    }

    private String cooldownKey(Long botId, Long userId) {
        return "cooldown:bot_" + botId + ":human_" + userId;
    }

    // ─── Virality Score ───────────────────────────────────

    public void addBotReply(Long postId) {
        redisTemplate.opsForValue().increment(viralityKey(postId), 1);
    }

    public void addHumanLike(Long postId) {
        redisTemplate.opsForValue().increment(viralityKey(postId), 20);
    }

    public void addHumanComment(Long postId) {
        redisTemplate.opsForValue().increment(viralityKey(postId), 50);
    }

    public Long getViralityScore(Long postId) {
        String val = redisTemplate.opsForValue().get(viralityKey(postId));
        return val != null ? Long.parseLong(val) : 0L;
    }

    // ─── Bot Count & Cooldown ─────────────────────────────

    public void incrementBotCount(Long postId) {
        redisTemplate.opsForValue().increment(botCountKey(postId), 1);
    }

    public void setCooldown(Long botId, Long userId, long durationInSeconds) {
        redisTemplate.opsForValue().set(cooldownKey(botId, userId), "active", java.time.Duration.ofSeconds(durationInSeconds));
    }
}
