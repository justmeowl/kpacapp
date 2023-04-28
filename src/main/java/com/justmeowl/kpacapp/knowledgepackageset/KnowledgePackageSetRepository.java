package com.justmeowl.kpacapp.knowledgepackageset;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class KnowledgePackageSetRepository {
    private final JdbcTemplate jdbcTemplate;

    public KnowledgePackageSetRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<KnowledgePackageSet> findAll() {
        String sql = "SELECT * " +
                "FROM knowledge_package_set;";
        return jdbcTemplate.query(sql, new KnowledgePackageSetRowMapper());
    }

    public Optional<KnowledgePackageSet> findById(Long id) {
        String sql = "SELECT * " +
                "FROM knowledge_package_set " +
                "WHERE knowledge_package_set_id = ?;";
        return jdbcTemplate.query(sql, new KnowledgePackageSetRowMapper(), id)
                .stream()
                .findFirst();
    }

    public boolean existsById(Long id) {
        String sql = "SELECT count(knowledge_package_set_id) " +
                "FROM knowledge_package_set " +
                "WHERE knowledge_package_set_id = ?;";
        Long count = jdbcTemplate.queryForObject(sql, Long.class, id);
        return count != null && count > 0;
    }

    public int save(KnowledgePackageSet KnowledgePackageSet) {
        String sql = "INSERT INTO knowledge_package_set " +
                "(title) " +
                "VALUES (?);";
        return jdbcTemplate.update(sql, KnowledgePackageSet.getTitle());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM knowledge_package_set " +
                "WHERE knowledge_package_set_id = ?;";
        return jdbcTemplate.update(sql, id);
    }
}
