package com.justmeowl.kpacapp.knowledgepackage;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class KnowledgePackageRepository {
    private final JdbcTemplate jdbcTemplate;

    public KnowledgePackageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<KnowledgePackage> findAll() {
        String sql = "SELECT * " +
                "FROM knowledge_package;";
        return jdbcTemplate.query(sql, new KnowledgePackageRowMapper());
    }
    
    public Optional<KnowledgePackage> findById(Long id) {
        String sql = "SELECT * " +
                "FROM knowledge_package " +
                "WHERE knowledge_package_id = ?;";
        return jdbcTemplate.query(sql, new KnowledgePackageRowMapper(), id)
                .stream()
                .findFirst();
    }

    public Set<KnowledgePackage> findByKnowledgePackageSetId(Long id) {
        String sql = "SELECT knowledge_package.* " +
                "FROM knowledge_package " +
                "INNER JOIN knowledge_package_set_knowledge_package " +
                "USING(knowledge_package_id) " +
                "WHERE knowledge_package_set_id = ?;";
        return new HashSet<>(jdbcTemplate.query(sql, new KnowledgePackageRowMapper(), id));
    }

    public boolean existsById(Long id) {
        String sql = "SELECT count(knowledge_package_id) " +
                "FROM knowledge_package " +
                "WHERE knowledge_package_id = ?;";
        Long count = jdbcTemplate.queryForObject(sql, Long.class, id);
        return count != null && count > 0;
    }

    public int save(KnowledgePackage knowledgePackage) {
        String sql = "INSERT INTO knowledge_package " +
                "(title, description, creation_date) " +
                "VALUES (?, ?, ?);";
        return jdbcTemplate.update(sql,
                knowledgePackage.getTitle(),
                knowledgePackage.getDescription(),
                knowledgePackage.getCreationDate());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM knowledge_package " +
                "WHERE knowledge_package_id = ?;";
        return jdbcTemplate.update(sql, id);
    }

}
