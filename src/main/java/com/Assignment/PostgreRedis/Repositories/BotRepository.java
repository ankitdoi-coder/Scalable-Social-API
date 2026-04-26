package com.Assignment.PostgreRedis.Repositories;

import com.Assignment.PostgreRedis.Models.Bot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotRepository extends JpaRepository<Bot, Long> {
}